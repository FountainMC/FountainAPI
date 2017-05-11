package org.fountainmc.api.command

import org.fountainmc.api.chat.Component

interface CommandSender {
    /**
     * Attempt to send the specified chat component to this sender,
     * preserving as much formatting as possible.
     *
     * Some formatting may be stripped depending on the capibilties of this sender.
     * For example, command consoles may not support colors or italics,
     * and almost definitely don't support hover events.
     *
     * @param component the chat component to send
     */
    fun sendMessage(component: Component<*>) = sendMessage(component.toPlainText())

    /**
     * Send a message to this [CommandSender].
     *
     * @param message the message to send
     */
    fun sendMessage(message: String)

    /**
     * Send the specified list of messages to this sender,
     * as if by invoking [sendMessage] repeatedly.
     *
     * @param messages the messages to send
     */
    fun sendMessages(vararg messages: String) {
        messages.forEach(this::sendMessage)
    }
}
