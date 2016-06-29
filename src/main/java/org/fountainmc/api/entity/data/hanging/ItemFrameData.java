package org.fountainmc.api.entity.data.hanging;

import javax.annotation.Nullable;

import org.fountainmc.api.Material;

public interface ItemFrameData extends HangingEntityData {

    /**
     * Does the ItemFrame contain an Item?
     *
     * @return whether the ItemFrame contains an Item
     */
    default boolean containsItem() {
        return getMaterial() != null;
    }

    /**
     * Get the Material of the Item in the ItemFrame, or null if there isn't an item.
     *
     * @return the Material of the Item in the ItemFrame
     */
    @Nullable
    Material getMaterial();

}
