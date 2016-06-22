package org.fountainmc.api.world.block;

public interface DoubleSlab extends BlockState {

    /**
     * Get the block type of the slab
     *
     * @return the type of the slab
     */
    StairType getType();

    /**
     * Get a copy of this slab with the given type
     *
     * @param type the new type
     * @return the new slab
     */
    Slab withType(StairType type);

    boolean isSmooth();

    DoubleSlab withSmoothness(boolean smooth);

    default DoubleSlab asSmooth() {
        return withSmoothness(true);
    }

    default DoubleSlab asRough() {
        return withSmoothness(false);
    }

}
