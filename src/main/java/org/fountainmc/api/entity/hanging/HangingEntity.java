package org.fountainmc.api.entity.hanging;

import org.fountainmc.api.NonnullByDefault;
import org.fountainmc.api.entity.Entity;
import org.fountainmc.api.entity.data.EntityData;
import org.fountainmc.api.entity.data.hanging.HangingEntityData;

/**
 * An entity handing on a wall or other object.
 */
@NonnullByDefault
public interface HangingEntity extends HangingEntityData, Entity {
    /**
     * Copy all of the given data to this entity.
     * <p>Doesn't copy passenger information.</p>
     *
     * @param data the data to copy from
     */
    @Override
    default void copyDataFrom(EntityData data) {
        HangingEntityData.super.copyDataFrom(data);
    }

    /**
     * Take a snapshot of this entity's data
     * <p>The resulting snapshot is thread-safe.</p>
     *
     * @return a snapshot
     */
    @Override
    HangingEntityData snapshot();

}
