package org.fountainmc.api.chat.values

import com.google.common.collect.ImmutableList
import org.fountainmc.api.internal.utils.immutableListOf
import org.fountainmc.api.internal.utils.toImmutableList

/**
 * Represents a translatable Minecraft message.
 */
data class Translatable internal constructor(
        /**
         * The message ID for this translatable.
         */
        val message: String,
        /**
         * An immutable list of substitutions used for the message.
         */
        val substitutions: List<String>
) : ComponentValue {

    /**
     * Creates a new instance of `Translatable` with new substitutions.
     * @param substitutions substitutions to use
     * *
     * @return a new `Translatable` with new substitutions
     */
    fun withSubstitutions(substitutions: Collection<String>): Translatable {
        return copy(substitutions = substitutions.toImmutableList())
    }

    /**
     * Creates a new instance of `Translatable` with new substitutions.
     * @param substitutions substitutions to use
     * *
     * @return a new `Translatable` with new substitutions
     */
    fun withSubstitutions(vararg substitutions: String): Translatable {
        return Translatable(message, ImmutableList.copyOf(substitutions))
    }

    override fun toPlainText(buffer: StringBuilder) = buffer.also {
        // TODO: Actually utilize translation data instead of printing a debug string
        it.append("\${")
        it.append(message)
        if (substitutions.isNotEmpty()) {
            buffer.append("::")
            substitutions.joinTo(
                    buffer = it,
                    separator = ",",
                    prefix = "[",
                    postfix = "]"
            ) { '"' + it + '"' }
        }
        it.append("}")
    }

    override fun toString() = "Translatable" + toPlainText()

    companion object {

        /**
         * Creates a translatable message with a specific message ID (i.e. demo.day.2).
         * @param message the message ID to use
         * *
         * @return a Translatable
         */
        fun withId(message: String): Translatable {
            return Translatable(message, immutableListOf())
        }
    }

}
