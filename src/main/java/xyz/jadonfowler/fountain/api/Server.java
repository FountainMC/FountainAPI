package xyz.jadonfowler.fountain.api;

import xyz.jadonfowler.fountain.api.plugin.PluginManager;

public interface Server {

    public String getName();
    
    public String getVersion();
    
    public String getMotd();
    
    public PluginManager getPluginManager();
}