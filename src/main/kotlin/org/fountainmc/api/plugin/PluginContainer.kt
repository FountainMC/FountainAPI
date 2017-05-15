package org.fountainmc.api.plugin

import org.slf4j.Logger

/**
 * A wrapper around a plugin.
 */
interface PluginContainer {

    val id: String

    val name: String

    val version: String

    val description: String

    val url: String

    val author: Array<String>

    val instance: Any

    val logger: Logger
}
