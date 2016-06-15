package org.fountainmc.api.event;

public class CancellableBase extends Event implements Cancellable {

    private boolean cancelled;

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean value) {
        cancelled = value;
    }

}
