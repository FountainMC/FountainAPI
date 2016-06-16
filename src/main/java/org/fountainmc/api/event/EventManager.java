package org.fountainmc.api.event;

public interface EventManager {

    void fire(Event event);

    void registerListener(Object object);

}
