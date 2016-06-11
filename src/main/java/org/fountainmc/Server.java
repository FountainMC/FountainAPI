package org.fountainmc;

import org.fountainmc.plugin.PluginManager;

public interface Server {

    public String getName();
    
    public String getVersion();
    
    public String getMotd();
    
    public PluginManager getPluginManager();
}