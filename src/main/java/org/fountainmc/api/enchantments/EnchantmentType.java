package org.fountainmc.api.enchantments;

public interface EnchantmentType {
    public String getName();

    public int getId();

    public default int getMinLevel() {
        return 1;
    }

    public int getMaxLevel();
}
