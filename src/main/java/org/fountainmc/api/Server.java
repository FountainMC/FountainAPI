package org.fountainmc.api;

import org.fountainmc.api.plugin.PluginManager;

public interface Server {

    public ServerInfo getServerInfo();

    public PluginManager getPluginManager();

    public String[] getLaunchArguments();
}