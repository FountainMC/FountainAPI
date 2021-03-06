package org.fountainmc.api.world.block.plants

interface Flower : Plant {

    val type: FlowerType

    enum class FlowerType {
        POPPY,
        BLUE_ORCHARD,
        ALLIUM,
        AZURE_BLUET,
        RED_TULIP,
        ORANGE_TULIP,
        WHITE_TULIP,
        PINK_TULIP,
        OXEYE_DAISY
    }
}
