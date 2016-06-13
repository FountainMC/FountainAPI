package org.fountainmc.api.entity;

import java.util.UUID;

public interface Player extends EntityLiving {

    String getName();
    
    UUID getUUID();
    
}
