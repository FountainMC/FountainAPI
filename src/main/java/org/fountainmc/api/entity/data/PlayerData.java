package org.fountainmc.api.entity.data;

import java.util.UUID;
import javax.annotation.Nonnegative;

import org.fountainmc.api.GameMode;
import org.fountainmc.api.NonnullByDefault;

import static com.google.common.base.Preconditions.*;

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

    void setGameMode(GameMode gameMode);

    boolean canFly();

    boolean setCanFly(boolean canFly);

    @Nonnegative
    float getPercentageToNextExperienceLevel();

    void setPercentageToNextExperienceLevel(@Nonnegative float percentage);

    @Nonnegative
    int getExperienceLevel();

    void setExperienceLevel(@Nonnegative int experienceLevel);

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

    default void setTotalExperience(long total) {
        checkArgument(total >= 0, "Negative experience %s", total);
        int level = 0;
        while (true) {
            long expAtLevel = getServer().getExpAtLevel(level);
            if (expAtLevel <= total) {
                total -= expAtLevel;
                level++;
            } else {
                setPercentageToNextExperienceLevel((float) ((double) total / (double) expAtLevel));
                break;
            }
        }
        setExperienceLevel(level);
    }

    default void giveExp(@Nonnegative long amount) {
        checkArgument(amount >= 0, "Can't give negative exp %s! Use takeExp(long) if you want that!", amount);
        setTotalExperience(getTotalExperience() + amount);
    }

    default void takeExp(@Nonnegative long amount) {
        checkArgument(amount >= 0, "Can't give negative exp %s! Use takeExp(long) if you want that!", amount);
        long exp = getTotalExperience();
        checkState(exp >= amount, "Can't take away %s exp because the player only has %s exp.", amount, exp);
        setTotalExperience(exp - amount);
    }

    @Override
    default void copyDataFrom(EntityData data) {
        LivingEntityData.super.copyDataFrom(data);
        if (data instanceof PlayerData) {
            this.setGameMode(((PlayerData) data).getGameMode());
            this.setCanFly(((PlayerData) data).canFly());
            this.setPercentageToNextExperienceLevel(((PlayerData) data).getPercentageToNextExperienceLevel());
            this.setExperienceLevel(((PlayerData) data).getExperienceLevel());
        }
    }
}
