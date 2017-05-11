package org.fountainmc.api.world.block

interface Sand : FallingBlock {

    val type: SandType

    fun withType(type: SandType): Sand

    enum class SandType {
        SAND,
        RED_SAND
    }

}
