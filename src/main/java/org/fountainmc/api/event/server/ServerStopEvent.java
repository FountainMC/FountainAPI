package org.fountainmc.api.event.server;

import javax.annotation.Nonnull;

import org.fountainmc.api.Server;
import org.fountainmc.api.event.Event;

import static com.google.common.base.Preconditions.checkNotNull;

public interface ServerStopEvent extends Event {

    @Nonnull
    public Server getServer();

    public static ServerStopEvent create(Server server) {
        checkNotNull(server, "Null server");
        return () -> server;
    }

}
