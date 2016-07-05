package org.fountainmc.api.entity.data.hanging;

import javax.annotation.Nullable;

import org.fountainmc.api.NonnullByDefault;
import org.fountainmc.api.entity.data.EntityData;

@NonnullByDefault
public interface MutablePaintingData extends PaintingData, MutableHangingEntityData {

    @Override
    default void copyDataFrom(EntityData data) {
        MutableHangingEntityData.super.copyDataFrom(data);
        // if we ever get anything to copy add it here
    }
}
