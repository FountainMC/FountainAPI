package org.fountainmc.api.inventory.item

import org.fountainmc.api.enchantments.EnchantmentType
import org.fountainmc.api.world.block.BlockState

/**
 * Return an immutable copy of this item with the specified values.
 */
fun Item.copy(
        rawData: Short = @Suppress("DEPRECATION") this.rawData,
        amount: Int = this.amount,
        isUnbreakable: Boolean = this.isUnbreakable,
        displayName: String? = this.displayName,
        lore: List<String> = this.lore,
        enchantments: Map<EnchantmentType, Int> = this.enchantments
): Item {
    return server.itemFactory.createItem(
            type = this.type,
            rawData = rawData,
            amount = amount,
            isUnbreakable = isUnbreakable,
            displayName = displayName,
            enchantments = enchantments,
            lore = lore
    )
}

/**
 * Return an immutable copy of this item with the specified values.
 */
fun <S: BlockState> BlockItem<S>.copy(
        itemData: Item = (this as Item).copy(),
        blockState: S = type.defaultState
): BlockItem<S> {
    return server.itemFactory.createBlockItem(
            type = this.type,
            itemData = itemData,
            blockState = blockState
    )
}

