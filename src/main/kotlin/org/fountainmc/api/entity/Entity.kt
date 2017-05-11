package org.fountainmc.api.entity

import com.google.common.collect.ImmutableSet
import org.fountainmc.api.Server
import org.fountainmc.api.entity.data.EntityData
import org.fountainmc.api.world.Location
import org.fountainmc.api.world.World

/**
 * Base interface for every Entity.
 */
interface Entity : EntityData {

    override val server: Server

    /**
     * The Location of the Entity within the World
     *
     * @return the Location of the Entity
     */
    val location: Location

    /**
     * If the Entity is on the ground.
     */
    val isOnGround: Boolean

    val world: World
        get() = location.world

    /**
     * Teleport the entity to the specified location
     *
     * @param destination The destination location to send the Entity to
     */
    fun teleport(destination: Location)

    /**
     * The primary entity riding on top of this Entity.
     */
    override var primaryPassenger: Entity?

    /**
     * The passengers riding on top of this Entity.
     */
    override val passengers: List<Entity>

    /**
     * Set the entity's passengers
     *
     * @param entities the passengers to set
     * @param force if passenger limits should be forcibly bypassed
     * @throws IllegalArgumentException if not force-adding and this would cause more passengers then the entity is allowed to have
     */
    fun setPassengers(entities: List<Entity>, force: Boolean) {
        require(force || entities.size <= maximumPassengers) {
            "${entities.size} entities were given, but only $maximumPassengers are allowed!"
        }
        if (!passengers.isEmpty()) this.ejectAll()
        for (entity in entities) {
            addPassenger(entity, force)
        }
    }

    /**
     * Add a passenger to this entity
     *
     * @param entity the passenger to add
     * @param force whether to bypass passenger limits
     * @return if successfully added
     * @throws IllegalStateException If the entity would have too many passengers
     */
    fun addPassenger(entity: Entity, force: Boolean = false): Boolean {
        return entity.startRiding(this, force)
    }

    /**
     * Start riding the specified vehicle
     *
     * @param vehicle the vehicle to start riding
     * @param force whether to bypass passenger limits
     * @return if successfully added
     * @throws IllegalStateException If the entity would have too many passengers
     */
    fun startRiding(vehicle: Entity, force: Boolean = false): Boolean

    /**
     * Ejects all this entities passengers
     */
    fun ejectAll()

    /**
     * Ejects the specified passenger from the entity
     *
     * @param passenger the passenger to eject
     * @throws IllegalArgumentException if the give entity isn't a passenger of this entity.
     */
    fun ejectPassenger(passenger: Entity)

    fun ejectPrimaryPassenger() {
        val primaryPassenger = primaryPassenger
        if (primaryPassenger != null) ejectPassenger(primaryPassenger)
    }

    val maximumPassengers: Int

    /**
     * Dismount this entity's vehicle if the entity is riding one
     */
    fun dismountVehicle()

    /**
     * If the entity is riding another entity
     */
    val isRiding: Boolean
        get() = vehicle != null

    /**
     * The vehicle this entity is riding on, or null if none.
     */
    val vehicle: Entity?

    /**
     * The bottommost vehicle in the stack of entity
     *
     * Equivalent to `vehicle?.bottomVehicle ?: vehicle`
     */
    val bottomVehicle: Entity?
        get() {
            var vehicle = this.vehicle ?: return null
            while (vehicle.vehicle != null) {
                vehicle = vehicle.vehicle!!
            }
            return vehicle
        }

    /**
     * Get any nearby Entities within a certain distance.
     *
     * @param distance max distance to search for entities
     * @return a Collection of nearby Entities within the distance
     */
    fun getNearbyEntities(distance: Double): ImmutableSet<Entity>

    /**
     * If the entity is 'dead'.
     *
     * An entity is only considered dead once it has been invalidated by the server,
     * and not just once it reaches 0 health.
     */
    val isDead: Boolean

    /**
     * Take a snapshot of this entity's data
     *
     * The resulting snapshot is thread-safe.
     * @return a new snapshot
     */
    fun snapshot(): EntityData

    /**
     * Copy all of the mutable properties from the given data into this object.
     *
     * @param data the data to copy from
     */
    override fun copyDataFrom(data: EntityData) {
        super.copyDataFrom(data)
    }

    /**
     * The type of this entity
     */
    override val entityType: EntityType<*>

}
