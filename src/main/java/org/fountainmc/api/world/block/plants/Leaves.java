package org.fountainmc.api.world.block.plants;

public interface Leaves extends Plant {

    /**
     * Return if the leaves can decay
     *
     * @return if able to decay
     */
    boolean canDecay();

    LeafType getType();

    enum LeafType {
        OAK,
        SPRUCE,
        BIRCH,
        JUNGLE,
        ACACIA,
        DARK_OAK;
    }

}
