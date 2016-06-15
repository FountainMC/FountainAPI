package org.fountainmc.api.world.block;

import javax.annotation.Nonnegative;

public interface LiquidBlock extends BlockState {

    @Nonnegative
    public int getLevel();

    public boolean isFlowing();

}
