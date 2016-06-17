package org.fountainmc.api.event.server;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.fountainmc.api.Server;
import org.fountainmc.api.event.AbstractCancellable;
import org.fountainmc.api.event.Event;

import static com.google.common.base.Preconditions.*;

@ParametersAreNonnullByDefault
public interface ServerStartEvent extends Event {

    @Nonnull
    Server getServer();

    public static ServerStartEvent create(Server server) {
        checkNotNull(server, "Null server");
        return () -> server;
    }

}
