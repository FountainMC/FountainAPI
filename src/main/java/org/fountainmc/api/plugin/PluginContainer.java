package org.fountainmc.api.plugin;

/**
 * A wrapper around a plugin.
 */
public interface PluginContainer {

    String getId();

    String getName();

    String getVersion();

    String getDescription();

    String getUrl();

    String[] getAuthor();

    Object getInstance();

}
