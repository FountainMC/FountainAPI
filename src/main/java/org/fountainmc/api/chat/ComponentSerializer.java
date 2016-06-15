package org.fountainmc.api.chat;

import com.google.gson.*;
import org.fountainmc.api.Color;
import org.fountainmc.api.chat.events.ClickEvent;
import org.fountainmc.api.chat.events.HoverEvent;
import org.fountainmc.api.chat.values.ComponentValue;
import org.fountainmc.api.chat.values.Text;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Locale;

public class ComponentSerializer implements JsonSerializer<Component>, JsonDeserializer<Component> {
    @Override
    public Component deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
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
            color = ChatColor.valueOf(object.get("color").getAsString().toUpperCase());
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

        // Now, figure out the component value.
        ComponentValue value = null;
        if (object.has("text")) {
            value = Text.of(object.get("string").getAsString());
        }

        if (value == null) {
            throw new JsonParseException("Invalid value");
        }

        return new Component(null, bold, italic, underlined, strikethrough, obfuscated, color, hoverEvent, clickEvent,
                insertion, value, extra);
    }

    @Override
    public JsonElement serialize(Component component, Type type, JsonSerializationContext context) {
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

        // Serialize values.
        if (component.getValue() instanceof Text) {
            object.addProperty("text", ((Text) component.getValue()).getText());
        }
        return object;
    }
}
