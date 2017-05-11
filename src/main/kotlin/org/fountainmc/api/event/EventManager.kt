package org.fountainmc.api.event

interface EventManager {

    fun fire(event: Event)

    fun registerListener(`object`: Any)

}
