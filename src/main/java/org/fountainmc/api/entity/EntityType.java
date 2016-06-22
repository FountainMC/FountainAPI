package org.fountainmc.api.entity;

import org.fountainmc.api.Fountain;
import org.fountainmc.api.entity.hanging.ItemFrame;
import org.fountainmc.api.entity.hanging.LeashKnot;
import org.fountainmc.api.entity.hanging.Painting;

public interface EntityType<T extends Entity> {

    EntityType<Player> PLAYER = Fountain.getServer().getEntityType("Player", Player.class);
    EntityType<Entity> XP_ORB = Fountain.getServer().getEntityType("XPOrb", Entity.class);
    EntityType<Entity> AREA_EFFECT_CLOUD = Fountain.getServer().getEntityType("AreaEffectCloud", Entity.class);
    EntityType<Entity> ENDER_SIGNAL = Fountain.getServer().getEntityType("EyeOfEnderSignal", Entity.class);
    EntityType<Entity> PRIMED_TNT = Fountain.getServer().getEntityType("PrimedTnt", Entity.class);
    EntityType<Entity> FALLING_BLOCK = Fountain.getServer().getEntityType("FallingSand", Entity.class);
    EntityType<Entity> ARMOR_STAND = Fountain.getServer().getEntityType("ArmorStand", Entity.class);
    EntityType<Entity> ENDER_CRYSTAL = Fountain.getServer().getEntityType("EnderCrystal", Entity.class);

    // Hanging entities
    EntityType<LeashKnot> LEASH_KNOT = Fountain.getServer().getEntityType("LeashKnot", LeashKnot.class);
    EntityType<Painting> PAINTING = Fountain.getServer().getEntityType("Painting", Painting.class);
    EntityType<ItemFrame> ITEM_FRAME = Fountain.getServer().getEntityType("ItemFrame", ItemFrame.class);

    // Projectiles
    EntityType<Entity> FIREWORK_ROCKET = Fountain.getServer().getEntityType("FireworksRocketEntity", Entity.class);
    EntityType<Entity> SPECTRAL_ARROW = Fountain.getServer().getEntityType("SpectralArrow", Entity.class);
    EntityType<Entity> SHULKER_BULLET = Fountain.getServer().getEntityType("ShulkerBullet", Entity.class);
    EntityType<Entity> DRAGON_FIREBALL = Fountain.getServer().getEntityType("DragonFireball", Entity.class);
    EntityType<Entity> WITHER_SKULL = Fountain.getServer().getEntityType("WitherSkull", Entity.class);
    EntityType<Entity> THROWN_POTION = Fountain.getServer().getEntityType("ThrownPotion", Entity.class);
    EntityType<Entity> THROWN_EXP_BOTTLE = Fountain.getServer().getEntityType("ThrownExpBottle", Entity.class);
    EntityType<Entity> ARROW = Fountain.getServer().getEntityType("Arrow", Entity.class);
    EntityType<Entity> SNOWBALL = Fountain.getServer().getEntityType("Snowball", Entity.class);
    EntityType<Entity> FIREBALL = Fountain.getServer().getEntityType("Fireball", Entity.class);
    EntityType<Entity> SMALL_FIREBALL = Fountain.getServer().getEntityType("SmallFireball", Entity.class);
    EntityType<Entity> THROWN_ENDERPEARL = Fountain.getServer().getEntityType("ThrownEnderpearl", Entity.class);
    EntityType<Entity> THROWN_EGG = Fountain.getServer().getEntityType("ThrownEgg", Entity.class);

    // Vehicles
    EntityType<Entity> BOAT = Fountain.getServer().getEntityType("Boat", Entity.class);

    // TODO: MINECARTS
    //
    // Living Entities
    //
    EntityType<LivingEntity> CREEPER = Fountain.getServer().getEntityType("Creeper", LivingEntity.class);
    EntityType<LivingEntity> SKELETON = Fountain.getServer().getEntityType("Skeleton", LivingEntity.class);
    EntityType<LivingEntity> SPIDER = Fountain.getServer().getEntityType("Spider", LivingEntity.class);
    EntityType<LivingEntity> GIANT = Fountain.getServer().getEntityType("Giant", LivingEntity.class);
    EntityType<LivingEntity> ZOMBIE = Fountain.getServer().getEntityType("Zombie", LivingEntity.class);
    EntityType<LivingEntity> SLIME = Fountain.getServer().getEntityType("Slime", LivingEntity.class);
    EntityType<LivingEntity> GHAST = Fountain.getServer().getEntityType("Ghast", LivingEntity.class);
    EntityType<LivingEntity> PIG_ZOMBIE = Fountain.getServer().getEntityType("PigZombie", LivingEntity.class);
    EntityType<LivingEntity> ENDERMAN = Fountain.getServer().getEntityType("Enderman", LivingEntity.class);
    EntityType<LivingEntity> CAVE_SPIDER = Fountain.getServer().getEntityType("CaveSpider", LivingEntity.class);
    EntityType<LivingEntity> SILVERFISH = Fountain.getServer().getEntityType("Silverfish", LivingEntity.class);
    EntityType<LivingEntity> BLAZE = Fountain.getServer().getEntityType("Blaze", LivingEntity.class);
    EntityType<LivingEntity> MAGMA_CUBE = Fountain.getServer().getEntityType("LavaSlime", LivingEntity.class);
    EntityType<LivingEntity> ENDER_DRAGON = Fountain.getServer().getEntityType("EnderDragon", LivingEntity.class);
    EntityType<LivingEntity> WITHER = Fountain.getServer().getEntityType("WitherBoss", LivingEntity.class);
    EntityType<LivingEntity> BAT = Fountain.getServer().getEntityType("Bat", LivingEntity.class);
    EntityType<LivingEntity> WITCH = Fountain.getServer().getEntityType("Witch", LivingEntity.class);
    EntityType<LivingEntity> ENDERMITE = Fountain.getServer().getEntityType("Endermite", LivingEntity.class);
    EntityType<LivingEntity> GUARDIAN = Fountain.getServer().getEntityType("Guardian", LivingEntity.class);
    EntityType<LivingEntity> SHULKER = Fountain.getServer().getEntityType("Shulker", LivingEntity.class);
    EntityType<LivingEntity> PIG = Fountain.getServer().getEntityType("Pig", LivingEntity.class);
    EntityType<LivingEntity> SHEEP = Fountain.getServer().getEntityType("Sheep", LivingEntity.class);
    EntityType<LivingEntity> COW = Fountain.getServer().getEntityType("Cow", LivingEntity.class);
    EntityType<LivingEntity> CHICKEN = Fountain.getServer().getEntityType("Chicken", LivingEntity.class);
    EntityType<LivingEntity> SQUID = Fountain.getServer().getEntityType("Squid", LivingEntity.class);
    EntityType<LivingEntity> WOLF = Fountain.getServer().getEntityType("Wolf", LivingEntity.class);
    EntityType<LivingEntity> MUSHROOM_COW = Fountain.getServer().getEntityType("MushroomCow", LivingEntity.class);
    EntityType<LivingEntity> SNOWMAN = Fountain.getServer().getEntityType("SnowMan", LivingEntity.class);
    EntityType<LivingEntity> OCELOT = Fountain.getServer().getEntityType("Ozelot", LivingEntity.class);
    EntityType<LivingEntity> IRON_GOLEM = Fountain.getServer().getEntityType("VillagerGolem", LivingEntity.class);
    EntityType<LivingEntity> HORSE = Fountain.getServer().getEntityType("EntityHorse", LivingEntity.class);
    EntityType<LivingEntity> RABBIT = Fountain.getServer().getEntityType("Rabbit", LivingEntity.class);
    EntityType<LivingEntity> VILLAGER = Fountain.getServer().getEntityType("Villager", LivingEntity.class);

    /**
     * Get the maximum health a entity should have
     *
     * @return the maximum health
     */
    double getMaxHealth();

    /**
     * Get the class that implements this entity type
     *
     * @return the class that implements this entity type
     */
    Class<? extends T> getEntityClass();

}
