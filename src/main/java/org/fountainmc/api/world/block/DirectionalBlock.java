package org.fountainmc.api.world.block;

import org.fountainmc.api.Direction;

public interface DirectionalBlock extends BlockState {
    public DirectionalBlock withDirection(Direction direction);

    public boolean mayBeVertical();
}
