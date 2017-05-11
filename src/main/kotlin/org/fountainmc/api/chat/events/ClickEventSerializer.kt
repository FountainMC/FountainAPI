package org.fountainmc.api.chat.events

import java.lang.reflect.Type
import java.util.Locale

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParseException
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer

class ClickEventSerializer : JsonSerializer<ClickEvent>, JsonDeserializer<ClickEvent> {

    @Throws(JsonParseException::class)
    override fun deserialize(element: JsonElement, type: Type, context: JsonDeserializationContext): ClickEvent {
        val `object` = element.asJsonObject
        val action = ClickEvent.Action.valueOf(`object`.get("action").asString)
        return ClickEvent(action, `object`.get("value").asString)
    }

    override fun serialize(event: ClickEvent, type: Type, context: JsonSerializationContext): JsonElement {
        val `object` = JsonObject()
        `object`.addProperty("action", event.action.name.toLowerCase(Locale.US))
        `object`.addProperty("value", event.value)
        return `object`
    }

}
