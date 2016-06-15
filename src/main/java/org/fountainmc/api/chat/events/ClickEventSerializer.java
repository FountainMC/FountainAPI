package org.fountainmc.api.chat.events;

import java.lang.reflect.Type;
import java.util.Locale;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class ClickEventSerializer implements JsonSerializer<ClickEvent>, JsonDeserializer<ClickEvent> {

    @Override
    public ClickEvent deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = element.getAsJsonObject();
        ClickEvent.Action action = ClickEvent.Action.valueOf(object.get("action").getAsString());
        return new ClickEvent(action, object.get("value").getAsString());
    }

    @Override
    public JsonElement serialize(ClickEvent event, Type type, JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        object.addProperty("action", event.getAction().name().toLowerCase(Locale.US));
        object.addProperty("value", event.getValue());
        return object;
    }

}
