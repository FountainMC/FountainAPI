package org.fountainmc.api.world.block;

import javax.annotation.Nonnull;

/**
 * This block state represents a sign.
 */
public interface Sign extends DirectionalBlock {

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
