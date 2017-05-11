package org.fountainmc.api

/**
 * The singleton [Server] implementation,
 * which delegates all calls to [Fountain.server].
 */
@Suppress("DEPRECATION") // We're the one actually doing the delegation
object Fountain: Server by Fountain.server {

    @Volatile
    private var _server: Server? = null
    @Deprecated(
            message = "Server should be accessed through the 'Fountain' singleton",
            replaceWith = ReplaceWith("Fountain", "org.fountainmc.api.Fountain"),
            level = DeprecationLevel.WARNING
    )
    val server: Server
        get() = checkNotNull(_server) { "No server implementation currently present!" }

    /**
     * Set the specified server implementation as the delegate for this singleton.
     *
     * @throws IllegalStateException if the fountain implementation has already been set
     */
    fun setImplementation(implementation: Server) {
        synchronized(this) {
            val previousServer = this._server
            if (previousServer != null) {
                throw IllegalStateException(
                        "Can't set server implementation to ${implementation.javaClass.typeName}"
                                + " since it's already set to ${previousServer.javaClass.typeName}"
                )
            }
            _server = implementation
        }
    }
}
