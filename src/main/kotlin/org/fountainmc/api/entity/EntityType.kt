package org.fountainmc.api.entity

import org.fountainmc.api.Fountain
import org.fountainmc.api.entity.hanging.ItemFrame
import org.fountainmc.api.entity.hanging.LeashKnot
import org.fountainmc.api.entity.hanging.Painting
import kotlin.reflect.KClass
import kotlin.reflect.full.isSuperclassOf

interface EntityType<out T : Entity> {

    /**
     * The maximum health this type of entity should have.
     */
    val maxHealth: Double

    /**
     * The class that implements this entity type
     */
    val entityClass: KClass<out T>

    companion object {

        val PLAYER = Fountain.getEntityTypeAs("Player", Player::class)
        val XP_ORB = Fountain.getEntityTypeAs("XPOrb", Entity::class)
        val AREA_EFFECT_CLOUD = Fountain.getEntityTypeAs("AreaEffectCloud", Entity::class)
        val ENDER_SIGNAL = Fountain.getEntityTypeAs("EyeOfEnderSignal", Entity::class)
        val PRIMED_TNT = Fountain.getEntityTypeAs("PrimedTnt", Entity::class)
        val FALLING_BLOCK = Fountain.getEntityTypeAs("FallingSand", Entity::class)
        val ARMOR_STAND = Fountain.getEntityTypeAs("ArmorStand", Entity::class)
        val ENDER_CRYSTAL = Fountain.getEntityTypeAs("EnderCrystal", Entity::class)

        // Hanging entities
        val LEASH_KNOT = Fountain.getEntityTypeAs("LeashKnot", LeashKnot::class)
        val PAINTING = Fountain.getEntityTypeAs("Painting", Painting::class)
        val ITEM_FRAME = Fountain.getEntityTypeAs("ItemFrame", ItemFrame::class)

        // Projectiles
        val FIREWORK_ROCKET = Fountain.getEntityTypeAs("FireworksRocketEntity", Entity::class)
        val SPECTRAL_ARROW = Fountain.getEntityTypeAs("SpectralArrow", Entity::class)
        val SHULKER_BULLET = Fountain.getEntityTypeAs("ShulkerBullet", Entity::class)
        val DRAGON_FIREBALL = Fountain.getEntityTypeAs("DragonFireball", Entity::class)
        val WITHER_SKULL = Fountain.getEntityTypeAs("WitherSkull", Entity::class)
        val THROWN_POTION = Fountain.getEntityTypeAs("ThrownPotion", Entity::class)
        val THROWN_EXP_BOTTLE = Fountain.getEntityTypeAs("ThrownExpBottle", Entity::class)
        val ARROW = Fountain.getEntityTypeAs("Arrow", Entity::class)
        val SNOWBALL = Fountain.getEntityTypeAs("Snowball", Entity::class)
        val FIREBALL = Fountain.getEntityTypeAs("Fireball", Entity::class)
        val SMALL_FIREBALL = Fountain.getEntityTypeAs("SmallFireball", Entity::class)
        val THROWN_ENDERPEARL = Fountain.getEntityTypeAs("ThrownEnderpearl", Entity::class)
        val THROWN_EGG = Fountain.getEntityTypeAs("ThrownEgg", Entity::class)

        // Vehicles
        val BOAT = Fountain.getEntityTypeAs("Boat", Entity::class)

        // TODO: MINECARTS
        //
        // Living Entities
        //
        val CREEPER = Fountain.getEntityTypeAs("Creeper", LivingEntity::class)
        val SKELETON = Fountain.getEntityTypeAs("Skeleton", LivingEntity::class)
        val SPIDER = Fountain.getEntityTypeAs("Spider", LivingEntity::class)
        val GIANT = Fountain.getEntityTypeAs("Giant", LivingEntity::class)
        val ZOMBIE = Fountain.getEntityTypeAs("Zombie", LivingEntity::class)
        val SLIME = Fountain.getEntityTypeAs("Slime", LivingEntity::class)
        val GHAST = Fountain.getEntityTypeAs("Ghast", LivingEntity::class)
        val PIG_ZOMBIE = Fountain.getEntityTypeAs("PigZombie", LivingEntity::class)
        val ENDERMAN = Fountain.getEntityTypeAs("Enderman", LivingEntity::class)
        val CAVE_SPIDER = Fountain.getEntityTypeAs("CaveSpider", LivingEntity::class)
        val SILVERFISH = Fountain.getEntityTypeAs("Silverfish", LivingEntity::class)
        val BLAZE = Fountain.getEntityTypeAs("Blaze", LivingEntity::class)
        val MAGMA_CUBE = Fountain.getEntityTypeAs("LavaSlime", LivingEntity::class)
        val ENDER_DRAGON = Fountain.getEntityTypeAs("EnderDragon", LivingEntity::class)
        val WITHER = Fountain.getEntityTypeAs("WitherBoss", LivingEntity::class)
        val BAT = Fountain.getEntityTypeAs("Bat", LivingEntity::class)
        val WITCH = Fountain.getEntityTypeAs("Witch", LivingEntity::class)
        val ENDERMITE = Fountain.getEntityTypeAs("Endermite", LivingEntity::class)
        val GUARDIAN = Fountain.getEntityTypeAs("Guardian", LivingEntity::class)
        val SHULKER = Fountain.getEntityTypeAs("Shulker", LivingEntity::class)
        val PIG = Fountain.getEntityTypeAs("Pig", LivingEntity::class)
        val SHEEP = Fountain.getEntityTypeAs("Sheep", LivingEntity::class)
        val COW = Fountain.getEntityTypeAs("Cow", LivingEntity::class)
        val CHICKEN = Fountain.getEntityTypeAs("Chicken", LivingEntity::class)
        val SQUID = Fountain.getEntityTypeAs("Squid", LivingEntity::class)
        val WOLF = Fountain.getEntityTypeAs("Wolf", LivingEntity::class)
        val MUSHROOM_COW = Fountain.getEntityTypeAs("MushroomCow", LivingEntity::class)
        val SNOWMAN = Fountain.getEntityTypeAs("SnowMan", LivingEntity::class)
        val OCELOT = Fountain.getEntityTypeAs("Ozelot", LivingEntity::class)
        val IRON_GOLEM = Fountain.getEntityTypeAs("VillagerGolem", LivingEntity::class)
        val HORSE = Fountain.getEntityTypeAs("EntityHorse", LivingEntity::class)
        val RABBIT = Fountain.getEntityTypeAs("Rabbit", LivingEntity::class)
        val VILLAGER = Fountain.getEntityTypeAs("Villager", LivingEntity::class)
    }

}

fun <T: Entity> EntityType<*>.cast(entityClass: KClass<T>): EntityType<T> {
    require(entityClass.isSuperclassOf(this.entityClass)) { "Entity type $this is not a $entityClass" }
    @Suppress("UNCHECKED_CAST") // We checked ;)
    return this as EntityType<T>
}
