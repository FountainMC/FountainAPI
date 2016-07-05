package org.fountainmc.api.entity.data.hanging;

import javax.annotation.Nullable;

import org.fountainmc.api.Direction;
import org.fountainmc.api.entity.data.EntityData;

public interface HangingEntityData extends EntityData {

    /**
     * Get the Direction the Entity is facing
     * The Direction will be null for Entities like LeashKnots.
     *
     * @return the Direction the Entity is facing
     */
    @Nullable
    Direction getDirection();

    @Override
    default void copyDataFrom(EntityData data) {
        EntityData.super.copyDataFrom(data);
        if (data instanceof HangingEntityData) {
            // if we ever get anything to copy add it here
        }
    }
}
