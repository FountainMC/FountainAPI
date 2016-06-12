package org.fountainmc.api.world.block;

import javax.annotation.Nonnull;

import org.fountainmc.api.Color;

@Nonnull
public interface ColoredBlock extends BlockState {
    public Color getColor();

    public ColoredBlock withColor(Color color);
}
