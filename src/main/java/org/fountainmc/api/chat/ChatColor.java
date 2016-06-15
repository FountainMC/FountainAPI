package org.fountainmc.api.chat;

import it.unimi.dsi.fastutil.chars.Char2ObjectMap;
import it.unimi.dsi.fastutil.chars.Char2ObjectMaps;
import it.unimi.dsi.fastutil.chars.Char2ObjectOpenHashMap;

import java.util.Optional;

public enum ChatColor {
    DARK_RED('4', false),
    RED('c', false),
    ORANGE('6', false),
    YELLOW('e', false),
    GREEN('a', false),
    DARK_GREEN('2', false),
    AQUA('b', false),
    DARK_AQUA('3', false),
    DARK_BLUE('1', false),
    BLUE('9', false),
    LIGHT_PURPLE('d', false),
    DARK_PURPLE('5', false),
    WHITE('f', false),
    GREY('7', false),
    DARK_GREY('8', false),
    BLACK('0', false),
    BOLD('l', true),
    UNDERLINE('n', true),
    ITALIC('o', true),
    OBFUSCATED('k', true),
    STRIKETHROUGH('m', true),
    RESET('r', true);

    private final char colorCode;
    private final boolean isFormatting;

    ChatColor(char colorCode, boolean isFormatting) {
        this.colorCode = colorCode;
        this.isFormatting = isFormatting;
    }

    public char getColorCode() {
        return colorCode;
    }

    public boolean isFormatting() {
        return isFormatting;
    }

    private static final Char2ObjectMap<ChatColor> COLOR_MAP;

    public static Optional<ChatColor> getForChar(char ch) {
        return Optional.ofNullable(COLOR_MAP.get(ch));
    }

    static {
        Char2ObjectMap<ChatColor> colors = new Char2ObjectOpenHashMap<>();
        for (ChatColor color : values()) {
            colors.put(color.colorCode, color);
        }
        COLOR_MAP = Char2ObjectMaps.unmodifiable(colors);
    }
}
