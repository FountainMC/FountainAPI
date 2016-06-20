package org.fountainmc.api.world.block;

import javax.annotation.Nonnull;

/**
 * This block state represents a sign's block state.
 */
public interface SignState extends DirectionalBlock {

    /**
     * Returns how this sign is attached.
     * @return how this sign is attached
     */
    @Nonnull
    AttachmentType getAttachmentType();

    enum AttachmentType {

        STANDING,
        AGAINST_BLOCK

    }
}
