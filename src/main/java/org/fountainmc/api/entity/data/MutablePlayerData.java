package org.fountainmc.api.entity.data;

import javax.annotation.Nonnegative;

import org.fountainmc.api.GameMode;
import org.fountainmc.api.NonnullByDefault;

import static com.google.common.base.Preconditions.*;

@NonnullByDefault
public interface MutablePlayerData extends MutableLivingEntityData, PlayerData {


    void setGameMode(GameMode gameMode);

    boolean setCanFly(boolean canFly);

    void setPercentageToNextExperienceLevel(@Nonnegative float percentage);

    void setExperienceLevel(@Nonnegative int experienceLevel);

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

    @Override
    default void copyDataFrom(EntityData data) {
        MutableLivingEntityData.super.copyDataFrom(data);
        if (data instanceof PlayerData) {
            this.setGameMode(((PlayerData) data).getGameMode());
            this.setCanFly(((PlayerData) data).canFly());
            this.setPercentageToNextExperienceLevel(((PlayerData) data).getPercentageToNextExperienceLevel());
            this.setExperienceLevel(((PlayerData) data).getExperienceLevel());
        }
    }
}
