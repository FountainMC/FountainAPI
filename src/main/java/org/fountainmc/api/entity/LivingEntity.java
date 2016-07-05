package org.fountainmc.api.entity;

import org.fountainmc.api.NonnullByDefault;
import org.fountainmc.api.entity.data.EntityData;
import org.fountainmc.api.entity.data.LivingEntityData;

/**
 * An entity that is alive.
 */
@NonnullByDefault
public interface LivingEntity extends Entity, LivingEntityData {

    /**
     * Damage the Entity
     *
     * @param amount amount of health to take away from the Entity
     */
    void damage(double amount);

    /**
     * Copy all of the given data to this entity.
     * <p>Doesn't copy passenger information.</p>
     *
     * @param data the data to copy from
     */
    @Override
    default void copyDataFrom(EntityData data) {
        LivingEntityData.super.copyDataFrom(data);
    }


    /**
     * Take a snapshot of this entity's data
     * <p>The resulting snapshot is thread-safe.</p>
     *
     * @return a snapshot
     */
    @Override
    LivingEntityData snapshot();
}
