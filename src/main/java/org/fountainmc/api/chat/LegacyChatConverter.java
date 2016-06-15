package org.fountainmc.api.chat;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableSet;
import org.fountainmc.api.chat.events.ClickEvent;
import org.fountainmc.api.chat.values.Text;

import java.util.*;
import java.util.regex.Pattern;

/**
 * This class is used to parse the text in a legacy chat message using a state machine.
 */
class LegacyChatConverter {
    private static final Pattern url = Pattern.compile("^(?:(https?)://)?([-\\w_\\.]{2,}\\.[a-z]{2,4})(/\\S*)?$");
    private final String text;
    private final List<LegacyChatPart> parts = new ArrayList<>();
    private final Set<ChatColor> formattingFound = EnumSet.noneOf(ChatColor.class);
    private boolean parsed = false;

    LegacyChatConverter(String text) {
        this.text = text;
    }

    public void parse() {
        // - Formatting codes that are grouped together are part of the same component.
        //   (i.e. &c&lTEST)
        StringBuilder foundText = new StringBuilder();
        State state = State.TEXT;
        textParse:
        for (int i = 0; i < text.length(); i++) {
            char at = text.charAt(i);
            switch (state) {
                case LOOKING_FOR_CODE:
                    // Is this a code?
                    ChatColor color = ChatColor.getForChar(at);
                    if (color != null) {
                        // Yes, it is.
                        if (formattingFound.contains(color)) {
                            // No need, since this is a duplicate.
                            state = State.TEXT;
                            continue textParse;
                        }

                        if (foundText.length() > 0 || color == ChatColor.RESET) {
                            // New text was found (or we're looking at a RESET), so this is a component.
                            parts.add(new LegacyChatPart(ImmutableSet.copyOf(formattingFound), null, foundText.toString()));
                            foundText.delete(0, foundText.length());
                        }

                        if (color == ChatColor.RESET) {
                            formattingFound.clear();
                        } else {
                            if (!color.isFormatting()) {
                                formattingFound.removeIf(c -> !c.isFormatting());
                            }
                            formattingFound.add(color);
                        }

                        state = State.TEXT;
                    } else {
                        // No, it's text. Proceed to add '&' and proceed as if we were looking at text.
                        foundText.append('&');
                        foundText.append(at);
                        state = State.TEXT;
                        continue textParse;
                    }
                    break;
                case TEXT:
                    if (at == '\u00a7') {
                        state = State.LOOKING_FOR_CODE;
                    } else {
                        foundText.append(at);
                    }
                    break;
            }
        }

        // Finalize what we have
        if (foundText.length() > 0) {
            parts.add(new LegacyChatPart(ImmutableSet.copyOf(formattingFound), null, foundText.toString()));
        }

        // Now we can check for URLs
        for (ListIterator<LegacyChatPart> it = parts.listIterator(); it.hasNext(); ) {
            LegacyChatPart part = it.next();
            String[] splitForUrl = part.text.split(" ");
            for (int i = 0; i < splitForUrl.length; i++) {
                String at = splitForUrl[i];
                if (url.matcher(at).matches()) {
                    // We need to rewrite this part.
                    it.remove();

                    String previousText = Joiner.on(' ').join(Arrays.copyOfRange(splitForUrl, 0, i)) + " ";
                    LegacyChatPart newPreviousPart = new LegacyChatPart(part.formattingFound, null, previousText);
                    LegacyChatPart thisPart = new LegacyChatPart(part.formattingFound, at, at);
                    String nextText = " " + Joiner.on(' ').join(Arrays.copyOfRange(splitForUrl, i + 1, splitForUrl.length));
                    LegacyChatPart nextPart = new LegacyChatPart(part.formattingFound, null, nextText);

                    if (!previousText.trim().isEmpty()) {
                        it.add(newPreviousPart);
                    }
                    it.add(thisPart);
                    if (!nextText.trim().isEmpty()) {
                        it.add(nextPart);
                    }
                }
            }
        }

        parsed = true;
    }

    public List<Component<Text>> toComponents() {
        List<Component<Text>> components = new ArrayList<>();
        for (LegacyChatPart part : parts) {
            Component<Text> current = Components.forPlainText(part.text);
            for (ChatColor color : part.formattingFound) {
                if (!color.isFormatting()) {
                    current = current.withColor(color);
                } else {
                    switch (color) {
                        case BOLD:
                            current = current.withBold(true);
                            break;
                        case UNDERLINE:
                            current = current.withUnderlined(true);
                            break;
                        case ITALIC:
                            current = current.withItalic(true);
                            break;
                        case OBFUSCATED:
                            current = current.withObfuscated(true);
                            break;
                        case STRIKETHROUGH:
                            current = current.withStrikethrough(true);
                            break;
                    }
                }
            }
            if (part.url != null) {
                current = current.withClickEvent(ClickEvent.openUrl(part.url));
            }
            components.add(current);
        }
        if (components.isEmpty()) {
            components.add(Components.EMPTY_TEXT);
        }
        return components;
    }

    private enum State {
        TEXT,
        LOOKING_FOR_CODE
    }

    private class LegacyChatPart {
        private final Set<ChatColor> formattingFound;
        private final String url;
        private final String text;

        private LegacyChatPart(Set<ChatColor> formattingFound, String url, String text) {
            this.formattingFound = formattingFound;
            this.url = url;
            this.text = text;
        }
    }
}
