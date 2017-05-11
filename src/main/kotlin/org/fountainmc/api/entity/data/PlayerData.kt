package org.fountainmc.api.entity.data

import org.fountainmc.api.Fountain
import org.fountainmc.api.GameMode
import java.util.*

/**
 * The data of a player.
 */

interface PlayerData : LivingEntityData {

    /**
     * Get the name of the Player.

     * @return name of the Player
     */
    val name: String

    /**
     * Get the Unique Identifier of the Player.

     * @return UUID of the Player
     */
    val uniqueId: UUID

    /**
     * Get the game-mode the player is (or will be) in

     * @return the game-mode
     */
    var gameMode: GameMode

    var isFlying: Boolean

    var isFlyingAllowed: Boolean

    var percentageToNextExperienceLevel: Float

    var experienceLevel: Int

    var experienceData: ExperienceData
        get() = ExperienceData.create(experienceLevel, percentageToNextExperienceLevel)
        set(experienceData) {
            experienceData
            experienceLevel = experienceData.experienceLevel
            percentageToNextExperienceLevel = experienceData.percentageUntilNextExperienceLevel
        }

    /**
     * Calculate and return the player's total experience
     *
     * The experience in each level is given in

     * @return the player's total experience
     */
    var totalExperience: Long
        get() = experienceData.totalExperience
        set(total) {
            experienceData = ExperienceData.fromTotalExperience(total)
        }

    fun giveExp(amount: Long) {
        require(amount >= 0) { "Can't give negative exp $amount! Use takeExp if you want that!" }
        totalExperience += amount
    }

    fun takeExp(amount: Long) {
        require(amount >= 0) { "Can't take negative exp $amount! Use giveExp if you want that!" }
        val exp = totalExperience
        require(totalExperience >= amount) { "Can't take away $amount exp because the player only has $exp exp!" }
        totalExperience = exp - amount
    }

    override fun copyDataFrom(data: EntityData) {
        super.copyDataFrom(data)
        if (data is PlayerData) {
            this.gameMode = data.gameMode
            this.isFlying = data.isFlying
            this.isFlyingAllowed = data.isFlyingAllowed
            this.percentageToNextExperienceLevel = data.percentageToNextExperienceLevel
            this.experienceLevel = data.experienceLevel
        }
    }

    class ExperienceData private constructor(val experienceLevel: Int, val percentageUntilNextExperienceLevel: Float) {

        init {
            require(percentageUntilNextExperienceLevel < 1) {
                "Percentage until next experience level must be less than one: $percentageUntilNextExperienceLevel"
            }
            require(percentageUntilNextExperienceLevel >= 0) {
                "Percentage until next experience level can't be negative: $percentageUntilNextExperienceLevel"
            }
            require(experienceLevel >= 0) { "Experience level can't be negative: $experienceLevel" }
        }

        val totalExperience: Long
            get() = Fountain.calculateTotalExperience(this)

        companion object {
            val ZERO = ExperienceData.create(0, 0f)

            fun create(experienceLevel: Int, percentageUntilNextExperienceLevel: Float): ExperienceData {
                return ExperienceData(experienceLevel, percentageUntilNextExperienceLevel)
            }

            fun fromTotalExperience(totalExperience: Long): ExperienceData {
                require(totalExperience >= 0) { "Negative total experience $totalExperience" }
                return Fountain.calculateExperienceData(totalExperience)
            }
        }
    }
}
