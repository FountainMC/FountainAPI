package org.fountainmc.api.inventory.item

import org.fountainmc.api.enchantments.EnchantmentType
import javax.annotation.OverridingMethodsMustInvokeSuper

/**
 * A mutable [Item].
 */
interface MutableItem: Item {
    @Suppress("OverridingDeprecatedMember")
    override var rawData: Short
    override var amount: Int
    override var displayName: String?
    override var isUnbreakable: Boolean
    override val lore: MutableList<String>
    override val enchantments: MutableMap<EnchantmentType, Int>

    fun addEnchantment(type: EnchantmentType, level: Int = 1) {
        val minLevel = type.minLevel
        require(level >= minLevel) { "Level $level is lower than minimum $type level $minLevel" }
        this.enchantments += (type to level)
    }

    /**
     * Copy the values from the specified item to this object.
     */
    @OverridingMethodsMustInvokeSuper
    fun copyFrom(item: Item) {
        val itemType = item.type
        require(itemType == this.type) { "Item's type $itemType doesn't equal the builder's type ${this.type}" }

        @Suppress("DEPRECATION")
        this.rawData = item.rawData
        this.amount = item.amount
        this.displayName = item.displayName
        this.resetLore(item.lore)
        this.resetEnchantments(item.enchantments)
        this.isUnbreakable = item.isUnbreakable
    }

    /**
     * Reset all the lore in this builder,
     * replacing it with the specified values
     *
     * Equivalent to `lore.clear()` then `lore.addAll(newLore)`
     */
    fun resetLore(newLore: List<String> = emptyList()) = resetLore { addAll(newLore) }

    /**
     * Reset all the enchantments in this builder,
     * replacing it with the specified values.
     *
     * Equivalent to `enchantments.clear()` then `enchantments.putAll(newEnchantments)`
     */
    fun resetEnchantments(newEnchantments: Map<EnchantmentType, Int> = emptyMap()) = resetEnchantments { putAll(newEnchantments) }
}