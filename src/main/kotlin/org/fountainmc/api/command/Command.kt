package org.fountainmc.api.command

import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
annotation class Command(val name: String, val permission: String = "", val noPermissionMessage: String = "You do not have permission to run this command", val aliases: Array<String> = arrayOf(), val description: String = "", val usage: String = "", val allow: KClass<out CommandSender> = CommandSender::class)
