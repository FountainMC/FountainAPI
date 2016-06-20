package org.fountainmc.api.world.tileentity;

import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

import org.fountainmc.api.world.block.BlockState;
import org.fountainmc.api.world.block.SignState;

import static com.google.common.base.Preconditions.*;

@ParametersAreNonnullByDefault
public interface Sign extends TileEntity {
    @Nonnull
    public ImmutableList<String> getLines();

    public void setLines(List<String> lines);

    public default SignState.AttachmentType getAttachmentType() {
        return getBlockState().getAttachmentType();
    }

    public default void setAttachmentType(SignState.AttachmentType attachmentType) {
        setBlockState(getBlockState().withAttachmentType(checkNotNull(attachmentType, "Null attachment type")));
    }

    @Override
    default SignState getBlockState() {
        return (SignState) TileEntity.super.getBlockState();
    }
}
