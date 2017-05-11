package org.fountainmc.api.command

/**
 * Represents a registry handler.
 * A registry handler is a listener intended for internal use for implementations.
 * Allows to register plugins on [CommandManager.registerCommands] and [CommandManager.registerCommand].
 */
interface RegistryHandler {

    fun onRegister(iCommand: AbstractCommand<*>)

    fun onRegister(commandHandler: CommandManager.CommandHandler)

}
