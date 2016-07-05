package org.fountainmc.api.entity.data;

import javax.annotation.Nonnull;

import org.fountainmc.api.NonnullByDefault;
import org.fountainmc.api.entity.EntityType;
import org.fountainmc.api.entity.LivingEntity;

/**
 * The data of a {@link org.fountainmc.api.entity.LivingEntity}
 */
@NonnullByDefault
public interface LivingEntityData extends EntityData {

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

    @Override
    @Nonnull
    EntityType<? extends LivingEntity> getEntityType();

    @Override
    default void copyDataFrom(EntityData data) {
        EntityData.super.copyDataFrom(data);
        if (data instanceof LivingEntityData) {
            this.setHealth(((LivingEntityData) data).getHealth());
            this.setMaxHealth(((LivingEntityData) data).getMaxHealth());
        }
    }

}
