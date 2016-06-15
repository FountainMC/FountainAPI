package org.fountainmc.api.chat;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import org.fountainmc.api.chat.events.ClickEvent;
import org.fountainmc.api.chat.events.HoverEvent;
import org.fountainmc.api.chat.values.Text;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.*;

public class ComponentBuilder {
    private Component<Text> currentComponent = Components.EMPTY_TEXT;
    private final List<Component<Text>> components = new ArrayList<>();

    private ComponentBuilder(String first) {
        currentComponent = currentComponent.withValue(Text.of(checkNotNull(first, "first")));
    }

    public ComponentBuilder then(String text) {
        currentComponent = currentComponent.withValue(Text.of(checkNotNull(text, "text")));
        components.add(currentComponent);
        currentComponent = currentComponent.withParent(currentComponent);
        return this;
    }

    public ComponentBuilder color(ChatColor color) {
        checkNotNull(color, color);
        checkArgument(!color.isFormatting(), "color is a formatting code");
        currentComponent = currentComponent.withColor(color);
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

    public ComponentBuilder italic(boolean italic) {
        currentComponent = currentComponent.withItalic(italic);
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
        currentComponent = Components.EMPTY_TEXT;
        return this;
    }

    public List<Component<Text>> build() {
        components.add(currentComponent);
        return ImmutableList.copyOf(components);
    }

    public static ComponentBuilder start(String first) {
        return new ComponentBuilder(Preconditions.checkNotNull(first));
    }
}
