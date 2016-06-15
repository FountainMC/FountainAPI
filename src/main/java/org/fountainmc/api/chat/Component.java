package org.fountainmc.api.chat;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import org.fountainmc.api.chat.events.ClickEvent;
import org.fountainmc.api.chat.events.HoverEvent;
import org.fountainmc.api.chat.values.ComponentValue;

import java.util.List;
import java.util.Optional;

/**
 * This class represents a Minecraft chat component.
 */
public class Component<T extends ComponentValue> {
    private final Component<?> parent;
    private final Boolean bold;
    private final Boolean italic;
    private final Boolean underlined;
    private final Boolean strikethrough;
    private final Boolean obfuscated;
    private final ChatColor color;
    private final HoverEvent hoverEvent;
    private final ClickEvent clickEvent;
    private final String insertion;
    private final T value;
    private final List<Component<?>> extra;

    protected Component(Component<?> parent, Boolean bold, Boolean italic, Boolean underlined, Boolean strikethrough, Boolean obfuscated, ChatColor color, HoverEvent hoverEvent, ClickEvent clickEvent, String insertion, T value, List<Component<?>> extra) {
        this.parent = parent;
        this.bold = bold;
        this.italic = italic;
        this.underlined = underlined;
        this.strikethrough = strikethrough;
        this.obfuscated = obfuscated;
        this.color = color;
        this.hoverEvent = hoverEvent;
        this.clickEvent = clickEvent;
        this.insertion = insertion;
        this.value = value;
        this.extra = extra;
    }

    public boolean isBold() {
        if (bold != null) {
            return bold;
        }

        return parent != null && parent.bold;
    }

    public boolean isItalic() {
        if (italic != null) {
            return italic;
        }

        return parent != null && parent.italic;
    }

    public boolean isUnderlined() {
        if (underlined != null) {
            return underlined;
        }

        return parent != null && parent.underlined;
    }

    public boolean isStrikethrough() {
        if (strikethrough != null) {
            return strikethrough;
        }

        return parent != null && parent.strikethrough;
    }

    public boolean isObfuscated() {
        if (obfuscated != null) {
            return obfuscated;
        }

        return parent != null && parent.obfuscated;
    }

    protected Boolean isBoldRaw() {
        return bold;
    }

    protected Boolean isItalicRaw() {
        return italic;
    }

    protected Boolean isUnderlinedRaw() {
        return underlined;
    }

    protected Boolean isStrikethroughRaw() {
        return strikethrough;
    }

    protected Boolean isObfuscatedRaw() {
        return obfuscated;
    }

    protected ChatColor getColorRaw() {
        return color;
    }

    protected HoverEvent<?> getHoverEventRaw() {
        return hoverEvent;
    }

    protected ClickEvent getClickEventRaw() {
        return clickEvent;
    }

    protected String getInsertionRaw() {
        return insertion;
    }

    public ChatColor getColor() {
        if (color != null) {
            return color;
        }
        return parent != null ? parent.color : ChatColor.WHITE;
    }

    public Optional<HoverEvent> getHoverEvent() {
        if (hoverEvent == null) {
            return parent != null ? parent.getHoverEvent() : Optional.empty();
        }
        return Optional.of(hoverEvent);
    }

    public Optional<ClickEvent> getClickEvent() {
        if (clickEvent == null) {
            return parent != null ? parent.getClickEvent() : Optional.empty();
        }
        return Optional.of(clickEvent);
    }

    public Optional<String> getInsertion() {
        if (insertion == null) {
            return parent != null ? parent.getInsertion() : Optional.empty();
        }
        return Optional.of(insertion);
    }

    public T getValue() {
        return value;
    }

    public List<Component<?>> getExtra() {
        return extra == null ? ImmutableList.of() : ImmutableList.copyOf(extra);
    }

    public Optional<Component<?>> getParent() {
        return Optional.ofNullable(parent);
    }

    public Component<T> withBold(boolean bold) {
        return new Component<>(parent, bold, italic, underlined, strikethrough, obfuscated, color,
                hoverEvent, clickEvent, insertion, value, extra);
    }

    public Component<T> withItalic(boolean italic) {
        return new Component<>(parent, bold, italic, underlined, strikethrough, obfuscated, color,
                hoverEvent, clickEvent, insertion, value, extra);
    }

    public Component<T> withUnderlined(boolean underlined) {
        return new Component<>(parent, bold, italic, underlined, strikethrough, obfuscated, color,
                hoverEvent, clickEvent, insertion, value, extra);
    }

    public Component<T> withStrikethrough(boolean strikethrough) {
        return new Component<>(parent, bold, italic, underlined, strikethrough, obfuscated, color,
                hoverEvent, clickEvent, insertion, value, extra);
    }

    public Component<T> withObfuscated(boolean obfuscated) {
        return new Component<>(parent, bold, italic, underlined, strikethrough, obfuscated, color,
                hoverEvent, clickEvent, insertion, value, extra);
    }

    public Component<T> withColor(ChatColor color) {
        if (color != null) {
            Preconditions.checkArgument(!color.isFormatting(), "color is a formatting code");
        }
        return new Component<>(parent, bold, italic, underlined, strikethrough, obfuscated, color,
                hoverEvent, clickEvent, insertion, value, extra);
    }

    public Component<T> withHoverEvent(HoverEvent<?> hoverEvent) {
        return new Component<>(parent, bold, italic, underlined, strikethrough, obfuscated, color,
                hoverEvent, clickEvent, insertion, value, extra);
    }

    public Component<T> withClickEvent(ClickEvent clickEvent) {
        return new Component<>(parent, bold, italic, underlined, strikethrough, obfuscated, color,
                hoverEvent, clickEvent, insertion, value, extra);
    }

    public Component<T> withInsertion(String insertion) {
        return new Component<>(parent, bold, italic, underlined, strikethrough, obfuscated, color,
                hoverEvent, clickEvent, insertion, value, extra);
    }

    public Component<T> withValue(T value) {
        Preconditions.checkNotNull(value, "value");
        Preconditions.checkArgument(value.getClass().isAssignableFrom(this.value.getClass()), "invalid value, needs to implement current value's class");
        return new Component<>(parent, bold, italic, underlined, strikethrough, obfuscated, color,
                hoverEvent, clickEvent, insertion, value, extra);
    }

    public Component<T> withParent(Component<?> parent) {
        return new Component<>(parent, bold, italic, underlined, strikethrough, obfuscated, color,
                hoverEvent, clickEvent, insertion, value, extra);
    }
}
