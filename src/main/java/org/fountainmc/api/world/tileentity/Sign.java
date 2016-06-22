package org.fountainmc.api.world.tileentity;

import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.common.collect.ImmutableList;

import org.fountainmc.api.world.block.SignState;

import static com.google.common.base.Preconditions.*;

@ParametersAreNonnullByDefault
public interface Sign extends TileEntity {
    @Nonnull
    ImmutableList<String> getLines();

    void setLines(List<String> lines);

    default SignState.AttachmentType getAttachmentType() {
        return getBlockState().getAttachmentType();
    }

    default void setAttachmentType(SignState.AttachmentType attachmentType) {
        setBlockState(getBlockState().withAttachmentType(checkNotNull(attachmentType, "Null attachment type")));
    }

    @Override
    default SignState getBlockState() {
        return (SignState) TileEntity.super.getBlockState();
    }
}
