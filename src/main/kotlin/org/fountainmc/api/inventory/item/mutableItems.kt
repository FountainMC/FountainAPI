package org.fountainmc.api.inventory.item

import org.fountainmc.api.enchantments.EnchantmentType

/**
 * Run the specified action on this item's enchantments.
 */
inline fun MutableItem.withEnchantments(func: MutableMap<EnchantmentType, Int>.() -> Unit) {
    enchantments.apply(func)
}

/**
 * Run the specified action on the item's list of lore.
 */
inline fun MutableItem.lore(func: MutableList<String>.() -> Unit) {
    lore.apply(func)
}

/**
 * Clear the builder's current enchantments,
 * then perform the specified action on it's map.
 */
inline fun MutableItem.resetEnchantments(func: MutableMap<EnchantmentType, Int>.() -> Unit) {
    enchantments.clear()
    enchantments.apply(func)
}

/**
 * Clear the builder's current lore,
 * then perform the specified action on it's list.
 */
inline fun MutableItem.resetLore(func: MutableList<String>.() -> Unit) {
    lore.clear()
    lore.apply(func)
}
