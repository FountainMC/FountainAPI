package org.fountainmc.api.chat

import com.google.common.collect.ImmutableList
import org.fountainmc.api.chat.events.ClickEvent
import org.fountainmc.api.chat.events.HoverEvent
import org.fountainmc.api.chat.values.Text

/**
 * [ComponentBuilder] handles the creation of text-based [Component]
 * s using a fluent API.
 */
class ComponentBuilder private constructor(first: String) {

    private val components = ImmutableList.builder<Component<Text>>()
    private var currentComponent = Components.EMPTY_TEXT.withValue(Text.of(first))

    /**
     * Creates a new component, preserving existing formatting.

     * @param text the new text to use
     * *
     * @return itself, for chaining
     */
    fun then(text: String): ComponentBuilder {
        currentComponent = currentComponent.withValue(Text.of(text))
        components.add(currentComponent)
        currentComponent = currentComponent.withParent(currentComponent)
        return this
    }

    /**
     * Changes the color of the current component.

     * @param color the color to use
     * *
     * @return itself, for chaining
     */
    fun color(color: ChatColor): ComponentBuilder {
        require(!color.isFormatting) { "color is a formatting code: $color" }
        currentComponent = currentComponent.withColor(color)
        return this
    }

    /**
     * Changes the insertion (text inserted on shift-click) of this text.

     * @param insertion the insertion to use
     * *
     * @return itself, for chaining
     */
    fun insertion(insertion: String): ComponentBuilder {
        currentComponent = currentComponent.withInsertion(insertion)
        return this
    }

    /**
     * Changes the hover event for use.

     * @param event the hover event to use
     * *
     * @return itself, for chaining
     */
    fun hover(event: HoverEvent<*>): ComponentBuilder {
        currentComponent = currentComponent.withHoverEvent(event)
        return this
    }

    /**
     * Changes the click event for use.

     * @param event the click event to use.
     * *
     * @return itself, for chaining
     */
    fun click(event: ClickEvent): ComponentBuilder {
        currentComponent = currentComponent.withClickEvent(event)
        return this
    }

    /**
     * Changes whether or not the text is bold.

     * @param bold whether or not the text is bold
     * *
     * @return itself, for chaining
     */
    fun bold(bold: Boolean): ComponentBuilder {
        currentComponent = currentComponent.withBold(bold)
        return this
    }

    /**
     * Changes whether or not the text is italic.

     * @param italic whether or not the text is italic
     * *
     * @return itself, for chaining
     */
    fun italic(italic: Boolean): ComponentBuilder {
        currentComponent = currentComponent.withItalic(italic)
        return this
    }

    /**
     * Changes whether or not the text is underlined.

     * @param underlined whether or not the text is underlined
     * *
     * @return itself, for chaining
     */
    fun underlined(underlined: Boolean): ComponentBuilder {
        currentComponent = currentComponent.withUnderlined(underlined)
        return this
    }

    /**
     * Changes whether or not the text is obfuscated.

     * @param obfuscated whether or not the text is obfuscated
     * *
     * @return itself, for chaining
     */
    fun obfuscated(obfuscated: Boolean): ComponentBuilder {
        currentComponent = currentComponent.withObfuscated(obfuscated)
        return this
    }

    /**
     * Changes whether or not the text is stricken through.

     * @param strikethrough whether or not the text is stricken through
     * *
     * @return itself, for chaining
     */
    fun strikethrough(strikethrough: Boolean): ComponentBuilder {
        currentComponent = currentComponent.withStrikethrough(strikethrough)
        return this
    }

    fun reset(): ComponentBuilder {
        currentComponent = Components.EMPTY_TEXT
        return this
    }

    fun build(): List<Component<Text>> {
        components.add(currentComponent)
        return components.build()
    }

    companion object {
        @JvmStatic
        fun start(first: String): ComponentBuilder {
            return ComponentBuilder(first)
        }
    }
}
