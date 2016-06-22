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
import com.google.gson.reflect.TypeToken;

import org.fountainmc.api.chat.Component;
import org.fountainmc.api.chat.values.Text;

public class HoverEventSerializer implements JsonDeserializer<HoverEvent<?>>, JsonSerializer<HoverEvent<?>> {

    @Override
    public HoverEvent<?> deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = element.getAsJsonObject();
        HoverEvent.Action action = HoverEvent.Action.valueOf(object.get("action").getAsString());
        switch (action) {
            case SHOW_TEXT:
                return HoverEvent.showText(context.deserialize(object.get("value"), new TypeToken<Component<Text>>() {
                }.getType()));
            case SHOW_ACHIEVEMENT:
                return HoverEvent.showAchievement(object.get("value").getAsString());
            case SHOW_ITEM:
                return HoverEvent.showItem(object.get("value").getAsString());
            default:
                throw new JsonParseException("invalid action?");
        }
    }

    @Override
    public JsonElement serialize(HoverEvent<?> event, Type type, JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        object.addProperty("action", event.getAction().name().toLowerCase(Locale.US));
        object.add("value", context.serialize(event.getValue()));
        return object;
    }

}
