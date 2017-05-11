package org.fountainmc.api.world.block

import javax.annotation.ParametersAreNonnullByDefault

/**
 * This block state represents a sign's block state.
 */
@ParametersAreNonnullByDefault
interface SignState : DirectionalBlock {

    /**
     * Returns how this sign is attached.
     * @return how this sign is attached
     */
    val attachmentType: AttachmentType

    fun withAttachmentType(attachmentType: AttachmentType): SignState

    enum class AttachmentType {

        STANDING,
        AGAINST_BLOCK

    }
}
