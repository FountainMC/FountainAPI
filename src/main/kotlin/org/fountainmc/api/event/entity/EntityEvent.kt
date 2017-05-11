package org.fountainmc.api.event.entity

import org.fountainmc.api.entity.Entity
import org.fountainmc.api.event.Event

interface EntityEvent : Event {

    val entity: Entity

}
