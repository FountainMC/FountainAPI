package org.fountainmc.api.entity;

import java.util.UUID;
import org.fountainmc.api.command.CommandSender;

public interface Player extends EntityLiving, CommandSender {

    String getName();
    
    UUID getUUID();
    
}
