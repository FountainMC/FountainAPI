package org.fountainmc.api.entity;

import javax.annotation.Nonnull;

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
    double getMaxHealth();

    /**
     * Set the new maximum health this entity can have.
     *
     * @param maxHealth the new maximum health
     */
    void setMaxHealth(double maxHealth);

    /**
     * Damage the Entity
     *
     * @param amount amount of health to take away from the Entity
     */
    void damage(double amount);

    @Override
    @Nonnull
    EntityType<? extends LivingEntity> getEntityType();
}
