package xyz.jadonfowler.fountain.api.world.block;

import xyz.jadonfowler.fountain.api.world.Location;

public interface Block {

    int getId();

    int getX();

    int getY();

    int getZ();
    
    Location getLocation();
}