package org.fountainmc.api.entity.data;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;

import org.fountainmc.api.NonnullByDefault;
import org.fountainmc.api.Server;
import org.fountainmc.api.entity.EntityType;

/**
 * The data for an entity, which isn't necessarily spawned in a world.
 * <p>Notes:
 * <ul>
 * <li>this doesn't include the entity's location as that only makes sense for an entity that is in the world.</li>
 * <li>this doesn't include information about the riding entity because nbt doesn't contain that</li>
 * </ul>
 * </p>
 */
@NonnullByDefault
public interface EntityData {

    /**
     * Get the server that created this data
     *
     * @return the server that created this data
     */
    Server getServer();

    /**
     * Get the Pitch of the Entity.
     *
     * @return the pitch
     */
    float getPitch();

    /**
     * Get the Yaw of the Entity.
     *
     * @return the yaw
     */
    float getYaw();

    /**
     * Get the primary passenger riding on top of this entity
     *
     * @return the primary passenger
     */
    @Nullable
    EntityData getPrimaryPassenger();

    default boolean hasPassengers() {
        return !getPassengers().isEmpty();
    }

    /**
     * Get the entities riding on top of this Entity.
     * Returns null if there is no Entity riding on top of this Entity.
     *
     * @return the passengers
     */
    ImmutableList<? extends EntityData> getPassengers();

    int getTicksOnFire();

    default boolean isOnFire() {
        return getTicksOnFire() >= 0;
    }

    boolean isImmuneToFire();

    /**
     * The type of entity this data is intended for
     */
    @Nonnull
    EntityType<?> getEntityType();

}
