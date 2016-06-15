package org.fountainmc.api.world.block;

import org.fountainmc.api.Color;

public interface WoolBlock extends ColoredBlock {

    @Override
    public WoolBlock withColor(Color color);

}
