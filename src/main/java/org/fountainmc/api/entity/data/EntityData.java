package org.fountainmc.api.entity.data;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;

import org.fountainmc.api.NonnullByDefault;
import org.fountainmc.api.Server;
import org.fountainmc.api.entity.EntityType;

import static com.google.common.base.Preconditions.*;

/**
 * The data for an entity, which isn't necessarily spawned in a world.
 * <p>
 *     Notes:
 *     <ul>
 *         <li>this doesn't include the entity's location as that only makes sense for an entity that is in the world.</li>
 *         <li>this doesn't include information about the riding entity because nbt doesn't contain that</li>
 *         <li>you can't modify the passenger because it'd be extremely difficult and buggy to implement</li>
 *     </ul>
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
     * Set the Pitch of the Entity.
     *
     * @param pitch Pitch to set the Entity to
     */
    void setPitch(float pitch);

    /**
     * Get the Yaw of the Entity.
     *
     * @return the yaw
     */
    float getYaw();

    /**
     * Set the Yaw of the Entity.
     *
     * @param yaw Yaw to set the Entity to
     */
    void setYaw(float yaw);

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

    void setTicksOnFire(int ticksOnFire);

    default boolean isOnFire() {
        return getTicksOnFire() >= 0;
    }

    boolean isImmuneToFire();

    void setImmuneToFire(boolean immune);

    /**
     * The type of entity this data is intended for
     *
     * @return the type
     */
    @Nonnull
    EntityType<?> getEntityType();

    /**
     * Copy all of the given data to this object, making them effectively equal.
     *
     * @param data the data to copy from
     */
    default void copyDataFrom(EntityData data) {
        checkNotNull(data, "Null data");
        this.setPitch(data.getPitch());
        this.setYaw(data.getYaw());
        this.setTicksOnFire(data.getTicksOnFire());
        this.setImmuneToFire(data.isImmuneToFire());
    }
}
