package org.fountainmc.api.world.block;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * This block state represents a sign's block state.
 */
@ParametersAreNonnullByDefault
public interface SignState extends DirectionalBlock {

    /**
     * Returns how this sign is attached.
     * @return how this sign is attached
     */
    @Nonnull
    AttachmentType getAttachmentType();

    @Nonnull
    SignState withAttachmentType(AttachmentType attachmentType);

    enum AttachmentType {

        STANDING,
        AGAINST_BLOCK

    }
}
