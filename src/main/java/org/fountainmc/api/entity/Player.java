package org.fountainmc.api.entity;

import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.common.base.Preconditions;

import org.fountainmc.api.command.CommandSender;

import static com.google.common.base.Preconditions.checkNotNull;

@ParametersAreNonnullByDefault
public interface Player extends EntityLiving, CommandSender {

    @Nonnull
    public String getName();

    @Nonnull
    public UUID getUUID();

    public void sendMessage(String message);

    public default void sendMessages(String... messages) {
        for (int i = 0; i < checkNotNull(messages, "Null messages array").length; i++) {
            String message = messages[i];
            checkNotNull(message, "Null message at index %s", i);
            sendMessage(message);
        }
    }
}
