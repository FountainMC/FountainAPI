package org.fountainmc.api.command;

/**
 * Represents a command.
 * Alternative to using annotations.
 */
public abstract class AbstractCommand<T extends CommandSender> {
    public abstract String name();

    public String permission() {
        return "";
    }

    public String noPermissionMessage() {
        return "You do not have permission to run this command";
    }

    public String usage() {
        return "";
    }

    public String[] aliases() {
        return new String[0];
    }

    public String description() {
        return "";
    }

    public abstract Class<T> allow();

    public abstract void onExecute(T sender, String[] args);

}
