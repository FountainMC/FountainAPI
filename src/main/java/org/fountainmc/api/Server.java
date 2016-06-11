package org.fountainmc.api;

import org.fountainmc.api.plugin.PluginManager;

public interface Server {

    public String getName();
    
    public String getVersion();
    
    public String getMotd();
    
    public PluginManager getPluginManager();
}