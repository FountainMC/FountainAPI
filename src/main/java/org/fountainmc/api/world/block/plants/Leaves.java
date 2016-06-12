package org.fountainmc.api.world.block.plants;

public interface Leaves extends Plant {
    /**
     * Return if the leaves can decay
     *
     * @return if able to decay
     */
    public boolean canDecay();

    public LeafType getType();

    public static enum LeafType {
        OAK,
        SPRUCE,
        BIRCH,
        JUNGLE,
        ACACIA,
        DARK_OAK;
    }
}
