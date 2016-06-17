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
    float getHealth();

    /**
     * Set the health of the Entity
     *
     * @param health amount of health to set the Entity to
     */
    void setHealth(float health);

    /**
     * Get the maximum health this entity can have.
     *
     * @return the maximum health
     */
    float getMaxHealth();

    /**
     * Set the new maximum health this entity can have.
     *
     * @param maxHealth the new maximum health
     */
    void setMaxHealth(float maxHealth);

    /**
     * Damage the Entity
     *
     * @param amount amount of health to take away from the Entity
     */
    void damage(double amount);

    /**
     * Is the Entity alive?
     *
     * @return if the Entity is alive or not
     */
    default boolean isAlive() {
        return getHealth() > 0;
    }

    @Override
    EntityType<? extends LivingEntity> getEntityType();

}
