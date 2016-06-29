package org.fountainmc.api.entity.data;

import org.fountainmc.api.NonnullByDefault;

@NonnullByDefault
public interface MutableLivingEntityData extends MutableEntityData, LivingEntityData {

    /**
     * Set the health of the Entity
     *
     * @param health amount of health to set the Entity to
     */
    void setHealth(double health);


    /**
     * Set the new maximum health this entity can have.
     *
     * @param maxHealth the new maximum health
     */
    void setMaxHealth(double maxHealth);

    @Override
    default void copyDataFrom(EntityData data) {
        MutableEntityData.super.copyDataFrom(data);
        if (data instanceof LivingEntityData) {
            this.setHealth(((LivingEntityData) data).getHealth());
            this.setMaxHealth(((LivingEntityData) data).getMaxHealth());
        }
    }
}
