package org.fountainmc.api.chat.values

/**
 * [Text] represents a plain text component value.
 */
data class Text internal constructor(
        /**
         * Returns the component's text.

         * @return the text in this value
         */
        val text: String
) : ComponentValue {
    override fun toPlainText() = text
    override fun toPlainText(buffer: StringBuilder) = buffer.apply { append(text) }

    override fun toString() = "Text($text)"

    companion object {

        /**
         * Creates a new [Text] instance.

         * @param text the text to use
         * *
         * @return the value
         */
        fun of(text: String): Text {
            return Text(text)
        }
    }

}
