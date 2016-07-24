package org.fountainmc.api.entity.data;

import java.util.UUID;
import javax.annotation.Nonnegative;

import org.fountainmc.api.Fountain;
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

    boolean isFlying();

    void setFlying(boolean flying);

    boolean isFlyingAllowed();

    void setFlyingAllowed(boolean canFly);

    @Nonnegative
    float getPercentageToNextExperienceLevel();

    void setPercentageToNextExperienceLevel(@Nonnegative float percentage);

    @Nonnegative
    int getExperienceLevel();

    void setExperienceLevel(@Nonnegative int experienceLevel);

    default ExperienceData getExperienceData() {
        return ExperienceData.create(getExperienceLevel(), getPercentageToNextExperienceLevel());
    }

    default void setExperienceData(ExperienceData experienceData) {
        checkNotNull(experienceData, "Null experience data");
        setExperienceLevel(experienceData.getExperienceLevel());
        setPercentageToNextExperienceLevel(experienceData.getPercentageUntilNextExperienceLevel());
    }

    /**
     * Calculate and return the player's total experience
     * <p>The experience in each level is given in </p>
     *
     * @return the player's total experience
     */
    @Nonnegative
    default long getTotalExperience() {
        return getExperienceData().getTotalExperience();
    }

    default void setTotalExperience(long total) {
        setExperienceData(ExperienceData.fromTotalExperience(total));
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
            this.setFlying(((PlayerData) data).isFlying());
            this.setFlyingAllowed(((PlayerData) data).isFlyingAllowed());
            this.setPercentageToNextExperienceLevel(((PlayerData) data).getPercentageToNextExperienceLevel());
            this.setExperienceLevel(((PlayerData) data).getExperienceLevel());
        }
    }

    final class ExperienceData {
        public static final ExperienceData ZERO = ExperienceData.create(0, 0);
        private final float percentageUntilNextExperienceLevel;
        private final int experienceLevel;

        private ExperienceData(int experienceLevel, float percentageUntilNextExperienceLevel) {
            this.percentageUntilNextExperienceLevel = percentageUntilNextExperienceLevel;
            checkArgument(percentageUntilNextExperienceLevel < 1, "Percentage until next experience level %s is greater than or equal to 1", percentageUntilNextExperienceLevel);
            checkArgument(percentageUntilNextExperienceLevel >= 0, "Percentage until next experience level %s is negative", percentageUntilNextExperienceLevel);
            checkArgument(experienceLevel >= 0, "Experience level %s is negative", experienceLevel);
            this.experienceLevel = experienceLevel;
        }

        public float getPercentageUntilNextExperienceLevel() {
            return percentageUntilNextExperienceLevel;
        }

        public int getExperienceLevel() {
            return experienceLevel;
        }

        public long getTotalExperience() {
            return Fountain.getServer().calculateTotalExperience(this);
        }

        public static ExperienceData create(int experienceLevel, float percentageUntilNextExperienceLevel) {
            return new ExperienceData(experienceLevel, percentageUntilNextExperienceLevel);
        }

        public static ExperienceData fromTotalExperience(long totalExperience) {
            checkArgument(totalExperience >= 0, "Negative total experience %s", totalExperience);
            return Fountain.getServer().calculateExperienceData(totalExperience);
        }
    }
}
