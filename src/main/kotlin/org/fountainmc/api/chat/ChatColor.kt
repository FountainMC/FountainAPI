package org.fountainmc.api.chat

import com.google.common.collect.ImmutableMap
import com.google.gson.annotations.SerializedName

/**
 * Represents all available chat colors in Minecraft.
 */
enum class ChatColor private constructor(val colorCode: Char, val isFormatting: Boolean) {

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


    companion object {

        private val COLOR_MAP: Map<Char, ChatColor>

        init {
            val builder = ImmutableMap.builder<Char, ChatColor>()
            for (color in values()) {
                builder.put(color.colorCode, color)
            }
            COLOR_MAP = builder.build()
        }

        fun getForChar(ch: Char): ChatColor? {
            return COLOR_MAP[ch]
        }
    }

}
