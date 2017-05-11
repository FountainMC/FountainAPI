package org.fountainmc.api.world.tileentity

import org.fountainmc.api.world.block.SignState

interface Sign : TileEntity<SignState> {
    var lines: List<String>

    var attachmentType: SignState.AttachmentType
        get() = blockState.attachmentType
        set(attachmentType) {
            blockState = blockState.withAttachmentType(attachmentType)
        }
}
