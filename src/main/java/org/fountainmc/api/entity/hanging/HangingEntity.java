package org.fountainmc.api.entity.hanging;

import org.fountainmc.api.NonnullByDefault;
import org.fountainmc.api.entity.Entity;
import org.fountainmc.api.entity.data.EntityData;
import org.fountainmc.api.entity.data.hanging.HangingEntityData;
import org.fountainmc.api.entity.data.hanging.MutableHangingEntityData;

/**
 * An entity handing on a wall or other object.
 */
@NonnullByDefault
public interface HangingEntity extends MutableHangingEntityData, Entity {
    /**
     * Copy all of the given data to this entity.
     * <p>Doesn't copy passenger information.</p>
     *
     * @param data the data to copy from
     */
    @Override
    default void copyDataFrom(EntityData data) {
        MutableHangingEntityData.super.copyDataFrom(data);
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
