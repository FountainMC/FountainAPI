@file:Suppress("NOTHING_TO_INLINE")
package org.fountainmc.api.internal.utils

import java.lang.invoke.MethodHandle
import java.lang.invoke.MethodHandles
import java.lang.invoke.MethodType
import java.lang.reflect.Member
import java.lang.reflect.Method
import java.lang.reflect.Modifier
import kotlin.reflect.KClass

// Shiny MethodHandle API

inline val Method.methodType: MethodType
    get() = MethodType.methodType(returnType, parameterTypes)

fun Method.toMethodHandle(setAccessible: Boolean = false): MethodHandle {
    if (setAccessible) this.isAccessible = true
    return MethodHandles.publicLookup().unreflect(this)
}

inline operator fun MethodType.get(index: Int): Class<*> = parameterType(index)

fun createMethodType(
        returnType: KClass<*> = Void.TYPE.kotlin,
        vararg parameterTypes: KClass<*>
): MethodType = MethodType.methodType(returnType.java, parameterTypes.map { it.java })

// Traditional reflection API utilities

val Method.qualifiedName: String
    get() = "${declaringClass.typeName}.$name"

// Modifier checks
inline val Member.isPublic: Boolean
    get() = Modifier.isPublic(modifiers)
