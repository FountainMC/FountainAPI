package org.fountainmc.api.entity;

import java.util.UUID;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.common.collect.ImmutableCollection;

import org.fountainmc.api.command.CommandSender;

import static com.google.common.base.Preconditions.*;

/**
 * A connected player.
 */
@ParametersAreNonnullByDefault
public interface Player extends LivingEntity, CommandSender {

    /**
     * Get the name of the Player.
     *
     * @return name of the Player
     */
    @Nonnull
    String getName();

    /**
     * Get the Unique Identifier of the Player.
     *
     * @return UUID of the Player
     */
    @Nonnull
    UUID getUniqueId();

    /**
     * Send a chat message to the Player
     *
     * @param message String to send
     */
    void sendMessage(String message);

    /**
     * Send multiple messages to the Player
     *
     * @param messages Strings to send
     */
    default void sendMessages(String... messages) {
        for (int i = 0; i < checkNotNull(messages, "Null messages array").length; i++) {
            String message = messages[i];
            checkNotNull(message, "Null message at index %s", i);
            sendMessage(message);
        }
    }

    /**
     * Hide an Entity from the Player's view.
     *
     * @param entity Entity to hide
     */
    void hide(Entity entity);


    /**
     * Return if the player can see a specified entity
     *
     * @param entity the entity to check if the player can see
     * @return if the player can see the specified entity
     */
    boolean canSee(Entity entity);

    ImmutableCollection<? extends Entity> getHiddenEntities();

    /**
     * Return if the player is still connected to the server
     * <p>Returns false for NPCs</p>
     *
     * @return if connected
     */
    boolean isConnected();

    @Override
    @Nonnull
    default EntityType<Player> getEntityType() {
        return EntityType.PLAYER;
    }

    void setGameMode(GameMode gameMode);

    @Nonnull
    GameMode getGameMode();

    boolean setCanFly(boolean canFly);

    boolean canFly();

    @Nonnegative
    float getPercentageToNextExperienceLevel();

    void setPercentageToNextExperienceLevel(@Nonnegative float percentage);

    @Nonnegative
    int getExperienceLevel();

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

    enum GameMode {
        CREATIVE,
        SURVIVAL,
        ADVENTURE,
        SPECTATING;
    }

}
