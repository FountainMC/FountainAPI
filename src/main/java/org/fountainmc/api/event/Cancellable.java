package org.fountainmc.api.event;


public interface Cancellable {

    public boolean isCancelled();
    
    public void setCancelled(boolean value);
    
}
