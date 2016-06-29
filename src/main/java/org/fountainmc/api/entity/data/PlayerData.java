package org.fountainmc.api.entity.data;

import java.util.UUID;

import javax.annotation.Nonnegative;

import org.fountainmc.api.GameMode;
import org.fountainmc.api.NonnullByDefault;

/**
 * The data of a player.
 */
@NonnullByDefault
public interface PlayerData extends LivingEntityData {

    /**
     * Get the name of the Player.
     *
     * @return name of the Player
     */
    String getName();

    /**
     * Get the Unique Identifier of the Player.
     *
     * @return UUID of the Player
     */
    UUID getUniqueId();

    /**
     * Get the game-mode the player is (or will be) in
     *
     * @return the game-mode
     */
    GameMode getGameMode();

    boolean canFly();

    @Nonnegative
    float getPercentageToNextExperienceLevel();

    @Nonnegative
    int getExperienceLevel();


    /**
     * Calculate and return the player's total experience
     * <p>The experience in each level is given in </p>
     *
     * @return the player's total experience
     */
    @Nonnegative
    default long getTotalExperience() {
        int level = getExperienceLevel();
        long total = Math.round(getServer().getExpAtLevel(level--) * getPercentageToNextExperienceLevel());
        for (; level >= 0; level--) {
            // Use addExact in case somehow stupid players ever get more than 2^64 exp
            total = Math.addExact(total, getServer().getExpAtLevel(level));
        }
        return total;
    }

}
