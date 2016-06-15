package org.fountainmc.api.entity.hanging;

import javax.annotation.Nullable;

import org.fountainmc.api.entity.Entity;

public interface LeashKnot extends HangingEntity {

    @Nullable Entity getLeashedEntity();
}
