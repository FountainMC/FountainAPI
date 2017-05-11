package org.fountainmc.api

import org.fountainmc.api.inventory.item.Item
import org.fountainmc.api.inventory.item.MutableItem
import kotlin.reflect.KClass

/**
 * Get the material
 *
 * Plugins should keep in mind that forge mods may add new
 * materials.
 */
interface Material<out T: Item> {
    val server: Server

    val id: Int

    val name: String

    val isBlock: Boolean

    val isEdible: Boolean

    /**
     * Return if the material is present in unmodified vanilla minecraft

     * @return if in vanilla minecraft
     */
    val isVanilla: Boolean
        get() = name.startsWith("minecraft:")

    val itemClass: KClass<out T>
    val mutableItemClass: KClass<out T>

    fun createMutableItem(): MutableItem = server.itemFactory.createMutableItem(this)
}
