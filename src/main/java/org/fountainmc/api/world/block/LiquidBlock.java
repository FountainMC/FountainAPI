package org.fountainmc.api.world.block;

import javax.annotation.Nonnegative;

public interface LiquidBlock extends BlockState {

    @Nonnegative
    int getLevel();

    boolean isFlowing();

}
