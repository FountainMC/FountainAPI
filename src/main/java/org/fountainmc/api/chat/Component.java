package org.fountainmc.api.chat;

import org.fountainmc.api.Color;

import java.util.Optional;

/**
 * This class represents a Minecraft chat component.
 */
public abstract class Component {
    private final boolean bold;
    private final boolean underlined;
    private final boolean strikethrough;
    private final boolean obfuscated;
    private final Color color;
    private final HoverEvent hoverEvent;
    private final ClickEvent clickEvent;
    private final String insertion;

    protected Component(boolean bold, boolean underlined, boolean strikethrough, boolean obfuscated, Color color, HoverEvent hoverEvent, ClickEvent clickEvent, String insertion) {
        this.bold = bold;
        this.underlined = underlined;
        this.strikethrough = strikethrough;
        this.obfuscated = obfuscated;
        this.color = color;
        this.hoverEvent = hoverEvent;
        this.clickEvent = clickEvent;
        this.insertion = insertion;
    }

    public boolean isBold() {
        return bold;
    }

    public boolean isUnderlined() {
        return underlined;
    }

    public boolean isStrikethrough() {
        return strikethrough;
    }

    public boolean isObfuscated() {
        return obfuscated;
    }

    public Color getColor() {
        return color;
    }

    public Optional<HoverEvent> getHoverEvent() {
        return Optional.ofNullable(hoverEvent);
    }

    public Optional<ClickEvent> getClickEvent() {
        return Optional.ofNullable(clickEvent);
    }

    public Optional<String> getInsertion() {
        return Optional.ofNullable(insertion);
    }
}
