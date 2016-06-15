package org.fountainmc.api;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.common.collect.ImmutableList;
import org.fountainmc.api.command.CommandManager;
import org.fountainmc.api.entity.Entity;
import org.fountainmc.api.entity.EntityType;
import org.fountainmc.api.plugin.PluginManager;

import static com.google.common.base.Preconditions.checkArgument;

@ParametersAreNonnullByDefault
public interface Server extends ServerInfo {

    public PluginManager getPluginManager();

    public CommandManager getCommandManager();

    public ImmutableList<String> getLaunchArguments();

    @Nonnull
    public Material getMaterial(String name);

    @Nonnull
    public default BlockType getBlockType(String name) {
        Material material = getMaterial(name);
        checkArgument(material instanceof BlockType, "%s is not a block!", name);
        return (BlockType) material;
    }

    @Nonnull
    public EntityType<?> getEntityType(String name);

    @Nonnull
    @SuppressWarnings("unchecked")
    public default <T extends Entity> EntityType<T> getEntityType(String name, Class<T> entityType) {
        EntityType<?> type = getEntityType(name);
        if (entityType.isAssignableFrom(type.getEntityClass())) {
            return (EntityType<T>) type;
        } else {
            throw new IllegalArgumentException("Entity type " + type + " is not a " + entityType.getTypeName());
        }
    }

}
