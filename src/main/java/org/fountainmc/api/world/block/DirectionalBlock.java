package org.fountainmc.api.world.block;

import org.fountainmc.api.Direction;

public interface DirectionalBlock extends BlockState {

    DirectionalBlock withDirection(Direction direction);

    boolean mayBeVertical();

}
