package org.fountainmc.api.command;

/**
 * Represents a registry handler.
 * A registry handler is a listener intended for internal use for implementations.
 * Allows to register plugins on {@link CommandManager}#registerCommands(Object source) and {@link CommandManager}#registerCommand(ICommand command).
 */
public interface RegistryHandler {
    void onRegister(AbstractCommand iCommand);

    void onRegister(CommandManager.CommandHandler commandHandler);
}
