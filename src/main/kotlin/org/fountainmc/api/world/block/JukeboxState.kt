package org.fountainmc.api.world.block

/**
 * Represents a jukebox's block state
 */
interface JukeboxState : BlockState {
    fun hasRecord(): Boolean
}
