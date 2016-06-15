package org.fountainmc.api.entity;

import org.fountainmc.api.Fountain;
import org.fountainmc.api.entity.hanging.ItemFrame;
import org.fountainmc.api.entity.hanging.LeashKnot;
import org.fountainmc.api.entity.hanging.Painting;

public interface EntityType<T extends Entity> {

    public static final EntityType<Player> PLAYER = Fountain.getServer().getEntityType("Player", Player.class);
    public static final EntityType<Entity> XP_ORB = Fountain.getServer().getEntityType("XPOrb", Entity.class);
    public static final EntityType<Entity> AREA_EFFECT_CLOUD = Fountain.getServer().getEntityType("AreaEffectCloud", Entity.class);
    public static final EntityType<Entity> ENDER_SIGNAL = Fountain.getServer().getEntityType("EyeOfEnderSignal", Entity.class);
    public static final EntityType<Entity> PRIMED_TNT = Fountain.getServer().getEntityType("PrimedTnt", Entity.class);
    public static final EntityType<Entity> FALLING_BLOCK = Fountain.getServer().getEntityType("FallingSand", Entity.class);
    public static final EntityType<Entity> ARMOR_STAND = Fountain.getServer().getEntityType("ArmorStand", Entity.class);
    public static final EntityType<Entity> ENDER_CRYSTAL = Fountain.getServer().getEntityType("EnderCrystal", Entity.class);

    // Hanging entities
    public static final EntityType<LeashKnot> LEASH_KNOT = Fountain.getServer().getEntityType("LeashKnot", LeashKnot.class);
    public static final EntityType<Painting> PAINTING = Fountain.getServer().getEntityType("Painting", Painting.class);
    public static final EntityType<ItemFrame> ITEM_FRAME = Fountain.getServer().getEntityType("ItemFrame", ItemFrame.class);

    // Projectiles
    public static final EntityType<Entity> FIREWORK_ROCKET = Fountain.getServer().getEntityType("FireworksRocketEntity", Entity.class);
    public static final EntityType<Entity> SPECTRAL_ARROW = Fountain.getServer().getEntityType("SpectralArrow", Entity.class);
    public static final EntityType<Entity> SHULKER_BULLET = Fountain.getServer().getEntityType("ShulkerBullet", Entity.class);
    public static final EntityType<Entity> DRAGON_FIREBALL = Fountain.getServer().getEntityType("DragonFireball", Entity.class);
    public static final EntityType<Entity> WITHER_SKULL = Fountain.getServer().getEntityType("WitherSkull", Entity.class);
    public static final EntityType<Entity> THROWN_POTION = Fountain.getServer().getEntityType("ThrownPotion", Entity.class);
    public static final EntityType<Entity> THROWN_EXP_BOTTLE = Fountain.getServer().getEntityType("ThrownExpBottle", Entity.class);
    public static final EntityType<Entity> ARROW = Fountain.getServer().getEntityType("Arrow", Entity.class);
    public static final EntityType<Entity> SNOWBALL = Fountain.getServer().getEntityType("Snowball", Entity.class);
    public static final EntityType<Entity> FIREBALL = Fountain.getServer().getEntityType("Fireball", Entity.class);
    public static final EntityType<Entity> SMALL_FIREBALL = Fountain.getServer().getEntityType("SmallFireball", Entity.class);
    public static final EntityType<Entity> THROWN_ENDERPEARL = Fountain.getServer().getEntityType("ThrownEnderpearl", Entity.class);
    public static final EntityType<Entity> THROWN_EGG = Fountain.getServer().getEntityType("ThrownEgg", Entity.class);

    // Vehicles
    public static final EntityType<Entity> BOAT = Fountain.getServer().getEntityType("Boat", Entity.class);

    // TODO: MINECARTS
    //
    // Living Entities
    //
    public static final EntityType<LivingEntity> CREEPER = Fountain.getServer().getEntityType("Creeper", LivingEntity.class);
    public static final EntityType<LivingEntity> SKELETON = Fountain.getServer().getEntityType("Skeleton", LivingEntity.class);
    public static final EntityType<LivingEntity> SPIDER = Fountain.getServer().getEntityType("Spider", LivingEntity.class);
    public static final EntityType<LivingEntity> GIANT = Fountain.getServer().getEntityType("Giant", LivingEntity.class);
    public static final EntityType<LivingEntity> ZOMBIE = Fountain.getServer().getEntityType("Zombie", LivingEntity.class);
    public static final EntityType<LivingEntity> SLIME = Fountain.getServer().getEntityType("Slime", LivingEntity.class);
    public static final EntityType<LivingEntity> GHAST = Fountain.getServer().getEntityType("Ghast", LivingEntity.class);
    public static final EntityType<LivingEntity> PIG_ZOMBIE = Fountain.getServer().getEntityType("PigZombie", LivingEntity.class);
    public static final EntityType<LivingEntity> ENDERMAN = Fountain.getServer().getEntityType("Enderman", LivingEntity.class);
    public static final EntityType<LivingEntity> CAVE_SPIDER = Fountain.getServer().getEntityType("CaveSpider", LivingEntity.class);
    public static final EntityType<LivingEntity> SILVERFISH = Fountain.getServer().getEntityType("Silverfish", LivingEntity.class);
    public static final EntityType<LivingEntity> BLAZE = Fountain.getServer().getEntityType("Blaze", LivingEntity.class);
    public static final EntityType<LivingEntity> MAGMA_CUBE = Fountain.getServer().getEntityType("LavaSlime", LivingEntity.class);
    public static final EntityType<LivingEntity> ENDER_DRAGON = Fountain.getServer().getEntityType("EnderDragon", LivingEntity.class);
    public static final EntityType<LivingEntity> WITHER = Fountain.getServer().getEntityType("WitherBoss", LivingEntity.class);
    public static final EntityType<LivingEntity> BAT = Fountain.getServer().getEntityType("Bat", LivingEntity.class);
    public static final EntityType<LivingEntity> WITCH = Fountain.getServer().getEntityType("Witch", LivingEntity.class);
    public static final EntityType<LivingEntity> ENDERMITE = Fountain.getServer().getEntityType("Endermite", LivingEntity.class);
    public static final EntityType<LivingEntity> GUARDIAN = Fountain.getServer().getEntityType("Guardian", LivingEntity.class);
    public static final EntityType<LivingEntity> SHULKER = Fountain.getServer().getEntityType("Shulker", LivingEntity.class);
    public static final EntityType<LivingEntity> PIG = Fountain.getServer().getEntityType("Pig", LivingEntity.class);
    public static final EntityType<LivingEntity> SHEEP = Fountain.getServer().getEntityType("Sheep", LivingEntity.class);
    public static final EntityType<LivingEntity> COW = Fountain.getServer().getEntityType("Cow", LivingEntity.class);
    public static final EntityType<LivingEntity> CHICKEN = Fountain.getServer().getEntityType("Chicken", LivingEntity.class);
    public static final EntityType<LivingEntity> SQUID = Fountain.getServer().getEntityType("Squid", LivingEntity.class);
    public static final EntityType<LivingEntity> WOLF = Fountain.getServer().getEntityType("Wolf", LivingEntity.class);
    public static final EntityType<LivingEntity> MUSHROOM_COW = Fountain.getServer().getEntityType("MushroomCow", LivingEntity.class);
    public static final EntityType<LivingEntity> SNOWMAN = Fountain.getServer().getEntityType("SnowMan", LivingEntity.class);
    public static final EntityType<LivingEntity> OCELOT = Fountain.getServer().getEntityType("Ozelot", LivingEntity.class);
    public static final EntityType<LivingEntity> IRON_GOLEM = Fountain.getServer().getEntityType("VillagerGolem", LivingEntity.class);
    public static final EntityType<LivingEntity> HORSE = Fountain.getServer().getEntityType("EntityHorse", LivingEntity.class);
    public static final EntityType<LivingEntity> RABBIT = Fountain.getServer().getEntityType("Rabbit", LivingEntity.class);
    public static final EntityType<LivingEntity> VILLAGER = Fountain.getServer().getEntityType("Villager", LivingEntity.class);

    /**
     * Get the maximum health a entity should have
     *
     * @return the maximum health
     */
    public double getMaxHealth();

    /**
     * Get the class that implements this entity type
     *
     * @return the class that implements this entity type
     */
    public Class<? extends T> getEntityClass();

}
