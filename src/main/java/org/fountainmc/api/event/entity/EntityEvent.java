package org.fountainmc.api.event.entity;

import org.fountainmc.api.entity.Entity;
import org.fountainmc.api.event.Event;

public interface EntityEvent extends Event {

    Entity getEntity();

}
