package org.fountainmc.api.entity.hanging;

import javax.annotation.Nullable;

import org.fountainmc.api.Direction;
import org.fountainmc.api.entity.Entity;

/**
 * Entity handing on a wall or other object
 */
public interface HangingEntity extends Entity {

    /**
     * Get the Direction the Entity is facing
     * The Direction will be null for Entities like LeashKnots.
     * 
     * @return the Direction the Entity is facing
     */
    @Nullable
    Direction getDirection();
}
