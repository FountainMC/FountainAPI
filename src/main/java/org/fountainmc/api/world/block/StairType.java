package org.fountainmc.api.world.block;

public enum StairType {
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
        @Override
        public WoodType getWoodType() {
            return WoodType.OAK;
        }
    },
    SPRUCE(Category.WOODEN) {
        @Override
        public WoodType getWoodType() {
            return WoodType.SPRUCE;
        }
    },
    BIRCH(Category.WOODEN) {
        @Override
        public WoodType getWoodType() {
            return WoodType.BIRCH;
        }
    },
    JUNGLE(Category.WOODEN) {
        @Override
        public WoodType getWoodType() {
            return WoodType.JUNGLE;
        }
    },
    ACACIA(Category.WOODEN) {
        @Override
        public WoodType getWoodType() {
            return WoodType.ACACIA;
        }
    },
    DARK_OAK(Category.WOODEN) {
        @Override
        public WoodType getWoodType() {
            return WoodType.DARK_OAK;
        }
    };
    private final Category category;

    StairType(Category category) {
        this.category = category;
    }

    public boolean isWooden() {
        return category == Category.WOODEN;
    }

    public boolean isRocky() {
        return category == Category.ROCKY;
    }

    public WoodType getWoodType() {
        if (isWooden()) {
            throw new AssertionError("Wooden type " + this + " didn't implement getWoodType()");
        } else {
            throw new IllegalStateException("StairType " + this + " isn't wooden");
        }
    }

    public static enum Category {
        WOODEN,
        ROCKY,
        PURPUR;
    }
}
