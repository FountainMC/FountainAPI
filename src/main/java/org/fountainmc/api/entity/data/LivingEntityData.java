package org.fountainmc.api.entity.data;

import javax.annotation.Nonnull;

import org.fountainmc.api.entity.EntityType;
import org.fountainmc.api.entity.LivingEntity;

/**
 * The data of a {@link org.fountainmc.api.entity.LivingEntity}
 */
public interface LivingEntityData extends EntityData {

    /**
     * Get the health of the Entity
     *
     * @return health of the Entity
     */
    double getHealth();

    /**
     * Get the maximum health this entity can have.
     *
     * @return the maximum health
     */
    double getMaxHealth();


    @Override
    @Nonnull
    EntityType<? extends LivingEntity> getEntityType();
}
