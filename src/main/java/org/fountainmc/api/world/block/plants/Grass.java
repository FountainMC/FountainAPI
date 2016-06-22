package org.fountainmc.api.world.block.plants;

/**
 * Data for a 'grass' block, which may or may not actually be grass. Note that
 * double tall grass resides in {@link DoublePlant}
 */
public interface Grass extends Plant {

    GrassType getType();

    enum GrassType {
        SHRUB,
        TALL_GRASS,
        FERN;
    }

}
