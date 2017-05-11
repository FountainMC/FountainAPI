package org.fountainmc.api.plugin

interface PluginManager {

    fun fromInstance(instance: Any): PluginContainer

    fun getPlugin(id: String): PluginContainer

    val plugins: Collection<PluginContainer>

    fun isLoaded(id: String): Boolean

}
