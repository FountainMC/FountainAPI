package org.fountainmc.api.event.server;

import org.fountainmc.api.Server;
import org.fountainmc.api.event.Event;

public interface ServerStartEvent extends Event {

    Server getServer();

}
