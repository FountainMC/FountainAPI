package org.fountainmc.api.enchantments;

public interface EnchantmentType {
    String getName();

    int getId();

    default int getMinLevel() {
        return 1;
    }

    int getMaxLevel();
}
