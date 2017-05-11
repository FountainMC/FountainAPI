package org.fountainmc.api.chat.events

import java.net.URL

/**
 * Specifies behavior to use when a component is clicked on.
 */
data class ClickEvent internal constructor(val action: ClickEvent.Action, val value: String) {
    enum class Action {
        OPEN_URL,
        OPEN_FILE,
        RUN_COMMAND,
        SUGGEST_COMMAND
    }

    companion object {

        /**
         * Creates a click event for opening a URL.
         *
         * @param url the URL to open when clicked
         * *
         * @return the click event
         */
        fun openUrl(url: URL): ClickEvent {
            return ClickEvent(Action.OPEN_URL, url.toString())
        }

        /**
         * Creates a click event for opening a URL.

         * @param url the URL to open when clicked
         * *
         * @return the click event
         */
        fun openUrl(url: String): ClickEvent {
            return ClickEvent(Action.OPEN_URL, url)
        }

        /**
         * Creates a click event for opening a file on the user's local computer.

         * @param file the file to open
         * *
         * @return the click event
         */
        fun openFile(file: String): ClickEvent {
            return ClickEvent(Action.OPEN_FILE, file)
        }

        /**
         * Creates a click event to run a command when the component is clicked.

         * @param command the command to run
         * *
         * @return the click event
         */
        fun runCommand(command: String): ClickEvent {
            return ClickEvent(Action.RUN_COMMAND, command)
        }

        /**
         * Creates a click event to fill a command to run when the component is
         * clicked.

         * @param command the command to suggest
         * *
         * @return the click event
         */
        fun suggestCommand(command: String): ClickEvent {
            return ClickEvent(Action.SUGGEST_COMMAND, command)
        }
    }

}
