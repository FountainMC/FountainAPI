package org.fountainmc.api.chat;

import com.google.common.base.Preconditions;
import org.fountainmc.api.Color;

public class TextComponent extends Component {
    public static final TextComponent EMPTY = new TextComponent(false, false, false, false, null, null, null, null, "");
    private final String text;

    protected TextComponent(boolean bold, boolean underlined, boolean strikethrough, boolean obfuscated, Color color, HoverEvent hoverEvent, ClickEvent clickEvent, String insertion, String text) {
        super(bold, underlined, strikethrough, obfuscated, color, hoverEvent, clickEvent, insertion);
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public TextComponent withBold(boolean bold) {
        return new TextComponent(bold, isUnderlined(), isStrikethrough(), isObfuscated(), getColor(),
                getHoverEvent().orElse(null), getClickEvent().orElse(null), getInsertion().orElse(null),
                text);
    }

    public TextComponent withUnderlined(boolean underlined) {
        return new TextComponent(isBold(), underlined, isStrikethrough(), isObfuscated(), getColor(),
                getHoverEvent().orElse(null), getClickEvent().orElse(null), getInsertion().orElse(null),
                text);
    }

    public TextComponent withStrikethrough(boolean strikethrough) {
        return new TextComponent(isBold(), isUnderlined(), strikethrough, isObfuscated(), getColor(),
                getHoverEvent().orElse(null), getClickEvent().orElse(null), getInsertion().orElse(null),
                text);
    }

    public TextComponent withObfuscated(boolean obfuscated) {
        return new TextComponent(isBold(), isUnderlined(), isStrikethrough(), obfuscated, getColor(),
                getHoverEvent().orElse(null), getClickEvent().orElse(null), getInsertion().orElse(null),
                text);
    }

    public TextComponent withColor(Color color) {
        return new TextComponent(isBold(), isUnderlined(), isStrikethrough(), isObfuscated(),
                color, getHoverEvent().orElse(null), getClickEvent().orElse(null),
                getInsertion().orElse(null), text);
    }

    public TextComponent withHoverEvent(HoverEvent<?> hoverEvent) {
        return new TextComponent(isBold(), isUnderlined(), isStrikethrough(), isObfuscated(), getColor(),
                hoverEvent, getClickEvent().orElse(null), getInsertion().orElse(null),
                text);
    }

    public TextComponent withClickEvent(ClickEvent clickEvent) {
        return new TextComponent(isBold(), isUnderlined(), isStrikethrough(), isObfuscated(), getColor(),
                getHoverEvent().orElse(null), clickEvent, getInsertion().orElse(null),
                text);
    }

    public TextComponent withInsertion(String insertion) {
        return new TextComponent(isBold(), isUnderlined(), isStrikethrough(), isObfuscated(), getColor(),
                getHoverEvent().orElse(null), getClickEvent().orElse(null), insertion,
                text);
    }

    public TextComponent withText(String text) {
        return new TextComponent(isBold(), isUnderlined(), isStrikethrough(), isObfuscated(), getColor(),
                getHoverEvent().orElse(null), getClickEvent().orElse(null), getInsertion().orElse(null),
                Preconditions.checkNotNull(text, "text"));
    }
}
