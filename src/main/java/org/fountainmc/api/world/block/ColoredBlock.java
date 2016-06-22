package org.fountainmc.api.world.block;

import javax.annotation.Nonnull;

import org.fountainmc.api.Color;

@Nonnull
public interface ColoredBlock extends BlockState {

    Color getColor();

    ColoredBlock withColor(Color color);

}
