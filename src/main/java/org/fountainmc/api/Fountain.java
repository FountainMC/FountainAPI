package org.fountainmc.api;

public final class Fountain {

    private static Server server = null;

    private Fountain() {}

    public static Server getServer() {
        return server;
    }

    public static void setServer(Server s) {
        if(server != null) throw new IllegalArgumentException("Server can not be redefined.");
        server = s;
    }
}