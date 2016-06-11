package org.fountainmc.api.event.server;

import org.fountainmc.api.Server;
import org.fountainmc.api.event.Event;

public class ServerStartEvent extends Event {

    private final Server server;

    public ServerStartEvent(Server server) {
        this.server = server;
    }

    public Server getServer() {
        return server;
    }
}
