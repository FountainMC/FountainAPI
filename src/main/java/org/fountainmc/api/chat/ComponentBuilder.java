package org.fountainmc.api.chat;

import com.google.common.base.Preconditions;
import org.fountainmc.api.Color;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.*;

public class ComponentBuilder {
    private TextComponent currentComponent = TextComponent.EMPTY;
    private final List<TextComponent> components = new ArrayList<>();

    private ComponentBuilder(String first) {
        currentComponent = currentComponent.withText(checkNotNull(first, "first"));
    }

    public ComponentBuilder then(String text) {
        currentComponent = currentComponent.withText(checkNotNull(text, "text"));
        components.add(currentComponent);
        return this;
    }

    public ComponentBuilder color(Color color) {
        currentComponent = currentComponent.withColor(checkNotNull(color, "color"));
        return this;
    }

    public ComponentBuilder insertion(String insertion) {
        currentComponent = currentComponent.withInsertion(checkNotNull(insertion, "insertion"));
        return this;
    }

    public ComponentBuilder hover(HoverEvent<?> event) {
        currentComponent = currentComponent.withHoverEvent(checkNotNull(event, "event"));
        return this;
    }

    public ComponentBuilder click(ClickEvent event) {
        currentComponent = currentComponent.withClickEvent(checkNotNull(event, "event"));
        return this;
    }

    public ComponentBuilder bold(boolean bold) {
        currentComponent = currentComponent.withBold(bold);
        return this;
    }

    public ComponentBuilder underlined(boolean underlined) {
        currentComponent = currentComponent.withUnderlined(underlined);
        return this;
    }

    public ComponentBuilder obfuscated(boolean obfuscated) {
        currentComponent = currentComponent.withObfuscated(obfuscated);
        return this;
    }

    public ComponentBuilder strikethrough(boolean strikethrough) {
        currentComponent = currentComponent.withStrikethrough(strikethrough);
        return this;
    }

    public ComponentBuilder reset() {
        currentComponent = TextComponent.EMPTY;
        return this;
    }

    public Component[] build() {
        components.add(currentComponent);
        return components.toArray(new Component[components.size()]);
    }

    public static ComponentBuilder start(String first) {
        return new ComponentBuilder(Preconditions.checkNotNull(first));
    }
}
