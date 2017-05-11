package org.fountainmc.api.chat

import com.google.common.collect.ImmutableList
import org.fountainmc.api.chat.events.ClickEvent
import org.fountainmc.api.chat.events.HoverEvent
import org.fountainmc.api.chat.values.ComponentValue
import org.fountainmc.api.internal.utils.immutableListOf
import org.fountainmc.api.internal.utils.toImmutableList
import javax.annotation.concurrent.Immutable


/**
 * This class represents a Minecraft chat component.
 * This class is immutable and is thread-safe.
 *
 * Components may have a [parent] value,
 * which undefined properties are inherited from.
 */
@Immutable
data class Component<out T : ComponentValue> @Deprecated("Prefer ComponentBuilder") constructor(
        val parent: Component<*>? = null,
        internal val isBoldRaw: Boolean? = null,
        internal val isItalicRaw: Boolean? = null,
        internal val isUnderlinedRaw: Boolean? = null,
        internal val isStrikethroughRaw: Boolean? = null,
        internal val isObfuscatedRaw: Boolean? = null,
        internal val colorRaw: ChatColor? = null,
        internal val hoverEventRaw: HoverEvent<*>? = null,
        internal val clickEventRaw: ClickEvent? = null,
        internal val insertionRaw: String? = null,
        val value: T,
        val extra: ImmutableList<Component<*>> = immutableListOf()
) {
    init {
        var p = parent
        while (p != null) {
            require(p != this) { "Cyclic component chain!" }
            p = p.parent
        }
        if (colorRaw != null) {
            require(!colorRaw.isFormatting) { "$colorRaw is a formatting code, not a color!" }
        }
    }

    val isBold: Boolean
        get() = isBoldRaw ?: parent?.isBold ?: false

    val isItalic: Boolean
        get() = isItalicRaw ?: parent?.isItalic ?: false

    val isUnderlined: Boolean
        get() = isUnderlinedRaw ?: parent?.isItalic ?: false

    val isStrikethrough: Boolean
        get() = isStrikethroughRaw ?: parent?.isStrikethrough ?: false

    val isObfuscated: Boolean
        get() = isObfuscatedRaw ?: parent?.isObfuscated ?: false

    val color: ChatColor
        get() = colorRaw ?: parent?.color ?: ChatColor.WHITE

    val hoverEvent: HoverEvent<*>?
        get() = hoverEventRaw ?: parent?.hoverEvent

    val clickEvent: ClickEvent?
        get() = clickEventRaw ?: parent?.clickEvent

    val insertion: String?
        get() = insertionRaw ?: parent?.insertion


    /**
     * Returns a copy of this component with the specified [bold][isBold] value.
     *
     * @param bold the new value
     * @return a copy of this component with the new value
     */
    fun withBold(bold: Boolean): Component<T> {
        return copy(isBoldRaw = bold)
    }

    /**
     * Returns a copy of this component with the specified [italic][isItalic] value.

     * @param italic the new value
     * @return a copy of this component with the new value
     */
    fun withItalic(italic: Boolean): Component<T> {
        return copy(isItalicRaw = italic)

    }

    /**
     * Returns a copy of this component with a new [underlined][isUnderlined] value.
     *
     * @param underlined the new value
     * @return a copy of this component with the new value
     */
    fun withUnderlined(underlined: Boolean): Component<T> {
        return copy(isUnderlinedRaw = underlined)
    }

    /**
     * Returns a copy of this component with a new [strikethrough][isStrikethrough] value.
     *
     * @param strikethrough the new value
     * @return a copy of this component with the new value
     */
    fun withStrikethrough(strikethrough: Boolean): Component<T> {
        return copy(isStrikethroughRaw = strikethrough)
    }

    /**
     * Returns a copy of this component with a new [obfuscated][isObfuscated] value.
     *
     * @param obfuscated the new value
     * @return a copy of this component with the new value
     */
    fun withObfuscated(obfuscated: Boolean): Component<T> {
        return copy(isObfuscatedRaw = obfuscated)
    }

    /**
     * Returns a copy of this component with a new [color] value.
     *
     * @param color the new value
     * @return a copy of this component with the new value
     */
    fun withColor(color: ChatColor?): Component<T> {
        return copy(colorRaw = color)
    }

    /**
     * Returns a copy of this component with a new [hoverEvent] value.
     *
     * @param hoverEvent the new value
     * @return a copy of this component with the new value
     */
    fun withHoverEvent(hoverEvent: HoverEvent<*>): Component<T> {
        return copy(hoverEventRaw = hoverEvent)
    }

    /**
     * Returns a copy of this component with a new [clickEvent] value.
     *
     * @param clickEvent the new value
     * @return a copy of this component with the new value
     */
    fun withClickEvent(clickEvent: ClickEvent): Component<T> {
        return copy(clickEventRaw = clickEvent)
    }

    /**
     * Returns a copy of this component with a new [insertion] value.
     *
     * @param insertion the new value
     * @return a copy of this component with the new value
     */
    fun withInsertion(insertion: String): Component<T> {
        return copy(insertionRaw = insertion)
    }

    /**
     * Returns a copy of this component with a new [value].
     * Note that a component can only have its value changed
     * to a different value of the same type.
     *
     * @param newValue the new value
     * @return a copy of this component with the new value
     */
    fun withValue(newValue: @UnsafeVariance T): Component<T> {
        require(this.value::class.isInstance(newValue)) {
            "New value's type doesn't match existing type ${this.value::class}: $newValue"
        }
        return copy(value = newValue)
    }

    /**
     * Returns a copy of this component with a new [parent].
     *
     * @param parent the new parent
     * @return a copy of this component with a new value
     */
    fun withParent(parent: Component<*>): Component<T> {
        return copy(parent = parent)
    }

    /**
     * Returns a copy of this component with a new [extra] value.
     *
     * @param extra the new value
     * @return a copy of this component with a new value
     */
    fun withExtra(extra: List<Component<*>>): Component<T> {
        return copy(extra = extra.toImmutableList())
    }

    fun toPlainText(): String = toPlainText(StringBuilder()).toString()
    fun toPlainText(buffer: StringBuilder) {
        value.toPlainText(buffer)
        extra.forEach { it.toPlainText(buffer) }
    }
}
