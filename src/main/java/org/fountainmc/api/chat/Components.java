package org.fountainmc.api.chat;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.fountainmc.api.chat.events.ClickEvent;
import org.fountainmc.api.chat.events.ClickEventSerializer;
import org.fountainmc.api.chat.events.HoverEvent;
import org.fountainmc.api.chat.events.HoverEventSerializer;
import org.fountainmc.api.chat.values.Text;

import static com.google.common.base.Preconditions.checkNotNull;

public class Components {

    /**
     * An empty component with no text.
     */
    public static final Component<Text> EMPTY_TEXT = new Component<>(null, null, null, null, null, null, null, null, null, null, Text.of(""),
            Collections.emptyList());
    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(Component.class, new ComponentSerializer())
            .registerTypeAdapter(HoverEvent.class, new HoverEventSerializer())
            .registerTypeAdapter(ClickEvent.class, new ClickEventSerializer())
            .create();

    /**
     * Returns the empty component ({@link #EMPTY_TEXT}).
     *
     * @return the empty component
     */
    public static Component<Text> empty() {
        return EMPTY_TEXT;
    }

    /**
     * Creates a component for some plain text.
     *
     * @param text the plain text to use
     * @return the component created
     */
    public static Component<Text> forPlainText(String text) {
        return EMPTY_TEXT.withValue(Text.of(checkNotNull(text, "text")));
    }

    /**
     * Creates components from legacy text with color codes.
     *
     * @param text the text to convert
     * @return the components created
     */
    public static List<Component<Text>> forLegacyText(String text) {
        LegacyChatConverter converter = new LegacyChatConverter(checkNotNull(text, "text"));
        converter.parse();
        return converter.toComponents();
    }

    /**
     * Converts components into Minecraft JSON representation.
     *
     * @param component the components to convert
     * @return JSON representation of components
     */
    public static String toJson(List<Component<?>> component) {
        return GSON.toJson(component);
    }

    /**
     * Deserializes Minecraft chat component JSON into a component.
     *
     * @param json the JSON to deserialize
     * @return the deserialized components
     */
    public static List<Component<?>> fromJson(String json) {
        if (!json.startsWith("[")) {
            return ImmutableList.of(GSON.fromJson(json, new TypeToken<Component<?>>() {
            }.getType()));
        }
        return GSON.fromJson(json, new TypeToken<List<Component<?>>>() {
        }.getType());
    }

}
