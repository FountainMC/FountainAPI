@file:Suppress("NOTHING_TO_INLINE") // I know what i'm doing ;)
package org.fountainmc.api.internal.utils

import com.google.common.collect.ImmutableList
import com.google.common.collect.ImmutableMap
import com.google.common.collect.ImmutableSet
import com.google.common.collect.ImmutableSetMultimap
import com.google.common.primitives.Primitives
import com.google.common.util.concurrent.ListenableFuture
import com.google.common.util.concurrent.MoreExecutors
import java.util.concurrent.Executor

//
// Misc
//

internal inline fun <T> ListenableFuture<T>.addListener(
        executor: Executor = MoreExecutors.newDirectExecutorService(),
        crossinline listener: ListenableFuture<T>.() -> Unit
) {
    this.addListener(Runnable { listener(this@addListener) }, executor)
}

internal val Class<*>.isWrapperType: Boolean
    inline get() = Primitives.isWrapperType(this)
internal val <T> Class<T>.wrappedPrimitiveType: Class<T>
    get() {
        require(isWrapperType) { "$typeName is not a primitive wrapper type!" }
        return Primitives.unwrap(this)
    }
val <T> Class<T>.primitiveWrapperType: Class<T>
    get() {
        require(isPrimitive) { "$typeName is not a primitive type!" }
        return Primitives.wrap(this)
    }

//
// Collections
//

// Utils
operator fun <T> ImmutableList<T>.plus(other: Array<T>): ImmutableList<T> {
    val array = arrayOfNulls<Any>(this.size + other.size)
    check(this.toArray(array) === array)
    System.arraycopy(other, 0, array, this.size, other.size)
    @Suppress("UNCHECKED_CAST") // We can't use generic arrays
    return array.toImmutableList() as ImmutableList<T>
}
// Collection.toArray is missing in kotlin, and we can't use generic arrays
@Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN", "UNCHECKED_CAST")
operator fun <T> ImmutableList<T>.plus(other: Collection<T>): ImmutableList<T> {
    return this.plus((other as java.util.Collection<*>).toArray()) as ImmutableList<T>
}
inline operator fun <reified T> ImmutableList<T>.plus(element: T) = this.plus(arrayOf(element))

// Conversions

inline fun <T> Iterator<T>.toImmutableList(): ImmutableList<T> = ImmutableList.copyOf(this)!!
inline fun <T> Iterable<T>.toImmutableList(): ImmutableList<T> = ImmutableList.copyOf(this)!!
inline fun <T> Collection<T>.toImmutableList(): ImmutableList<T> = ImmutableList.copyOf(this)!!
inline fun <T> Array<out T>.toImmutableList(): ImmutableList<T> = ImmutableList.copyOf(this)!!

inline fun <T> Iterator<T>.toImmutableSet(): ImmutableSet<T> = ImmutableSet.copyOf(this)!!
inline fun <T> Iterable<T>.toImmutableSet(): ImmutableSet<T> = ImmutableSet.copyOf(this)!!
inline fun <T> Collection<T>.toImmutableSet(): ImmutableSet<T> = ImmutableSet.copyOf(this)!!
inline fun <T> Array<out T>.toImmutableSet(): ImmutableSet<T> = ImmutableSet.copyOf(this)!!

inline fun <K, V> Map<K, V>.toImmutableMap() = ImmutableMap.copyOf(this)!!

// Constructors
inline fun <T> emptyImmutableList() = immutableListOf<T>()
inline fun <T> emptyImmutableSet() = immutableSetOf<T>()

inline fun <T> immutableListOf(): ImmutableList<T> = ImmutableList.of<T>()!!
inline fun <T> immutableListOf(element: T): ImmutableList<T> = ImmutableList.of(element)!!
inline fun <T> immutableListOf(vararg elements: T): ImmutableList<T> = elements.toImmutableList()

inline fun <T> immutableSetOf(): ImmutableSet<T> = ImmutableSet.of<T>()!!
inline fun <T> immutableSetOf(element: T): ImmutableSet<T> = ImmutableSet.of(element)!!
inline fun <T> immutableSetOf(vararg elements: T): ImmutableSet<T> = elements.toImmutableSet()

inline fun <K, V> immutableMapOf(): ImmutableMap<K, V> = ImmutableMap.of<K, V>()!!
fun <K, V> immutableMapOf(vararg pairs: Pair<K, V>): ImmutableMap<K, V> {
    val builder = ImmutableMap.builder<K, V>()
    for (pair in pairs) {
        builder.put(pair.first, pair.second)
    }
    return builder.build()
}

inline fun <T> buildImmutableList(func: ImmutableList.Builder<T>.() -> Unit): ImmutableList<T> {
    return ImmutableList.builder<T>().apply(func).build()
}
inline fun <T> buildImmutableSet(func: ImmutableSet.Builder<T>.() -> Unit): ImmutableSet<T> {
    return ImmutableSet.builder<T>().apply(func).build()
}
inline fun <K, V> buildImmutableMap(func: ImmutableMap.Builder<K, V>.() -> Unit): ImmutableMap<K, V> {
    return ImmutableMap.builder<K, V>().apply(func).build()
}
inline fun <K, V> buildImmutableSetMultimap(func: ImmutableSetMultimap.Builder<K, V>.() -> Unit): ImmutableSetMultimap<K, V> {
    return ImmutableSetMultimap.builder<K, V>().apply(func).build()
}