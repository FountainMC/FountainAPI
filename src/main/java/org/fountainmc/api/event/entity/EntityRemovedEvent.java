package org.fountainmc.api.event.entity;

import org.fountainmc.api.entity.Entity;
import org.fountainmc.api.world.Location;

public class EntityRemovedEvent extends EntitySpawnEvent {

    public EntityRemovedEvent(Entity entity, Location location) {
        super(entity, location);
    }

}
