package org.fountainmc.api.entity;

import java.util.Collection;
import javax.annotation.Nullable;

import org.fountainmc.api.world.Location;

/**
 * Base interface for every Entity.
 */
public interface Entity {

    /**
     * Get the Location of the Entity within the World
     *
     * @return the Location of the Entity
     */
    Location getLocation();

    /**
     * Set the Location of the Entity.
     *
     * @param loc The Location to send the Entity
     */
    void setLocation(Location loc);

    /**
     * Get the Pitch of the Entity.
     *
     * @return the pitch
     */
    float getPitch();

    /**
     * Set the Pitch of the Entity.
     *
     * @param pitch Pitch to set the Entity to
     */
    void setPitch(float pitch);

    /**
     * Get the Yaw of the Entity.
     *
     * @return the yaw
     */
    float getYaw();

    /**
     * Set the Yaw of the Entity.
     *
     * @param yaw Yaw to set the Entity to
     */
    void setYaw(float yaw);

    /**
     * Check if the Entity is on the ground.
     *
     * @return Whether the Entity is on the ground
     */
    boolean isOnGround();

    /**
     * Get the Entity riding on top of this Entity.
     * Returns null if there is no Entity riding on top of this Entity.
     *
     * @return the passenger
     */
    @Nullable
    Entity getPassenger();

    /**
     * Set the Entity riding on top of this Entity.
     * If there is already a passenger riding on this Entity, it will be knocked off.
     *
     * @param passenger
     */
    void setPassenger(@Nullable Entity passenger);

    /**
     * Get the Entity this Entity is riding on.
     *
     * @return the Entity this Entity is riding on
     */
    @Nullable
    Entity getVehicle();

    /**
     * Set the Entity that this Entity will ride.
     *
     * @param vehicle The Entity that this Entity will ride
     */
    void setVehicle(@Nullable Entity vehicle);

    /**
     * Get the bottom vehicle. Equivalent to:
     * <p>
     * <pre>
     * Entity entity = ...
     * Entity bottom = null;
     * while(bottom.getVehicle() != null) bottom = bottom.getVehicle();
     * </pre>
     *
     * @return the bottom Entity in the stack
     */
    Entity getBottomVehicle();

    /**
     * Get any nearby Entities within a certain distance.
     *
     * @param distance max distance to search for entities
     * @return a Collection of nearby Entities within the distance
     */
    Collection<Entity> getNearbyEntities(double distance);

    /**
     * Damage the Entity
     *
     * @param amount amount of health to take away from the Entity
     */
    void damage(int amount);

    public EntityType<?> getEntityType();
}
