package org.fountainmc.api;

import javax.annotation.Nonnull;

import com.google.common.collect.ImmutableList;

import org.fountainmc.api.plugin.PluginManager;
import org.fountainmc.api.command.CommandManager;
import org.fountainmc.api.scheduler.Scheduler;

import static com.google.common.base.Preconditions.*;

@Nonnull
public interface Server extends ServerInfo {

    public PluginManager getPluginManager();
    
    public CommandManager getCommandManager();

    public ImmutableList<String> getLaunchArguments();

    public Material getMaterial(String name);

    public default BlockType getBlockType(String name) {
        Material material = getMaterial(name);
        checkArgument(material instanceof BlockType, "%s is not a block!", name);
        return (BlockType) material;
    }

    public Scheduler getBlockingScheduler();

    public Scheduler getAsynchronousScheduler();
}