package org.fountainmc.api.event.entity;

import org.fountainmc.api.entity.Entity;
import org.fountainmc.api.event.CancellableBase;
import org.fountainmc.api.world.Location;

public class EntitySpawnEvent extends CancellableBase {

    private final Entity entity;
    private final Location location;

    public EntitySpawnEvent(Entity entity, Location location) {
        this.entity = entity;
        this.location = location;
    }

    public Entity getEntity() {
        return entity;
    }

    public Location getLocation() {
        return location;
    }

}
