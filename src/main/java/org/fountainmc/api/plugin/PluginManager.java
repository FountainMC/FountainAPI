package org.fountainmc.api.plugin;

import java.util.Collection;

public interface PluginManager {

    PluginContainer fromInstance(Object instance);

    PluginContainer getPlugin(String id);

    Collection<PluginContainer> getPlugins();

    boolean isLoaded(String id);

}
