package org.fountainmc.api.chat;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import org.fountainmc.api.chat.events.ClickEvent;
import org.fountainmc.api.chat.events.HoverEvent;
import org.fountainmc.api.chat.values.Text;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * {@link ComponentBuilder} handles the creation of text-based {@link Component}
 * s using a fluent API.
 */
public class ComponentBuilder {

    private final List<Component<Text>> components = new ArrayList<>();
    private Component<Text> currentComponent = Components.EMPTY_TEXT;

    private ComponentBuilder(String first) {
        currentComponent = currentComponent.withValue(Text.of(checkNotNull(first, "first")));
    }

    public static ComponentBuilder start(String first) {
        return new ComponentBuilder(Preconditions.checkNotNull(first));
    }

    /**
     * Creates a new component, preserving existing formatting.
     *
     * @param text the new text to use
     * @return itself, for chaining
     */
    public ComponentBuilder then(String text) {
        currentComponent = currentComponent.withValue(Text.of(checkNotNull(text, "text")));
        components.add(currentComponent);
        currentComponent = currentComponent.withParent(currentComponent);
        return this;
    }

    /**
     * Changes the color of the current component.
     *
     * @param color the color to use
     * @return itself, for chaining
     */
    public ComponentBuilder color(ChatColor color) {
        checkNotNull(color, color);
        checkArgument(!color.isFormatting(), "color is a formatting code");
        currentComponent = currentComponent.withColor(color);
        return this;
    }

    /**
     * Changes the insertion (text inserted on shift-click) of this text.
     *
     * @param insertion the insertion to use
     * @return itself, for chaining
     */
    public ComponentBuilder insertion(String insertion) {
        currentComponent = currentComponent.withInsertion(insertion);
        return this;
    }

    /**
     * Changes the hover event for use.
     *
     * @param event the hover event to use
     * @return itself, for chaining
     */
    public ComponentBuilder hover(HoverEvent<?> event) {
        currentComponent = currentComponent.withHoverEvent(event);
        return this;
    }

    /**
     * Changes the click event for use.
     *
     * @param event the click event to use.
     * @return itself, for chaining
     */
    public ComponentBuilder click(ClickEvent event) {
        currentComponent = currentComponent.withClickEvent(event);
        return this;
    }

    /**
     * Changes whether or not the text is bold.
     *
     * @param bold whether or not the text is bold
     * @return itself, for chaining
     */
    public ComponentBuilder bold(boolean bold) {
        currentComponent = currentComponent.withBold(bold);
        return this;
    }

    /**
     * Changes whether or not the text is italic.
     *
     * @param italic whether or not the text is italic
     * @return itself, for chaining
     */
    public ComponentBuilder italic(boolean italic) {
        currentComponent = currentComponent.withItalic(italic);
        return this;
    }

    /**
     * Changes whether or not the text is underlined.
     *
     * @param underlined whether or not the text is underlined
     * @return itself, for chaining
     */
    public ComponentBuilder underlined(boolean underlined) {
        currentComponent = currentComponent.withUnderlined(underlined);
        return this;
    }

    /**
     * Changes whether or not the text is obfuscated.
     *
     * @param obfuscated whether or not the text is obfuscated
     * @return itself, for chaining
     */
    public ComponentBuilder obfuscated(boolean obfuscated) {
        currentComponent = currentComponent.withObfuscated(obfuscated);
        return this;
    }

    /**
     * Changes whether or not the text is stricken through.
     *
     * @param strikethrough whether or not the text is stricken through
     * @return itself, for chaining
     */
    public ComponentBuilder strikethrough(boolean strikethrough) {
        currentComponent = currentComponent.withStrikethrough(strikethrough);
        return this;
    }

    public ComponentBuilder reset() {
        currentComponent = Components.EMPTY_TEXT;
        return this;
    }

    public List<Component<Text>> build() {
        components.add(currentComponent);
        return ImmutableList.copyOf(components);
    }

}
