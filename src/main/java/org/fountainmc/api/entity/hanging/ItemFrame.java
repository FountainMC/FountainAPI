package org.fountainmc.api.entity.hanging;

import org.fountainmc.api.Material;

/**
 * An ItemFrame that is hanging on a wall
 */
public interface ItemFrame extends HangingEntity {

    /**
     * Does the ItemFrame contain an Item?
     * 
     * @return whether the ItemFrame contains an Item
     */
    boolean containsItem();

    /**
     * Get the Material of the Item in the ItemFrame.
     * 
     * @return the Material of the Item in the ItemFrame
     */
    Material getMaterial();

    /**
     * Set the Material of the Item in the ItemFrame.
     * 
     * @param material the Material to set the Item in the ItemFrame to
     */
    void setMaterial(Material material);
}
