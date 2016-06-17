package org.fountainmc.api.event;

public abstract class AbstractCancellable implements Cancellable {
    private boolean cancelled = false;

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean value) {
        this.cancelled = value;
    }
}
