package org.fountainmc.api.entity.data;

import javax.annotation.OverridingMethodsMustInvokeSuper;

import org.fountainmc.api.NonnullByDefault;

import static com.google.common.base.Preconditions.*;

/**
 * Modifiable data for an entity.
 * <p>
 * Notes:
 * <ul>
 * <li> Passenger modification is extremely limited because passengers are assumed to exist and </li>
 * </ul>
 * </p>
 *
 */
@NonnullByDefault
public interface MutableEntityData extends EntityData {

    /**
     * Set the Pitch of the Entity.
     *
     * @param pitch Pitch to set the Entity to
     */
    void setPitch(float pitch);

    /**
     * Set the Yaw of the Entity.
     *
     * @param yaw Yaw to set the Entity to
     */
    void setYaw(float yaw);

    void setTicksOnFire(int ticksOnFire);

    void setImmuneToFire(boolean immune);

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
