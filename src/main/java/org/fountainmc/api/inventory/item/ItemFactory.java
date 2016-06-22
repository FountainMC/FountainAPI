package org.fountainmc.api.inventory.item;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import org.fountainmc.api.Material;
import org.fountainmc.api.enchantments.EnchantmentType;

@ParametersAreNonnullByDefault
public interface ItemFactory {
    Item createItem(Material type, short rawData, int amount, @Nullable String displayName, ImmutableList<String> lore, ImmutableMap<EnchantmentType, Integer> enchantments, boolean unbreakable);
}
