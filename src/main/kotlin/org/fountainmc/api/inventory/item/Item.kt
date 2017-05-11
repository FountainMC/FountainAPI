package org.fountainmc.api.inventory.item

import org.fountainmc.api.Material
import org.fountainmc.api.Server
import org.fountainmc.api.enchantments.EnchantmentType
import javax.annotation.ParametersAreNonnullByDefault

/**
 * A stack of items in a player's inventory.
 */
@ParametersAreNonnullByDefault
interface Item {
    val server: Server

    val type: Material<Item>

    @Deprecated("Magic value")
    val rawData: Short

    val amount: Int

    val isUnbreakable: Boolean

    val displayName: String?

    val hasDisplayName: Boolean
        get() = displayName != null

    val lore: List<String>

    val hasLore: Boolean
        get() = lore.isNotEmpty()

    // Enchantment methods

    val enchantments: Map<EnchantmentType, Int>

    val isEnchanted: Boolean
        get() = !enchantments.isEmpty()

    fun withEnchantment(type: EnchantmentType, level: Int = 1): Item {
        return copy(enchantments = enchantments + (type to level))
    }

    fun withEnchantmentRemoved(type: EnchantmentType): Item {
        return copy(enchantments = enchantments - type)
    }

    /**
     * If the underlying representation of this item is immutable.
     *
     * Just because the item isn't a [MutableItem]
     * doesn't mean it's underlying representation is immutable,
     * as it may simply be a read-only view.
     */
    val immutable: Boolean
        get() = false

    /**
     * Create a mutable copy of this item.
     *
     * Modifications to this item won't be reflected in the resulting copy,
     * and modifications to the copy won't change this object.
     */
    fun mutableCopy(): MutableItem {
        val result = this.type.createMutableItem()
        result.copyFrom(this)
        return result
    }

    /**
     * Return a thread-safe immutable copy of this Item.
     *
     * Further modifications made to this item
     * will not be reflected in the resulting snapshot.
     * If the item is already [immutable],
     * the existing object may be returned as-is.
     */
    fun snapshot() = this.copy()

    /**
     * Return an unmodifiable, read-only view of this Item.
     *
     * Further modifications made to this item
     * will not be reflected in the resulting snapshot.
     * If the item is already unmodifiable or immutable,
     * the existing object may be returned.
     */
    fun unmodifiableView(): Item
}
