package org.fountainmc.api.entity;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;

import org.fountainmc.api.Server;
import org.fountainmc.api.world.Location;
import org.fountainmc.api.world.World;

import static com.google.common.base.Preconditions.*;

/**
 * Base interface for every Entity.
 */
@ParametersAreNonnullByDefault
public interface Entity {

    Server getServer();

    /**
     * Get the Location of the Entity within the World
     *
     * @return the Location of the Entity
     */
    @Nonnull
    Location getLocation();

    @Nonnull
    default World getWorld() {
        return getLocation().getWorld();
    }

    /**
     * Teleport the entity to the specified location
     *
     * @param destination The destination location to send the Entity to
     */
    void teleport(Location destination);

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

    default boolean hasPassengers() {
        return !getPassengers().isEmpty();
    }

    @Nullable
    Entity getPrimaryPassenger();

    /**
     * Set the primary entity riding on top of this Entity.
     *
     * @param passenger the new passenger
     */
    void setPrimaryPassenger(Entity passenger);

    /**
     * Get the entities riding on top of this Entity.
     * Returns null if there is no Entity riding on top of this Entity.
     *
     * @return the passengers
     */
    @Nonnull
    ImmutableList<Entity> getPassengers();

    /**
     * Get the entity's passengers
     *
     * @param entities the passengers to set
     * @throws IllegalArgumentException if this would cause more passengers then the entity is allowed to have
     */
    default void setPassengers(ImmutableList<Entity> entities) {
        setPassengers(entities, false);
    }

    /**
     * Set the entity's passengers
     *
     * @param entities the passengers to set
     * @param force weather to bypass passenger limits
     * @throws IllegalStateException if not force-adding and this would cause more passengers then the entity is allowed to have
     */
    default void setPassengers(ImmutableList<Entity> entities, boolean force) {
        if (checkNotNull(entities, "Null entity list").size() > getMaximumPassengers() & !force) {
            throw new IllegalArgumentException(entities.size() + " entities were given, but only " + getMaximumPassengers() + " are allowed!");
        }
        if (!getPassengers().isEmpty()) this.ejectAll();
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
        }
    }

    /**
     * Add a passenger to this entity
     *
     * @param entity the passenger to add
     * @return if successfully added
     * @throws IllegalStateException if this would cause more passengers then the entity is allowed to have
     */
    default boolean addPassenger(Entity entity) {
        return this.addPassenger(entity, false);
    }

    /**
     * Add a passenger to this entity
     *
     * @param entity the passenger to add
     * @param force  weather to bypass passenger limits
     * @return if successfully added
     * @throws IllegalStateException if not force-adding and this would cause more passengers then the entity is allowed to have
     */
    boolean addPassenger(Entity entity, boolean force);

    /**
     * Ejects all this entities passengers
     */
    void ejectAll();

    /**
     * Ejects the specified passenger from the entity
     *
     * @param passenger the passenger to eject
     * @throws IllegalArgumentException if the give entity isn't a passenger of this entity.
     */
    void ejectPassenger(Entity passenger);

    default void ejectPrimaryPassenger() {
        Entity primaryPassenger = getPrimaryPassenger();
        if (primaryPassenger != null) ejectPassenger(primaryPassenger);
    }

    @Nonnegative
    int getMaximumPassengers();


    /**
     * Dismount this entity's vehicle if the entity is riding one
     */
    void dismountVehicle();

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
     * @throws IllegalStateException if this would cause more passengers then the vehicle is allowed to have
     */
    default void setVehicle(Entity vehicle) {
        checkNotNull(vehicle, "Null vehicle").addPassenger(this);
    }

    /**
     * Leave this entity's vehicle
     */
    void leaveVehicle();

    /**
     * Return if the entity is riding another entity
     */
    default boolean isRiding() {
        return getVehicle() != null;
    }

    /**
     * Get the bottom vehicle. Equivalent to:
     * <pre>
     * Entity entity = ...
     * Entity bottom = null;
     * while(bottom.getVehicle() != null) bottom = bottom.getVehicle();
     * </pre>
     *
     * @return the bottom entity
     */
    @Nullable
    default Entity getBottomVehicle() {
        Entity entity = this;
        Entity vehicle = null;
        while ((entity = entity.getVehicle()) != null) {
            vehicle = entity;
        }
        return vehicle;
    }

    /**
     * Get any nearby Entities within a certain distance.
     *
     * @param distance max distance to search for entities
     * @return a Collection of nearby Entities within the distance
     */
    ImmutableCollection<Entity> getNearbyEntities(double distance);

    @Nonnull
    EntityType<?> getEntityType();

}
