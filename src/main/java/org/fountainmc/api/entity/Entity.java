package org.fountainmc.api.entity;

import org.fountainmc.api.world.Location;

public interface Entity {

    Location getLocation();
    
    boolean isOnGround();

}
