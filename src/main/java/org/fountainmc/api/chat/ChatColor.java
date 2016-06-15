package org.fountainmc.api.chat;

import com.google.gson.annotations.SerializedName;
import it.unimi.dsi.fastutil.chars.Char2ObjectMap;
import it.unimi.dsi.fastutil.chars.Char2ObjectMaps;
import it.unimi.dsi.fastutil.chars.Char2ObjectOpenHashMap;

import javax.annotation.Nullable;
import java.util.Optional;

/**
 * Represents all available chat colors in Minecraft.
 */
public enum ChatColor {
    @SerializedName("dark_red")
    DARK_RED('4', false),
    @SerializedName("red")
    RED('c', false),
    @SerializedName("orange")
    ORANGE('6', false),
    @SerializedName("yellow")
    YELLOW('e', false),
    @SerializedName("green")
    GREEN('a', false),
    @SerializedName("dark_green")
    DARK_GREEN('2', false),
    @SerializedName("aqua")
    AQUA('b', false),
    @SerializedName("dark_aqua")
    DARK_AQUA('3', false),
    @SerializedName("dark_blue")
    DARK_BLUE('1', false),
    @SerializedName("blue")
    BLUE('9', false),
    @SerializedName("light_purple")
    LIGHT_PURPLE('d', false),
    @SerializedName("dark_purple")
    DARK_PURPLE('5', false),
    @SerializedName("white")
    WHITE('f', false),
    @SerializedName("gray")
    GREY('7', false),
    @SerializedName("dark_gray")
    DARK_GREY('8', false),
    @SerializedName("black")
    BLACK('0', false),
    BOLD('l', true),
    UNDERLINE('n', true),
    ITALIC('o', true),
    OBFUSCATED('k', true),
    STRIKETHROUGH('m', true),
    RESET('r', true);

    private static final Char2ObjectMap<ChatColor> COLOR_MAP;

    static {
        Char2ObjectMap<ChatColor> colors = new Char2ObjectOpenHashMap<>();
        for (ChatColor color : values()) {
            colors.put(color.colorCode, color);
        }
        COLOR_MAP = Char2ObjectMaps.unmodifiable(colors);
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
