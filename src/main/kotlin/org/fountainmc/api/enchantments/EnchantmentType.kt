package org.fountainmc.api.enchantments

interface EnchantmentType {
    val name: String

    val id: Int

    val minLevel: Int
        get() = 1

    val maxLevel: Int
}
