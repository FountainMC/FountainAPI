package org.fountainmc.api.event.entity;

import javax.annotation.Nonnull;

import org.fountainmc.api.entity.Entity;
import org.fountainmc.api.event.Event;

public interface EntityEvent extends Event {

    @Nonnull
    Entity getEntity();

}
