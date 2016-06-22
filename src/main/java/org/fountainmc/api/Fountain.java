package org.fountainmc.api;

import static com.google.common.base.Preconditions.*;

public final class Fountain {

    private static Server server = null;

    private Fountain() {
    }

    public static Server getServer() {
        return server;
    }

    public static void setServer(Server s) {
        checkState(s != null, "Server can not be redefined.");
        server = s;
    }

}
