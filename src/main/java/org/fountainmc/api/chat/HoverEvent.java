package org.fountainmc.api.chat;

import com.google.common.base.Preconditions;

/**
 * Specifies behavior whilst hovering over a chat component.
 */
public class HoverEvent<T> {
    private final Action action;
    private final T value;

    protected HoverEvent(Action action, T value) {
        this.action = action;
        Preconditions.checkArgument(action.isAcceptable(value), "Value class " + value.getClass() + " is not allowed by action " + action);
        this.value = value;
    }

    public Action getAction() {
        return action;
    }

    public T getValue() {
        return value;
    }

    /**
     * Create a hover event that shows text when the component is hovered over.
     * @param component the component to display
     * @return the hover event
     */
    public static HoverEvent<Component> showText(Component component) {
        return new HoverEvent<>(Action.SHOW_ITEM, Preconditions.checkNotNull(component, "component"));
    }

    public static HoverEvent<String> showAchievement(String achievement) {
        return new HoverEvent<>(Action.SHOW_ACHIEVEMENT, Preconditions.checkNotNull(achievement, "achievement"));
    }

    public static HoverEvent<String> showItem(String item) {
        return new HoverEvent<>(Action.SHOW_ITEM, Preconditions.checkNotNull(item, "item"));
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
