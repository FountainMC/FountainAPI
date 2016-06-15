package org.fountainmc.api.world.block;

public interface DoubleSlab extends BlockState {

    /**
     * Get the block type of the slab
     *
     * @return the type of the slab
     */
    public StairType getType();

    /**
     * Get a copy of this slab with the given type
     *
     * @param type the new type
     * @return the new slab
     */
    public Slab withType(StairType type);

    public boolean isSmooth();

    public DoubleSlab withSmoothness(boolean smooth);

    public default DoubleSlab asSmooth() {
        return withSmoothness(true);
    }

    public default DoubleSlab asRough() {
        return withSmoothness(false);
    }

}
