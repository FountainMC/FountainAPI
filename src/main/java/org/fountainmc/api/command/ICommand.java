package org.fountainmc.api.command;

/**
 * Represents a command.
 * Alternative to using annotations.
 */
public interface ICommand<T extends CommandSender> {
    public String name();

    default String permission() {
        return "";
    }

    default String noPermissionMessage() {
        return "You do not have permission to run this command";
    }

    default String[] aliases() {
        return new String[0];
    }

    default String description() {
        return "";
    }

    Class<T> allow();

    public void onExecute(T sender, String[] args);

}
