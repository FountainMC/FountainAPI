package org.fountainmc.api;

import org.fountainmc.api.world.block.BlockState;

public interface BlockType extends Material {
    @Override
    public default boolean isBlock() {
        return true;
    }

    public boolean isFlammable();

    /**
     * Return if the block completely blocks all vision
     *
     * @return if opaque
     */
    public boolean isOpaque();

    /**
     * Return if the block is transparent and the player can see through it
     * <p>Transparent blocks do not block light.</p>
     *
     * @return if transparent
     */
    public boolean isTransparent();

    /**
     * Get the default state the block is in
     *
     * @return the default state of the block
     */
    public BlockState getDefaultState();

    public static BlockType getByName(String name) {
        return Fountain.getServer().getBlockType(name);
    }

    // Vanilla
    public static final BlockType AIR = getByName("minecraft:air");
    public static final BlockType STONE = getByName("minecraft:stone");
    public static final BlockType GRASS = getByName("minecraft:grass");
    public static final BlockType DIRT = getByName("minecraft:dirt");
    public static final BlockType COBBLESTONE = getByName("minecraft:cobblestone");
    public static final BlockType PLANKS = getByName("minecraft:planks");
    public static final BlockType SAPLING = getByName("minecraft:sapling");
    public static final BlockType BEDROCK = getByName("minecraft:bedrock");
    public static final BlockType FLOWING_WATER = getByName("minecraft:flowing_water");
    public static final BlockType WATER = getByName("minecraft:water");
    public static final BlockType FLOWING_LAVA = getByName("minecraft:flowing_lava");
    public static final BlockType LAVA = getByName("minecraft:lava");
    public static final BlockType SAND = getByName("minecraft:sand");
    public static final BlockType GRAVEL = getByName("minecraft:gravel");
    public static final BlockType GOLD_ORE = getByName("minecraft:gold_ore");
    public static final BlockType IRON_ORE = getByName("minecraft:iron_ore");
    public static final BlockType COAL_ORE = getByName("minecraft:coal_ore");
    public static final BlockType LOG = getByName("minecraft:log");
    public static final BlockType LOG2 = getByName("minecraft:log2");
    public static final BlockType LEAVES = getByName("minecraft:leaves");
    public static final BlockType LEAVES2 = getByName("minecraft:leaves2");
    public static final BlockType SPONGE = getByName("minecraft:sponge");
    public static final BlockType GLASS = getByName("minecraft:glass");
    public static final BlockType LAPIS_ORE = getByName("minecraft:lapis_ore");
    public static final BlockType LAPIS_BLOCK = getByName("minecraft:lapis_block");
    public static final BlockType DISPENSER = getByName("minecraft:dispenser");
    public static final BlockType SANDSTONE = getByName("minecraft:sandstone");
    public static final BlockType NOTEBLOCK = getByName("minecraft:noteblock");
    public static final BlockType BED = getByName("minecraft:bed");
    public static final BlockType GOLDEN_RAIL = getByName("minecraft:golden_rail");
    public static final BlockType DETECTOR_RAIL = getByName("minecraft:detector_rail");
    public static final BlockType STICKY_PISTON = getByName("minecraft:sticky_piston");
    public static final BlockType WEB = getByName("minecraft:web");
    public static final BlockType TALLGRASS = getByName("minecraft:tallgrass");
    public static final BlockType DEADBUSH = getByName("minecraft:deadbush");
    public static final BlockType PISTON = getByName("minecraft:piston");
    public static final BlockType PISTON_HEAD = getByName("minecraft:piston_head");
    public static final BlockType WOOL = getByName("minecraft:wool");
    public static final BlockType PISTON_EXTENSION = getByName("minecraft:piston_extension");
    public static final BlockType YELLOW_FLOWER = getByName("minecraft:yellow_flower");
    public static final BlockType RED_FLOWER = getByName("minecraft:red_flower");
    public static final BlockType BROWN_MUSHROOM = getByName("minecraft:brown_mushroom");
    public static final BlockType RED_MUSHROOM = getByName("minecraft:red_mushroom");
    public static final BlockType GOLD_BLOCK = getByName("minecraft:gold_block");
    public static final BlockType IRON_BLOCK = getByName("minecraft:iron_block");
    public static final BlockType DOUBLE_STONE_SLAB = getByName("minecraft:double_stone_slab");
    public static final BlockType STONE_SLAB = getByName("minecraft:stone_slab");
    public static final BlockType BRICK_BLOCK = getByName("minecraft:brick_block");
    public static final BlockType TNT = getByName("minecraft:tnt");
    public static final BlockType BOOKSHELF = getByName("minecraft:bookshelf");
    public static final BlockType MOSSY_COBBLESTONE = getByName("minecraft:mossy_cobblestone");
    public static final BlockType OBSIDIAN = getByName("minecraft:obsidian");
    public static final BlockType TORCH = getByName("minecraft:torch");
    public static final BlockType FIRE = getByName("minecraft:fire");
    public static final BlockType MOB_SPAWNER = getByName("minecraft:mob_spawner");
    public static final BlockType OAK_STAIRS = getByName("minecraft:oak_stairs");
    public static final BlockType CHEST = getByName("minecraft:chest");
    public static final BlockType REDSTONE_WIRE = getByName("minecraft:redstone_wire");
    public static final BlockType DIAMOND_ORE = getByName("minecraft:diamond_ore");
    public static final BlockType DIAMOND_BLOCK = getByName("minecraft:diamond_block");
    public static final BlockType CRAFTING_TABLE = getByName("minecraft:crafting_table");
    public static final BlockType WHEAT = getByName("minecraft:wheat");
    public static final BlockType FARMLAND = getByName("minecraft:farmland");
    public static final BlockType FURNACE = getByName("minecraft:furnace");
    public static final BlockType LIT_FURNACE = getByName("minecraft:lit_furnace");
    public static final BlockType STANDING_SIGN = getByName("minecraft:standing_sign");
    public static final BlockType WOODEN_DOOR = getByName("minecraft:wooden_door");
    public static final BlockType SPRUCE_DOOR = getByName("minecraft:spruce_door");
    public static final BlockType BIRCH_DOOR = getByName("minecraft:birch_door");
    public static final BlockType JUNGLE_DOOR = getByName("minecraft:jungle_door");
    public static final BlockType ACACIA_DOOR = getByName("minecraft:acacia_door");
    public static final BlockType DARK_OAK_DOOR = getByName("minecraft:dark_oak_door");
    public static final BlockType LADDER = getByName("minecraft:ladder");
    public static final BlockType RAIL = getByName("minecraft:rail");
    public static final BlockType STONE_STAIRS = getByName("minecraft:stone_stairs");
    public static final BlockType WALL_SIGN = getByName("minecraft:wall_sign");
    public static final BlockType LEVER = getByName("minecraft:lever");
    public static final BlockType STONE_PRESSURE_PLATE = getByName("minecraft:stone_pressure_plate");
    public static final BlockType IRON_DOOR = getByName("minecraft:iron_door");
    public static final BlockType WOODEN_PRESSURE_PLATE = getByName("minecraft:wooden_pressure_plate");
    public static final BlockType REDSTONE_ORE = getByName("minecraft:redstone_ore");
    public static final BlockType LIT_REDSTONE_ORE = getByName("minecraft:lit_redstone_ore");
    public static final BlockType UNLIT_REDSTONE_TORCH = getByName("minecraft:unlit_redstone_torch");
    public static final BlockType REDSTONE_TORCH = getByName("minecraft:redstone_torch");
    public static final BlockType STONE_BUTTON = getByName("minecraft:stone_button");
    public static final BlockType SNOW_LAYER = getByName("minecraft:snow_layer");
    public static final BlockType ICE = getByName("minecraft:ice");
    public static final BlockType SNOW = getByName("minecraft:snow");
    public static final BlockType CACTUS = getByName("minecraft:cactus");
    public static final BlockType CLAY = getByName("minecraft:clay");
    public static final BlockType REEDS = getByName("minecraft:reeds");
    public static final BlockType JUKEBOX = getByName("minecraft:jukebox");
    public static final BlockType FENCE = getByName("minecraft:fence");
    public static final BlockType SPRUCE_FENCE = getByName("minecraft:spruce_fence");
    public static final BlockType BIRCH_FENCE = getByName("minecraft:birch_fence");
    public static final BlockType JUNGLE_FENCE = getByName("minecraft:jungle_fence");
    public static final BlockType DARK_OAK_FENCE = getByName("minecraft:dark_oak_fence");
    public static final BlockType ACACIA_FENCE = getByName("minecraft:acacia_fence");
    public static final BlockType PUMPKIN = getByName("minecraft:pumpkin");
    public static final BlockType NETHERRACK = getByName("minecraft:netherrack");
    public static final BlockType SOUL_SAND = getByName("minecraft:soul_sand");
    public static final BlockType GLOWSTONE = getByName("minecraft:glowstone");
    public static final BlockType PORTAL = getByName("minecraft:portal");
    public static final BlockType LIT_PUMPKIN = getByName("minecraft:lit_pumpkin");
    public static final BlockType CAKE = getByName("minecraft:cake");
    public static final BlockType UNPOWERED_REPEATER = getByName("minecraft:unpowered_repeater");
    public static final BlockType POWERED_REPEATER = getByName("minecraft:powered_repeater");
    public static final BlockType TRAPDOOR = getByName("minecraft:trapdoor");
    public static final BlockType MONSTER_EGG = getByName("minecraft:monster_egg");
    public static final BlockType STONEBRICK = getByName("minecraft:stonebrick");
    public static final BlockType BROWN_MUSHROOM_BLOCK = getByName("minecraft:brown_mushroom_block");
    public static final BlockType RED_MUSHROOM_BLOCK = getByName("minecraft:red_mushroom_block");
    public static final BlockType IRON_BARS = getByName("minecraft:iron_bars");
    public static final BlockType GLASS_PANE = getByName("minecraft:glass_pane");
    public static final BlockType MELON_BLOCK = getByName("minecraft:melon_block");
    public static final BlockType PUMPKIN_STEM = getByName("minecraft:pumpkin_stem");
    public static final BlockType MELON_STEM = getByName("minecraft:melon_stem");
    public static final BlockType VINE = getByName("minecraft:vine");
    public static final BlockType FENCE_GATE = getByName("minecraft:fence_gate");
    public static final BlockType SPRUCE_FENCE_GATE = getByName("minecraft:spruce_fence_gate");
    public static final BlockType BIRCH_FENCE_GATE = getByName("minecraft:birch_fence_gate");
    public static final BlockType JUNGLE_FENCE_GATE = getByName("minecraft:jungle_fence_gate");
    public static final BlockType DARK_OAK_FENCE_GATE = getByName("minecraft:dark_oak_fence_gate");
    public static final BlockType ACACIA_FENCE_GATE = getByName("minecraft:acacia_fence_gate");
    public static final BlockType BRICK_STAIRS = getByName("minecraft:brick_stairs");
    public static final BlockType STONE_BRICK_STAIRS = getByName("minecraft:stone_brick_stairs");
    public static final BlockType MYCELIUM = getByName("minecraft:mycelium");
    public static final BlockType WATERLILY = getByName("minecraft:waterlily");
    public static final BlockType NETHER_BRICK = getByName("minecraft:nether_brick");
    public static final BlockType NETHER_BRICK_FENCE = getByName("minecraft:nether_brick_fence");
    public static final BlockType NETHER_BRICK_STAIRS = getByName("minecraft:nether_brick_stairs");
    public static final BlockType NETHER_WART = getByName("minecraft:nether_wart");
    public static final BlockType ENCHANTING_TABLE = getByName("minecraft:enchanting_table");
    public static final BlockType BREWING_STAND = getByName("minecraft:brewing_stand");
    public static final BlockType CAULDRON = getByName("minecraft:cauldron");
    public static final BlockType END_PORTAL = getByName("minecraft:end_portal");
    public static final BlockType END_PORTAL_FRAME = getByName("minecraft:end_portal_frame");
    public static final BlockType END_STONE = getByName("minecraft:end_stone");
    public static final BlockType DRAGON_EGG = getByName("minecraft:dragon_egg");
    public static final BlockType REDSTONE_LAMP = getByName("minecraft:redstone_lamp");
    public static final BlockType LIT_REDSTONE_LAMP = getByName("minecraft:lit_redstone_lamp");
    public static final BlockType DOUBLE_WOODEN_SLAB = getByName("minecraft:double_wooden_slab");
    public static final BlockType WOODEN_SLAB = getByName("minecraft:wooden_slab");
    public static final BlockType COCOA = getByName("minecraft:cocoa");
    public static final BlockType SANDSTONE_STAIRS = getByName("minecraft:sandstone_stairs");
    public static final BlockType EMERALD_ORE = getByName("minecraft:emerald_ore");
    public static final BlockType ENDER_CHEST = getByName("minecraft:ender_chest");
    public static final BlockType TRIPWIRE_HOOK = getByName("minecraft:tripwire_hook");
    public static final BlockType TRIPWIRE = getByName("minecraft:tripwire");
    public static final BlockType EMERALD_BLOCK = getByName("minecraft:emerald_block");
    public static final BlockType SPRUCE_STAIRS = getByName("minecraft:spruce_stairs");
    public static final BlockType BIRCH_STAIRS = getByName("minecraft:birch_stairs");
    public static final BlockType JUNGLE_STAIRS = getByName("minecraft:jungle_stairs");
    public static final BlockType COMMAND_BLOCK = getByName("minecraft:command_block");
    public static final BlockType BEACON = getByName("minecraft:beacon");
    public static final BlockType COBBLESTONE_WALL = getByName("minecraft:cobblestone_wall");
    public static final BlockType FLOWER_POT = getByName("minecraft:flower_pot");
    public static final BlockType CARROTS = getByName("minecraft:carrots");
    public static final BlockType POTATOES = getByName("minecraft:potatoes");
    public static final BlockType WOODEN_BUTTON = getByName("minecraft:wooden_button");
    public static final BlockType SKULL = getByName("minecraft:skull");
    public static final BlockType ANVIL = getByName("minecraft:anvil");
    public static final BlockType TRAPPED_CHEST = getByName("minecraft:trapped_chest");
    public static final BlockType LIGHT_WEIGHTED_PRESSURE_PLATE = getByName("minecraft:light_weighted_pressure_plate");
    public static final BlockType HEAVY_WEIGHTED_PRESSURE_PLATE = getByName("minecraft:heavy_weighted_pressure_plate");
    public static final BlockType UNPOWERED_COMPARATOR = getByName("minecraft:unpowered_comparator");
    public static final BlockType POWERED_COMPARATOR = getByName("minecraft:powered_comparator");
    public static final BlockType DAYLIGHT_DETECTOR = getByName("minecraft:daylight_detector");
    public static final BlockType DAYLIGHT_DETECTOR_INVERTED = getByName("minecraft:daylight_detector_inverted");
    public static final BlockType REDSTONE_BLOCK = getByName("minecraft:redstone_block");
    public static final BlockType QUARTZ_ORE = getByName("minecraft:quartz_ore");
    public static final BlockType HOPPER = getByName("minecraft:hopper");
    public static final BlockType QUARTZ_BLOCK = getByName("minecraft:quartz_block");
    public static final BlockType QUARTZ_STAIRS = getByName("minecraft:quartz_stairs");
    public static final BlockType ACTIVATOR_RAIL = getByName("minecraft:activator_rail");
    public static final BlockType DROPPER = getByName("minecraft:dropper");
    public static final BlockType STAINED_HARDENED_CLAY = getByName("minecraft:stained_hardened_clay");
    public static final BlockType BARRIER = getByName("minecraft:barrier");
    public static final BlockType IRON_TRAPDOOR = getByName("minecraft:iron_trapdoor");
    public static final BlockType HAY_BLOCK = getByName("minecraft:hay_block");
    public static final BlockType CARPET = getByName("minecraft:carpet");
    public static final BlockType HARDENED_CLAY = getByName("minecraft:hardened_clay");
    public static final BlockType COAL_BLOCK = getByName("minecraft:coal_block");
    public static final BlockType PACKED_ICE = getByName("minecraft:packed_ice");
    public static final BlockType ACACIA_STAIRS = getByName("minecraft:acacia_stairs");
    public static final BlockType DARK_OAK_STAIRS = getByName("minecraft:dark_oak_stairs");
    public static final BlockType SLIME = getByName("minecraft:slime");
    public static final BlockType DOUBLE_PLANT = getByName("minecraft:double_plant");
    public static final BlockType STAINED_GLASS = getByName("minecraft:stained_glass");
    public static final BlockType STAINED_GLASS_PANE = getByName("minecraft:stained_glass_pane");
    public static final BlockType PRISMARINE = getByName("minecraft:prismarine");
    public static final BlockType SEA_LANTERN = getByName("minecraft:sea_lantern");
    public static final BlockType STANDING_BANNER = getByName("minecraft:standing_banner");
    public static final BlockType WALL_BANNER = getByName("minecraft:wall_banner");
    public static final BlockType RED_SANDSTONE = getByName("minecraft:red_sandstone");
    public static final BlockType RED_SANDSTONE_STAIRS = getByName("minecraft:red_sandstone_stairs");
    public static final BlockType DOUBLE_STONE_SLAB2 = getByName("minecraft:double_stone_slab2");
    public static final BlockType STONE_SLAB2 = getByName("minecraft:stone_slab2");
    public static final BlockType END_ROD = getByName("minecraft:end_rod");
    public static final BlockType CHORUS_PLANT = getByName("minecraft:chorus_plant");
    public static final BlockType CHORUS_FLOWER = getByName("minecraft:chorus_flower");
    public static final BlockType PURPUR_BLOCK = getByName("minecraft:purpur_block");
    public static final BlockType PURPUR_PILLAR = getByName("minecraft:purpur_pillar");
    public static final BlockType PURPUR_STAIRS = getByName("minecraft:purpur_stairs");
    public static final BlockType PURPUR_DOUBLE_SLAB = getByName("minecraft:purpur_double_slab");
    public static final BlockType PURPUR_SLAB = getByName("minecraft:purpur_slab");
    public static final BlockType END_BRICKS = getByName("minecraft:end_bricks");
    public static final BlockType BEETROOT = getByName("minecraft:beetroots");
    public static final BlockType GRASS_PATH = getByName("minecraft:grass_path");
    public static final BlockType END_GATEWAY = getByName("minecraft:end_gateway");
    public static final BlockType FROSTED_ICE = getByName("minecraft:frosted_ice");
    public static final BlockType STRUCTURE_BLOCK = getByName("minecraft:structure_block");
}
