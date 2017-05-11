package org.fountainmc.api.world.block

enum class StairType private constructor(private val category: StairType.Category) {
    STONE(Category.ROCKY),
    SANDSTONE(Category.ROCKY),
    COBBLESTONE(Category.ROCKY),
    BRICKS(Category.ROCKY),
    STONE_BRICKS(Category.ROCKY),
    NETHER_BRICKS(Category.ROCKY),
    QUARTZ(Category.ROCKY),
    SMOOTH_STONE(Category.ROCKY),
    SANDSTONE_SLAB(Category.ROCKY),
    PURPUR(Category.PURPUR),
    OAK(Category.WOODEN) {

        override val woodType: WoodType
            get() = WoodType.OAK
    },
    SPRUCE(Category.WOODEN) {

        override val woodType: WoodType
            get() = WoodType.SPRUCE
    },
    BIRCH(Category.WOODEN) {

        override val woodType: WoodType
            get() = WoodType.BIRCH
    },
    JUNGLE(Category.WOODEN) {

        override val woodType: WoodType
            get() = WoodType.JUNGLE
    },
    ACACIA(Category.WOODEN) {

        override val woodType: WoodType
            get() = WoodType.ACACIA
    },
    DARK_OAK(Category.WOODEN) {

        override val woodType: WoodType
            get() = WoodType.DARK_OAK
    };

    val isWooden: Boolean
        get() = category == Category.WOODEN

    val isRocky: Boolean
        get() = category == Category.ROCKY

    open val woodType: WoodType
        get() = if (isWooden) {
            throw AssertionError("Wooden type " + this + " didn't implement getWoodType()")
        } else {
            throw IllegalStateException("StairType " + this + " isn't wooden")
        }

    enum class Category {
        WOODEN,
        ROCKY,
        PURPUR
    }

}
