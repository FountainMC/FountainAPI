package org.fountainmc.api.world.block.plants;

public interface Flower extends Plant {

    public FlowerType getType();

    public static enum FlowerType {
        POPPY,
        BLUE_ORCHARD,
        ALLIUM,
        AZURE_BLUET,
        RED_TULIP,
        ORANGE_TULIP,
        WHITE_TULIP,
        PINK_TULIP,
        OXEYE_DAISY;
    }
}
