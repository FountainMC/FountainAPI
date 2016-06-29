package org.fountainmc.api.entity.data.hanging;

import javax.annotation.Nullable;

import org.fountainmc.api.NonnullByDefault;
import org.fountainmc.api.entity.data.EntityData;

@NonnullByDefault
public interface MutableLeashKnotData extends LeashKnotData, MutableHangingEntityData {

    @Nullable
    EntityData getLeashedEntity();

    @Override
    default void copyDataFrom(EntityData data) {
        MutableHangingEntityData.super.copyDataFrom(data);
        if (data instanceof HangingEntityData) {
            // if we ever get anything to copy add it here
        }
    }
}
