package org.fountainmc.api.chat

import java.util.ArrayList
import java.util.Arrays
import java.util.EnumSet
import java.util.regex.Pattern

import com.google.common.base.Joiner
import com.google.common.collect.ImmutableSet

import org.fountainmc.api.chat.events.ClickEvent
import org.fountainmc.api.chat.values.Text

/**
 * This class is used to parse the text in a legacy chat message using a state
 * machine.
 */
internal class LegacyChatConverter(private val text: String) {
    private val parts = ArrayList<LegacyChatPart>()
    private val formattingFound = EnumSet.noneOf(ChatColor::class.java)
    var isParsed = false
        private set

    fun parse() {
        // - Formatting codes that are grouped together are part of the same
        // component.
        // (i.e. &c&lTEST)
        val foundText = StringBuilder()
        var state = State.TEXT
        textParse@ for (i in 0..text.length - 1) {
            val at = text[i]
            when (state) {
                LegacyChatConverter.State.LOOKING_FOR_CODE -> {
                    // Is this a code?
                    val color = ChatColor.getForChar(at)
                    if (color != null) {
                        // Yes, it is.
                        if (formattingFound.contains(color)) {
                            // No need, since this is a duplicate.
                            state = State.TEXT
                            continue@textParse
                        }

                        if (foundText.isNotEmpty() || color == ChatColor.RESET) {
                            // New text was found (or we're looking at a RESET),
                            // so this is a component.
                            parts.add(LegacyChatPart(ImmutableSet.copyOf(formattingFound), null, foundText.toString()))
                            foundText.delete(0, foundText.length)
                        }

                        if (color == ChatColor.RESET) {
                            formattingFound.clear()
                        } else {
                            if (!color.isFormatting) {
                                formattingFound.removeIf { c -> !c.isFormatting }
                            }
                            formattingFound.add(color)
                        }

                        state = State.TEXT
                    } else {
                        // No, it's text. Proceed to add '&' and proceed as if
                        // we were looking at text.
                        foundText.append('&')
                        foundText.append(at)
                        state = State.TEXT
                        continue@textParse
                    }
                }
                LegacyChatConverter.State.TEXT -> if (at == '\u00a7') {
                    state = State.LOOKING_FOR_CODE
                } else {
                    foundText.append(at)
                }
                else -> throw IllegalStateException("Unexpected state: " + state)
            }
        }

        // Finalize what we have
        if (foundText.isNotEmpty()) {
            parts.add(LegacyChatPart(ImmutableSet.copyOf(formattingFound), null, foundText.toString()))
        }

        // Now we can check for URLs
        val it = parts.listIterator()
        while (it.hasNext()) {
            val part = it.next()
            val splitForUrl = part.text.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            for (i in splitForUrl.indices) {
                val at = splitForUrl[i]
                if (url.matcher(at).matches()) {
                    // We need to rewrite this part.
                    it.remove()

                    val previousText = Joiner.on(' ').join(Arrays.copyOfRange(splitForUrl, 0, i)) + " "
                    val newPreviousPart = LegacyChatPart(part.formattingFound, null, previousText)
                    val thisPart = LegacyChatPart(part.formattingFound, at, at)
                    val nextText = " " + Joiner.on(' ').join(Arrays.copyOfRange(splitForUrl, i + 1, splitForUrl.size))
                    val nextPart = LegacyChatPart(part.formattingFound, null, nextText)

                    if (!previousText.trim { it <= ' ' }.isEmpty()) {
                        it.add(newPreviousPart)
                    }
                    it.add(thisPart)
                    if (!nextText.trim { it <= ' ' }.isEmpty()) {
                        it.add(nextPart)
                    }
                }
            }
        }

        isParsed = true
    }

    fun toComponents(): List<Component<Text>> {
        val components = ArrayList<Component<Text>>()
        for (part in parts) {
            var current = Components.forPlainText(part.text)
            for (color in part.formattingFound) {
                if (!color.isFormatting) {
                    current = current.withColor(color)
                } else {
                    when (color) {
                        ChatColor.BOLD -> current = current.withBold(true)
                        ChatColor.UNDERLINE -> current = current.withUnderlined(true)
                        ChatColor.ITALIC -> current = current.withItalic(true)
                        ChatColor.OBFUSCATED -> current = current.withObfuscated(true)
                        ChatColor.STRIKETHROUGH -> current = current.withStrikethrough(true)
                        else -> {
                        }
                    }
                }
            }
            if (part.url != null) {
                current = current.withClickEvent(ClickEvent.openUrl(part.url))
            }
            components.add(current)
        }
        if (components.isEmpty()) {
            components.add(Components.EMPTY_TEXT)
        }
        return components
    }

    private enum class State {
        TEXT,
        LOOKING_FOR_CODE
    }

    internal class LegacyChatPart(
            internal val formattingFound: Set<ChatColor>,
            internal val url: String?,
            internal val text: String
    )

    companion object {

        private val url = Pattern.compile("^(?:(https?)://)?([-\\w_\\.]{2,}\\.[a-z]{2,4})(/\\S*)?$")
    }

}
