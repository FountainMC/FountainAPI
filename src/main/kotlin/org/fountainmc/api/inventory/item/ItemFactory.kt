package org.fountainmc.api.inventory.item

import org.fountainmc.api.BlockType
import org.fountainmc.api.Material
import org.fountainmc.api.enchantments.EnchantmentType
import org.fountainmc.api.world.block.BlockState
import javax.annotation.ParametersAreNonnullByDefault

@ParametersAreNonnullByDefault
interface ItemFactory {
    /**
     * Create an immutable item with the specified values.
     */
    fun <T: Item> createItem(
            type: Material<T>,
            rawData: Short = 0,
            amount: Int = 1,
            displayName: String? = null,
            lore: List<String> = emptyList(),
            enchantments: Map<EnchantmentType, Int> = emptyMap(),
            isUnbreakable: Boolean = false
    ): T
    fun <S: BlockState> createBlockItem(
            type: BlockType<S>,
            itemData: Item = createItem(type),
            blockState: BlockState = type.defaultState
    ): BlockItem<S>

    /**
     * Create an mutable item of the specified type,
     * with the default values.
     */
    fun createMutableItem(type: Material<*>): MutableItem
}
