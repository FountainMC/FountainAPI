package org.fountainmc.api.chat.events;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

import org.fountainmc.api.chat.Component;

/**
 * Specifies behavior whilst hovering over a chat component.
 */
public class HoverEvent<T> {

    private final Action action;
    private final T value;

    HoverEvent(Action action, T value) {
        this.action = action;
        Preconditions.checkArgument(action.isAcceptable(value), "Value class " + value.getClass() + " is not allowed by action " + action);
        this.value = value;
    }

    /**
     * Create a hover event that shows text when the component is hovered over.
     *
     * @param component the component to display
     * @return the hover event
     */
    public static HoverEvent<Component<?>> showText(Component<?> component) {
        return new HoverEvent<>(Action.SHOW_ITEM, Preconditions.checkNotNull(component, "component"));
    }

    /**
     * Create a hover event to show information on a Minecraft game achievement.
     *
     * @param achievement the achievement to show
     * @return the hover event
     */
    public static HoverEvent<String> showAchievement(String achievement) {
        return new HoverEvent<>(Action.SHOW_ACHIEVEMENT, Preconditions.checkNotNull(achievement, "achievement"));
    }

    /**
     * Create a hover event to show information on a given item.
     *
     * @param item the item to show
     * @return the hover event
     */
    public static HoverEvent<String> showItem(String item) {
        return new HoverEvent<>(Action.SHOW_ITEM, Preconditions.checkNotNull(item, "item"));
    }

    public Action getAction() {
        return action;
    }

    public T getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HoverEvent<?> that = (HoverEvent<?>) o;
        return action == that.action && Objects.equal(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(action, value);
    }

    @Override
    public String toString() {
        return "HoverEvent{"
                + "action=" + action
                + ", value=" + value
                + '}';
    }

    public enum Action {
        SHOW_TEXT(Component.class),
        SHOW_ACHIEVEMENT(String.class),
        SHOW_ITEM(String.class);

        private final Class<?> acceptableType;

        Action(Class<?> acceptableType) {
            this.acceptableType = acceptableType;
        }

        boolean isAcceptable(Object o) {
            return o.getClass().isAssignableFrom(acceptableType);
        }
    }

}
