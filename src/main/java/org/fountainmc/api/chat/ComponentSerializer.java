package org.fountainmc.api.chat;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import org.fountainmc.api.chat.events.ClickEvent;
import org.fountainmc.api.chat.events.HoverEvent;
import org.fountainmc.api.chat.values.ComponentValue;

public class ComponentSerializer implements JsonSerializer<Component<?>>, JsonDeserializer<Component<?>> {

    private static final ThreadLocal<Set<Component<?>>> DESERIALIZED_THIS_INVOCATION = new ThreadLocal<>();

    @Override
    public Component<?> deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
        if (element.isJsonPrimitive()) {
            // It's probably a regular old component.
            return Components.forPlainText(element.getAsString());
        }
        JsonObject object = element.getAsJsonObject();

        // Deserialize formatting.
        ChatColor color = null;
        Boolean bold = null;
        Boolean italic = null;
        Boolean underlined = null;
        Boolean strikethrough = null;
        Boolean obfuscated = null;
        String insertion = null;
        ClickEvent clickEvent = null;
        HoverEvent<?> hoverEvent = null;
        List<Component<?>> extra = null;

        if (object.has("color")) {
            color = context.deserialize(object.get("color"), ChatColor.class);
        }
        if (object.has("bold")) {
            bold = object.get("bold").getAsBoolean();
        }
        if (object.has("italic")) {
            italic = object.get("italic").getAsBoolean();
        }
        if (object.has("underlined")) {
            underlined = object.get("underlined").getAsBoolean();
        }
        if (object.has("strikethrough")) {
            strikethrough = object.get("strikethrough").getAsBoolean();
        }
        if (object.has("obfuscated")) {
            obfuscated = object.get("obfuscated").getAsBoolean();
        }
        if (object.has("insertion")) {
            insertion = object.get("insertion").getAsString();
        }
        if (object.has("clickEvent")) {
            clickEvent = context.deserialize(object.get("clickEvent"), ClickEvent.class);
        }
        if (object.has("hoverEvent")) {
            clickEvent = context.deserialize(object.get("hoverEvent"), HoverEvent.class);
        }
        if (object.has("extra")) {
            extra = new ArrayList<>();
            JsonArray extraArray = object.getAsJsonArray("extra");
            for (JsonElement extraElement : extraArray) {
                extra.add(context.deserialize(extraElement, Component.class));
            }
        }

        // Now, figure out the component value.
        ComponentValue value = context.deserialize(object, ComponentValue.class);

        return new Component<>(null, bold, italic, underlined, strikethrough, obfuscated, color, hoverEvent, clickEvent,
                insertion, value, extra);
    }

    @Override
    public JsonElement serialize(Component<?> component, Type type, JsonSerializationContext context) {
        boolean first = false;
        if (DESERIALIZED_THIS_INVOCATION.get() == null) {
            DESERIALIZED_THIS_INVOCATION.set(new HashSet<>());
            first = true;
        }

        try {
            if (!DESERIALIZED_THIS_INVOCATION.get().add(component)) {
                throw new RuntimeException("Component loop detected");
            }

            JsonObject object = new JsonObject();
            if (component.getColorRaw() != null) {
                object.addProperty("color", component.getColorRaw().toString().toLowerCase(Locale.US));
            }
            if (component.isBoldRaw() != null) {
                object.addProperty("bold", component.isBoldRaw());
            }
            if (component.isItalicRaw() != null) {
                object.addProperty("italic", component.isItalicRaw());
            }
            if (component.isUnderlinedRaw() != null) {
                object.addProperty("underlined", component.isUnderlinedRaw());
            }
            if (component.isStrikethroughRaw() != null) {
                object.addProperty("strikethrough", component.isStrikethroughRaw());
            }
            if (component.isObfuscatedRaw() != null) {
                object.addProperty("obfuscated", component.isObfuscatedRaw());
            }
            if (component.getInsertionRaw() != null) {
                object.addProperty("insertion", component.getInsertionRaw());
            }
            if (component.getHoverEventRaw() != null) {
                object.add("hoverEvent", context.serialize(component.getHoverEventRaw()));
            }
            if (component.getClickEventRaw() != null) {
                object.add("hoverEvent", context.serialize(component.getClickEventRaw()));
            }

            // Serialize values.
            JsonObject value = context.serialize(component.getValue()).getAsJsonObject();
            for (Map.Entry<String, JsonElement> entry : value.entrySet()) {
                object.add(entry.getKey(), entry.getValue());
            }
            return object;
        } finally {
            DESERIALIZED_THIS_INVOCATION.get().remove(component);
            if (first) {
                DESERIALIZED_THIS_INVOCATION.set(null);
            }
        }
    }

}
