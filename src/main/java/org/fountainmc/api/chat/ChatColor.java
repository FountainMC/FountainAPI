package org.fountainmc.api.chat;

import java.util.Map;
import javax.annotation.Nullable;

import com.google.common.collect.ImmutableMap;
import com.google.gson.annotations.SerializedName;

/**
 * Represents all available chat colors in Minecraft.
 */
public enum ChatColor {

    @SerializedName("dark_red") DARK_RED('4', false),
    @SerializedName("red") RED('c', false),
    @SerializedName("orange") ORANGE('6', false),
    @SerializedName("yellow") YELLOW('e', false),
    @SerializedName("green") GREEN('a', false),
    @SerializedName("dark_green") DARK_GREEN('2', false),
    @SerializedName("aqua") AQUA('b', false),
    @SerializedName("dark_aqua") DARK_AQUA('3', false),
    @SerializedName("dark_blue") DARK_BLUE('1', false),
    @SerializedName("blue") BLUE('9', false),
    @SerializedName("light_purple") LIGHT_PURPLE('d', false),
    @SerializedName("dark_purple") DARK_PURPLE('5', false),
    @SerializedName("white") WHITE('f', false),
    @SerializedName("gray") GREY('7', false),
    @SerializedName("dark_gray") DARK_GREY('8', false),
    @SerializedName("black") BLACK('0', false),
    BOLD('l', true),
    UNDERLINE('n', true),
    ITALIC('o', true),
    OBFUSCATED('k', true),
    STRIKETHROUGH('m', true),
    RESET('r', true);

    private static final Map<Character, ChatColor> COLOR_MAP;

    static {
        ImmutableMap.Builder<Character, ChatColor> builder = ImmutableMap.builder();
        for (ChatColor color : values()) {
            builder.put(color.getColorCode(), color);
        }
        COLOR_MAP = builder.build();
    }

    private final char colorCode;
    private final boolean isFormatting;

    ChatColor(char colorCode, boolean isFormatting) {
        this.colorCode = colorCode;
        this.isFormatting = isFormatting;
    }

    @Nullable
    public static ChatColor getForChar(char ch) {
        return COLOR_MAP.get(ch);
    }

    public char getColorCode() {
        return colorCode;
    }

    public boolean isFormatting() {
        return isFormatting;
    }

}
