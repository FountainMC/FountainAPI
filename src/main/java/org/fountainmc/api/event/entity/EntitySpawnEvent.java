package org.fountainmc.api.event.entity;

import org.fountainmc.api.event.Cancellable;
import org.fountainmc.api.world.Location;

public interface EntitySpawnEvent extends EntityEvent, Cancellable {

    Location getLocation();

}
