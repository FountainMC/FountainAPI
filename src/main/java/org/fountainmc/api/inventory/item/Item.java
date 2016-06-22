package org.fountainmc.api.inventory.item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import org.fountainmc.api.Material;
import org.fountainmc.api.Server;
import org.fountainmc.api.enchantments.EnchantmentType;

import static com.google.common.base.Preconditions.*;

/**
 * A immutable stack of items in a player's inventory
 */
@ParametersAreNonnullByDefault
public interface Item {
    Server getServer();

    Material getType();

    @Deprecated
    short getRawData();

    @SuppressWarnings("deprecation") // I have to use rawData to implement rawData -_-
    @Deprecated
    default Item withRawData(short rawData) {
        return asBuilder().rawData(rawData).build();
    }

    int getAmount();

    default Item withAmount(int amount) {
        return asBuilder().amount(amount).build();
    }

    boolean isUnbreakable();

    default Item withUnbreakable(boolean unbreakable) {
        return asBuilder().unbreakable(unbreakable).build();
    }

    @Nullable
    String getDisplayName();

    default Item withDisplayName(@Nullable String displayName) {
        return asBuilder().displayName(displayName).build();
    }

    default boolean hasDisplayName() {
        return getDisplayName() != null;
    }

    @Nonnull
    ImmutableList<String> getLore();

    default Item withLore(List<String> lore) {
        return asBuilder().lore(lore).build();
    }

    default boolean hasLore() {
        return !getLore().isEmpty();
    }

    // Enchantment methods

    @Nonnull
    ImmutableMap<EnchantmentType, Integer> getEnchantments();

    default Item withEnchantments(Map<EnchantmentType, Integer> enchantments) {
        return asBuilder().enchantments(enchantments).build();
    }

    default Item withEnchantment(EnchantmentType type, int level) {
        return asBuilder().withEnchantment(type, level).build();
    }

    default Item withEnchantmentRemoved(EnchantmentType type) {
        return asBuilder().withEnchantmentRemoved(type).build();
    }

    default boolean isEnchanted() {
        return !getEnchantments().isEmpty();
    }

    // Builders

    default Builder asBuilder() {
        return builder(getServer(), getType()).copyFrom(this);
    }

    static Builder builder(Server server, Material type) {
        return new Builder(server, type);
    }

    @ParametersAreNonnullByDefault
    class Builder {
        private final Server server;
        @Nonnull
        private final Material type;
        private short rawData = 0;
        private int amount = 1;
        private String displayName;
        @Nonnull
        private ImmutableList<String> lore = ImmutableList.of();
        @Nonnull
        private ImmutableMap<EnchantmentType, Integer> enchantments = ImmutableMap.of();
        private boolean unbreakable = false;

        protected Builder(Server server, Material type) {
            this.server = checkNotNull(server, "Null server");
            this.type = checkNotNull(type, "Null type");
        }

        @Deprecated
        public Builder rawData(short rawData) {
            this.rawData = rawData;
            return this;
        }

        public Builder amount(int amount) {
            checkArgument(amount >= 0, "Negative amount %s", amount);
            this.amount = amount;
            return this;
        }

        public Builder displayName(@Nullable String displayName) {
            this.displayName = displayName;
            return this;
        }

        public Builder lore(List<String> lore) {
            this.lore = ImmutableList.copyOf(checkNotNull(lore, "Null lore list"));
            return this;
        }

        public Builder enchantments(Map<EnchantmentType, Integer> enchants) {
            this.enchantments = ImmutableMap.copyOf(checkNotNull(enchants, "Null enchantment map"));
            return this;
        }

        public Builder withEnchantment(EnchantmentType type, int level) {
            int minLevel = checkNotNull(type, "Null type").getMinLevel();
            checkArgument(level >= minLevel, "Level %s is lower than %'s minimum level %s", level, type, minLevel);
            Map<EnchantmentType, Integer> enchantments = new HashMap<>(this.enchantments);
            enchantments.put(type, level);
            return enchantments(enchantments);
        }

        public Builder withEnchantmentRemoved(EnchantmentType type) {
            if (!enchantments.containsKey(type)) return this;
            Map<EnchantmentType, Integer> enchantments = new HashMap<>(this.enchantments);
            enchantments.remove(type);
            return enchantments(enchantments);
        }

        public Builder unbreakable(boolean unbreakable) {
            this.unbreakable = unbreakable;
            return this;
        }

        @OverridingMethodsMustInvokeSuper
        public Builder copyFrom(Item item) {
            Material itemType = item.getType();
            checkArgument(itemType.equals(this.type), "Item's type %s doesn't equal the builder's type %s", itemType, this.type);
            //noinspection deprecation - we have to access the raw data to copy properly
            this.rawData = item.getRawData();
            this.amount = item.getAmount();
            this.displayName = item.getDisplayName();
            this.lore = item.getLore();
            this.enchantments = item.getEnchantments();
            this.unbreakable = item.isUnbreakable();
            return this;
        }

        public Item build() {
            return server.getItemFactory().createItem(type, rawData, amount, displayName, lore, enchantments, unbreakable);
        }
    }
}
