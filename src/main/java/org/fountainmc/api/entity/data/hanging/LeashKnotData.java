package org.fountainmc.api.entity.data.hanging;

import javax.annotation.Nullable;

import org.fountainmc.api.NonnullByDefault;
import org.fountainmc.api.entity.data.EntityData;

@NonnullByDefault
public interface LeashKnotData extends HangingEntityData {

    @Nullable
    EntityData getLeashedEntity();

    @Override
    default void copyDataFrom(EntityData data) {
        HangingEntityData.super.copyDataFrom(data);
    }
}
