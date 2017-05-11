package org.fountainmc.api.chat.values

/**
 * This catch-all interface represents objects that may be used as a component
 * value. For instance, [Text] is a component value that represents plain
 * text.
 */
interface ComponentValue {
    fun toPlainText(): String = toPlainText(StringBuilder()).toString()
    fun toPlainText(buffer: StringBuilder): StringBuilder
}
