package org.fountainmc.api.entity.data.hanging;

import javax.annotation.Nullable;

import org.fountainmc.api.Material;
import org.fountainmc.api.entity.data.EntityData;

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

    /**
     * Set the Material of the Item in the ItemFrame.
     * <p>Setting it to null empties the item frame.</p>
     *
     * @param material the Material to set the Item in the ItemFrame to
     */
    void setMaterial(@Nullable Material material);

    @Override
    default void copyDataFrom(EntityData data) {
        HangingEntityData.super.copyDataFrom(data);
        if (data instanceof ItemFrameData) {
            this.setMaterial(((ItemFrameData) data).getMaterial());
        }
    }
}
