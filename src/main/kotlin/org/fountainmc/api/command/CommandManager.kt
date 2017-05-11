package org.fountainmc.api.command

import org.fountainmc.api.internal.utils.*
import java.lang.invoke.MethodHandle
import java.lang.invoke.MethodType
import java.lang.invoke.WrongMethodTypeException
import java.lang.reflect.Method
import java.lang.reflect.Modifier
import java.util.*
import kotlin.reflect.full.isSubclassOf

class CommandManager {

    private val commands: MutableList<CommandHandler>
    private val dynamicCommands: MutableList<AbstractCommand<*>>
    private val handlers: MutableList<RegistryHandler>

    init {
        this.commands = ArrayList<CommandHandler>()
        this.dynamicCommands = ArrayList<AbstractCommand<*>>()
        this.handlers = ArrayList<RegistryHandler>()
    }

    fun registerCommands(source: Any) {
        for (method in source.javaClass.declaredMethods) {
            if (Modifier.isPublic(method.modifiers)) {
                if (method.isAnnotationPresent(Command::class.java)) {
                    val command = method.getAnnotation(Command::class.java)
                    val cmdHandler = CommandHandler(command, method, source)
                    commands.add(cmdHandler)
                    for (handler in handlers) {
                        handler.onRegister(cmdHandler)
                    }
                }
            }
        }
    }

    fun registerHandler(handler: RegistryHandler) {
        this.handlers.add(handler)
    }

    internal fun registerCommand(abstractCommand: AbstractCommand<*>) {
        for (handler in handlers) {
            handler.onRegister(abstractCommand)
        }
        dynamicCommands.add(abstractCommand)
    }

    fun fireCommand(command: String, arguments: Array<String>, sender: CommandSender) {
        for (handler in commands) {
            if (handler.command.name.equals(command, ignoreCase = true)) {
                fireCommand(handler, arguments, sender)
                return
            }
        }
        for (dynCmd in dynamicCommands) {
            if (dynCmd.name().equals(command, ignoreCase = true)) {
                fireCommand(dynCmd, arguments, sender)
                return
            }
        }
    }

    fun <T: CommandSender> fireCommand(command: AbstractCommand<T>, arguments: Array<String>, sender: CommandSender) {
        val senderClass = command.allow()
        if (senderClass.isAssignableFrom(sender.javaClass)) {
            command.onExecute(senderClass.cast(sender), arguments)
        }
    }

    fun fireCommand(handler: CommandHandler, arguments: Array<String>, sender: CommandSender) {
        val allowedSenderClass = handler.command.allow
        if (!allowedSenderClass.isInstance(sender)) {
            sender.sendMessage("[ERROR] Only ${allowedSenderClass.simpleName} may execute this command!")
            return
        }
        try {
            @Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN", "USELESS_CAST")
            handler.methodHandle.invokeExact(
                    handler.source as java.lang.Object,
                    arguments as Array<String>,
                    sender
            )
        } catch (e: Throwable) {
            sender.sendMessage("[ERROR] Unexpected ${e.javaClass.simpleName} while executing command!")
            // TODO: Implement proper logging code
            // TODO: Figure out how to determine the plugin to blame
            System.err.println("[ERROR] Unexpected ${e.javaClass.simpleName} while executing command '${handler.command.name}' via ${handler.source.javaClass.typeName}")
            e.printStackTrace()
        }
    }

    fun getCommands(): List<CommandHandler> {
        return commands
    }

    fun getDynamicCommands(): List<AbstractCommand<*>> {
        return dynamicCommands
    }

    class CommandHandler(val command: Command, val method: Method, val source: Any) {
        val methodHandle: MethodHandle
        init {
            require(method.isPublic) { "${method.qualifiedName} isn't publicly accessible" }
            try {
                methodHandle = method.toMethodHandle().asType(HANDLER_METHOD_TYPE)!!
            } catch (e: WrongMethodTypeException) {
                throw IllegalArgumentException("${method.qualifiedName} arguments can't be cast from ${HANDLER_METHOD_TYPE.toMethodDescriptorString()}")
            }
            val senderArgumentType = method.methodType[2].kotlin
            require(command.allow.isSubclassOf(senderArgumentType)) {
                "Allowed sender type ${command.allow.simpleName} must be a subtype of method argument $senderArgumentType"
            }
        }
        companion object {
            val HANDLER_METHOD_TYPE: MethodType = createMethodType(Any::class, Array<String>::class, CommandSender::class)
        }
    }
}
