package org.fountainmc.api.entity.data.hanging;

import org.fountainmc.api.NonnullByDefault;
import org.fountainmc.api.entity.data.EntityData;
import org.fountainmc.api.entity.data.MutableEntityData;

@NonnullByDefault
public interface MutableHangingEntityData extends MutableEntityData, HangingEntityData {
    @Override
    default void copyDataFrom(EntityData data) {
        MutableEntityData.super.copyDataFrom(data);
        if (data instanceof HangingEntityData) {
            // if we ever get anything to copy add it here
        }
    }
}
