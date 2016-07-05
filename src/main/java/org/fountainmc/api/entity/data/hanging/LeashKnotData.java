package org.fountainmc.api.entity.data.hanging;

import javax.annotation.Nullable;

import org.fountainmc.api.entity.data.EntityData;

public interface LeashKnotData extends HangingEntityData {

    @Nullable
    EntityData getLeashedEntity();

    @Override
    default void copyDataFrom(EntityData data) {
        HangingEntityData.super.copyDataFrom(data);
        if (data instanceof HangingEntityData) {
            // if we ever get anything to copy add it here
        }
    }
}
