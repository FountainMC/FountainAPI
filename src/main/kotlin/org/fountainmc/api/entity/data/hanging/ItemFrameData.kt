package org.fountainmc.api.entity.data.hanging

import org.fountainmc.api.Material
import org.fountainmc.api.entity.data.EntityData

interface ItemFrameData : HangingEntityData {

    /**
     * If the ItemFrame contains an Item
     */
    val containsItem
        get() = material != null

    /**
     * The Material of the Item in the ItemFrame, or null if there isn't an item.
     */
    var material: Material<*>?

    override fun copyDataFrom(data: EntityData) {
        super.copyDataFrom(data)
        if (data is ItemFrameData) {
            this.material = data.material
        }
    }
}
