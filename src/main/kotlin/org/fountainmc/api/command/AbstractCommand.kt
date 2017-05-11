package org.fountainmc.api.command

import com.google.common.collect.ImmutableList
import org.fountainmc.api.internal.utils.emptyImmutableList

/**
 * Represents a command.
 * Alternative to using annotations.
 */
abstract class AbstractCommand<in T : CommandSender> {

    abstract fun name(): String

    val permission: String?
        get() = null

    val noPermissionMessage
        get() = "You do not have permission to run this command"

    val usage: String?
        get() = null

    val aliases: ImmutableList<String>
        get() = emptyImmutableList()

    val description: String?
        get() = null

    abstract fun allow(): Class<@UnsafeVariance T>

    abstract fun onExecute(sender: T, args: Array<String>)

}
