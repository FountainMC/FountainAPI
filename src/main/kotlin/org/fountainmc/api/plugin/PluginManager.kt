package org.fountainmc.api.plugin

import org.slf4j.Logger

interface PluginManager {

    fun fromInstance(instance: Any): PluginContainer

    fun getPlugin(id: String): PluginContainer

    val plugins: Set<PluginContainer>

    fun isLoaded(id: String): Boolean

    fun getPluginLogger(id: String): Logger

    fun pluginForClass(type: Class<*>): PluginContainer?

    @sun.reflect.CallerSensitive // Use the sun.reflect magic when possible for faster performance
    fun currentPlugin(): PluginContainer? {
        var plugin = try {
            pluginForClass(sun.reflect.Reflection.getCallerClass())
        } catch (ignored: Throwable) { null }
        if (plugin == null) {
            val stackTrace = Exception().stackTrace
            /*
             * Start looking at index two, since that's the class that called us.
             * We don't care if we already looked in Reflection.getCallerClass(), since we're in the slow path anyways.
             * This method is relatively fast if the plugin itself calls this, since it'd be able to use the fast Reflection.getCallerClass().
             * However if it's called from API code, this has to look beyond the immediate caller with Thread.stackTrace which is hundreds of times slower.
             * Therefore, although this is a handy utility in plugin code, it should be avoided from API code since it's really really slow.
             * There are still valid uses, even in API code, like warning the server owner when a plugin is doing a deprecated or dangerous operation.
             */
            var index = 2
            while (plugin == null && index < stackTrace.size) {
                val stackTraceElement = stackTrace[index]
                try {
                    plugin = pluginForClass(Class.forName(stackTraceElement.className))
                } catch (ignored: ClassNotFoundException) {}
                index += 1
            }
        }
        return plugin
    }
}
