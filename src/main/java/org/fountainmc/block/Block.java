package org.fountainmc.block;

import xyz.jadonfowler.fountain.api.world.Location;

import org.fountainmc.world.Location;

public interface Block {

    int getId();

    int getX();

    int getY();

    int getZ();
    
    Location getLocation();
}