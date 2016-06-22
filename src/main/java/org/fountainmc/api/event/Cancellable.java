package org.fountainmc.api.event;

public interface Cancellable {

    boolean isCancelled();

    void setCancelled(boolean value);

}
