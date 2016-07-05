package org.fountainmc.api.entity.hanging;

import javax.annotation.Nullable;

import org.fountainmc.api.NonnullByDefault;
import org.fountainmc.api.entity.Entity;
import org.fountainmc.api.entity.data.EntityData;
import org.fountainmc.api.entity.data.hanging.LeashKnotData;

@NonnullByDefault
public interface LeashKnot extends HangingEntity, LeashKnotData {

    @Nullable
    @Override
    Entity getLeashedEntity();

    /**
     * Copy all of the given data to this entity.
     * <p>Doesn't copy passenger information.</p>
     *
     * @param data the data to copy from
     */
    @Override
    default void copyDataFrom(EntityData data) {
        LeashKnotData.super.copyDataFrom(data);
    }


    /**
     * Take a snapshot of this entity's data
     * <p>The resulting snapshot is thread-safe.</p>
     *
     * @return a snapshot
     */
    @Override
    LeashKnotData snapshot();

}
