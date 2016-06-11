package org.fountainmc.api.event.server;

import org.fountainmc.api.event.Event;

public class ServerStartEvent extends Event {

    private final int port;
    private final String serverOwner;
    private final String[] args;

    public ServerStartEvent(int port, String serverOwner, String[] args) {
        this.port = port;
        this.serverOwner = serverOwner;
        this.args = args;
    }

    public int getPort() {
        return port;
    }

    public String getServerOwner() {
        return serverOwner;
    }

    public String[] getArguments() {
        return args;
    }
}
