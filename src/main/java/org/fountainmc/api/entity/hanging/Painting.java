package org.fountainmc.api.entity.hanging;

import org.fountainmc.api.NonnullByDefault;
import org.fountainmc.api.entity.data.EntityData;
import org.fountainmc.api.entity.data.hanging.PaintingData;

/**
 * A painting that is hanging on a wall.
 */
@NonnullByDefault
public interface Painting extends PaintingData, HangingEntity {

    /**
     * Copy all of the given data to this entity.
     * <p>Doesn't copy passenger information.</p>
     *
     * @param data the data to copy from
     */
    @Override
    default void copyDataFrom(EntityData data) {
        PaintingData.super.copyDataFrom(data);
    }

    /**
     * Take a snapshot of this entity's data
     * <p>The resulting snapshot is thread-safe.</p>
     *
     * @return a snapshot
     */
    @Override
    PaintingData snapshot();
}
