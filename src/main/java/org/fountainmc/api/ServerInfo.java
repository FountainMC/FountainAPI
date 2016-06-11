package org.fountainmc.api;

import java.net.InetSocketAddress;

public interface ServerInfo {
    String getName();

    String getVersion();

    String getMotd();

    int getMaxPlayers();

    String getOwner();

    InetSocketAddress getAddress();
}
