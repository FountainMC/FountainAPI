package org.fountainmc.api.chat.events


import org.fountainmc.api.chat.Component
import java.util.*

/**
 * Specifies behavior whilst hovering over a chat component.
 */
class HoverEvent<out T: Any> internal constructor(val action: HoverEvent.Action, val value: T) {

    init {
        require(action.isAcceptable(value)) {
            "Value class ${value.javaClass} is not allowed by action $action"
        }
    }

    override fun equals(other: Any?) = when {
        other === this -> true
        other is HoverEvent<*> -> {
            this.action == other.action && this.value == other.value
        }
        else -> false
    }

    override fun hashCode() = Objects.hash(action, value)

    override fun toString(): String {
        return "HoverEvent{action=$action, value=$value}"
    }

    enum class Action(private val acceptableType: Class<*>) {
        SHOW_TEXT(Component::class.java),
        SHOW_ACHIEVEMENT(String::class.java),
        SHOW_ITEM(String::class.java);

        internal fun isAcceptable(o: Any): Boolean {
            return o.javaClass.isAssignableFrom(acceptableType)
        }
    }

    companion object {

        /**
         * Create a hover event that shows text when the component is hovered over.

         * @param component the component to display
         * *
         * @return the hover event
         */
        fun showText(component: Component<*>): HoverEvent<Component<*>> {
            return HoverEvent(Action.SHOW_ITEM, component)
        }

        /**
         * Create a hover event to show information on a Minecraft game achievement.

         * @param achievement the achievement to show
         * *
         * @return the hover event
         */
        fun showAchievement(achievement: String): HoverEvent<String> {
            return HoverEvent(Action.SHOW_ACHIEVEMENT, achievement)
        }

        /**
         * Create a hover event to show information on a given item.

         * @param item the item to show
         * *
         * @return the hover event
         */
        fun showItem(item: String): HoverEvent<String> {
            return HoverEvent(Action.SHOW_ITEM, item)
        }
    }

}
