package org.fountainmc.api.chat;

import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.fountainmc.api.chat.events.ClickEvent;
import org.fountainmc.api.chat.events.ClickEventSerializer;
import org.fountainmc.api.chat.events.HoverEvent;
import org.fountainmc.api.chat.events.HoverEventSerializer;
import org.fountainmc.api.chat.values.Text;

import java.util.Collections;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class Components {
    public static final Component<Text> EMPTY_TEXT = new Component<>(null, null, null, null, null, null, null, null, null, null, Text.of(""),
            Collections.emptyList());
    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(Component.class, new ComponentSerializer())
            .registerTypeAdapter(HoverEvent.class, new HoverEventSerializer())
            .registerTypeAdapter(ClickEvent.class, new ClickEventSerializer())
            .create();

    public static Component<Text> empty() {
        return EMPTY_TEXT;
    }

    public static Component<Text> forPlainText(String text) {
        return EMPTY_TEXT.withValue(Text.of(checkNotNull(text, "text")));
    }

    public static List<Component<Text>> forLegacyText(String text) {
        LegacyChatConverter converter = new LegacyChatConverter(checkNotNull(text, "text"));
        converter.parse();
        return converter.toComponents();
    }

    public static String toJson(List<Component<?>> component) {
        return GSON.toJson(component);
    }

    public static List<Component<?>> fromJson(String json) {
        if (!json.startsWith("[")) {
            return ImmutableList.of(GSON.fromJson(json, new TypeToken<Component>() {}.getType()));
        }
        return GSON.fromJson(json, new TypeToken<List<Component<?>>>() {}.getType());
    }
}
