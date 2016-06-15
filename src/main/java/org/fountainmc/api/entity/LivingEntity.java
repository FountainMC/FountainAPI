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
    double getHealth();

    /**
     * Set the health of the Entity
     *
     * @param health amount of health to set the Entity to
     */
    void setHealth(double health);

    /**
     * Get the maximum health this entity can have.
     *
     * @return the maximum health
     */
    public double getMaxHealth();

    /**
     * Set the new maximum health this entity can have.
     *
     * @param maxHealth the new maximum health
     */
    public void setMaxHealth(int maxHealth);

    /**
     * Is the Entity alive?
     *
     * @return if the Entity is alive or not
     */
    default boolean isAlive() {
        return getHealth() > 0;
    }

    @Override
    public EntityType<? extends LivingEntity> getEntityType();
}
