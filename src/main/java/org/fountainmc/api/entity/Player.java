package org.fountainmc.api.entity;

import org.fountainmc.api.command.Sender;

import java.util.UUID;

public interface Player extends EntityLiving, Sender {

    String getName();
    
    UUID getUUID();
    
}
