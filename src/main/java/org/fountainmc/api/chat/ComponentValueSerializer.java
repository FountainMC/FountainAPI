package org.fountainmc.api.chat;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import org.fountainmc.api.chat.values.ComponentValue;
import org.fountainmc.api.chat.values.Text;
import org.fountainmc.api.chat.values.Translatable;

public class ComponentValueSerializer implements JsonSerializer<ComponentValue>, JsonDeserializer<ComponentValue> {

    @Override
    public ComponentValue deserialize(JsonElement element, Type type, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject object = element.getAsJsonObject();
        ComponentValue value = null;
        if (object.has("text")) {
            value = Text.of(object.get("text").getAsString());
        } else if (object.has("translate")) {
            Translatable translatable = Translatable.withId(object.get("translate").getAsString());
            if (object.has("with")) {
                List<String> with = new ArrayList<>();
                for (JsonElement a : object.getAsJsonArray("with")) {
                    with.add(a.getAsString());
                }
                translatable = translatable.withSubstitutions(with);
            }
            value = translatable;
        }

        if (value == null) {
            throw new JsonParseException("Invalid value");
        }

        return value;
    }

    @Override
    public JsonElement serialize(ComponentValue value, Type type, JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        if (value instanceof Text) {
            object.addProperty("text", ((Text) value).getText());
        } else if (value instanceof Translatable) {
            object.addProperty("translate", ((Translatable) value).getMessage());
            List<String> with = ((Translatable) value).getSubstitutions();
            if (!with.isEmpty()) {
                JsonArray array = new JsonArray();
                with.forEach(array::add);
                object.add("with", array);
            }
        } else {
            throw new RuntimeException("Unable to serialize component value");
        }
        return object;
    }

}
