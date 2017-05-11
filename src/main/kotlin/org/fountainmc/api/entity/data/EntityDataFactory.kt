package org.fountainmc.api.entity.data

import java.util.UUID
import javax.annotation.concurrent.ThreadSafe

import org.fountainmc.api.GameMode
import org.fountainmc.api.Server
import org.fountainmc.api.entity.EntityType
import org.fountainmc.api.entity.LivingEntity

// TODO: Consider builder pattern?
@ThreadSafe
interface EntityDataFactory {

    val server: Server

    fun createEntityData(
            type: EntityType<*>,
            pitch: Float = 0.0f,
            yaw: Float = 0.0f,
            passengers: List<EntityData> = emptyList(),
            ticksOnFire: Int = 0,
            immuneToFire: Boolean = false
    ): EntityData

    fun createLivingEntityData(
            type: EntityType<LivingEntity>,
            entityData: EntityData = createEntityData(type),
            health: Double = maxHealth,
            maxHealth: Double = entityData.entityType.maxHealth
    ): LivingEntityData

    fun createPlayerData(
            entityData: LivingEntityData = createLivingEntityData(type = EntityType.PLAYER),
            name: String,
            uniqueId: UUID,
            gameMode: GameMode = GameMode.SURVIVAL,
            canFly: Boolean = false,
            percentageToNextExperienceLevel: Float = 0.0f,
            experienceLevel: Int = 0
    ): PlayerData
}
