package org.fountainmc.api.entity.data.hanging;

import javax.annotation.Nullable;

import org.fountainmc.api.entity.data.EntityData;
import org.fountainmc.api.entity.data.MutableEntityData;

public interface LeashKnotData extends HangingEntityData {

    @Nullable
    EntityData getLeashedEntity();

}
