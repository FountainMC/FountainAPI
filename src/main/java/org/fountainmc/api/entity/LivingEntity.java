package org.fountainmc.api.entity;

/**
 * Entity with health
 */
public interface LivingEntity extends Entity {

    /**
     * Get the health of the Entity
     * 
     * @return health of the Entity
     */
    int getHealth();

    /**
     * Set the health of the Entity
     * 
     * @param health amount of health to set the Entity to
     */
    void setHealth(int health);

    /**
     * Is the Entity alive?
     * 
     * @return if the Entity is alive or not
     */
    default boolean isAlive() {
        return getHealth() > 0;
    }
}
