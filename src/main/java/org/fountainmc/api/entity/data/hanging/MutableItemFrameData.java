package org.fountainmc.api.entity.data.hanging;

import javax.annotation.Nullable;

import org.fountainmc.api.Material;
import org.fountainmc.api.NonnullByDefault;
import org.fountainmc.api.entity.data.EntityData;
import org.fountainmc.api.entity.data.MutableEntityData;

@NonnullByDefault
public interface MutableItemFrameData extends MutableHangingEntityData, ItemFrameData {

    /**
     * Set the Material of the Item in the ItemFrame.
     * <p>Setting it to null empties the item frame.</p>
     *
     * @param material the Material to set the Item in the ItemFrame to
     */
    void setMaterial(@Nullable Material material);

    @Override
    default void copyDataFrom(EntityData data) {
        MutableHangingEntityData.super.copyDataFrom(data);
        if (data instanceof ItemFrameData) {
            this.setMaterial(((ItemFrameData) data).getMaterial());
        }
    }
}
