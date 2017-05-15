package org.fountainmc.api

import com.google.common.collect.ImmutableList
import org.fountainmc.api.command.CommandManager
import org.fountainmc.api.enchantments.EnchantmentType
import org.fountainmc.api.entity.Entity
import org.fountainmc.api.entity.EntityType
import org.fountainmc.api.entity.Player
import org.fountainmc.api.entity.cast
import org.fountainmc.api.entity.data.EntityDataFactory
import org.fountainmc.api.entity.data.PlayerData
import org.fountainmc.api.event.EventManager
import org.fountainmc.api.inventory.item.ItemFactory
import org.fountainmc.api.plugin.PluginManager
import org.fountainmc.api.scheduler.Scheduler
import org.slf4j.ILoggerFactory
import org.slf4j.Logger
import kotlin.reflect.KClass

interface Server : ServerInfo {

    val pluginManager: PluginManager

    val commandManager: CommandManager

    val eventManager: EventManager

    val launchArguments: ImmutableList<String>

    fun getMaterial(name: String): Material<*>

    fun getMaterial(id: Int): Material<*>

    fun getBlockType(name: String): BlockType<*> {
        val material = getMaterial(name)
        require(material is BlockType<*>) { "$name is not a block!" }
        return material as BlockType<*>
    }

    fun getBlockType(id: Int): BlockType<*> {
        val material = getMaterial(id)
        require(material is BlockType<*>) { "$id is not a block!" }
        return material as BlockType<*>
    }

    fun getEntityType(name: String): EntityType<*>

    fun <T : Entity> getEntityTypeAs(name: String, entityType: KClass<T>) = getEntityType(name).cast(entityType)

    fun calculateTotalExperience(experienceData: PlayerData.ExperienceData): Long

    fun calculateExperienceData(totalExperience: Long): PlayerData.ExperienceData

    fun getEnchantmentTypeByName(name: String): EnchantmentType

    val itemFactory: ItemFactory

    val onlinePlayerCount: Int

    val onlinePlayers: List<Player>

    val blockingScheduler: Scheduler

    val asynchronousScheduler: Scheduler

    val entityDataFactory: EntityDataFactory

    val loggerFactory: ILoggerFactory

    @Deprecated(
            message = "Plugins should use their own logger",
            replaceWith = ReplaceWith("currentPluginLogger", "org.fountainmc.api.utils.currentPluginLogger")
    )
    val serverLogger: Logger
}

