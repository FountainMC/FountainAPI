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
import com.google.gson.reflect.TypeToken

import org.fountainmc.api.chat.Component
import org.fountainmc.api.chat.values.Text

class HoverEventSerializer : JsonDeserializer<HoverEvent<*>>, JsonSerializer<HoverEvent<*>> {

    @Throws(JsonParseException::class)
    override fun deserialize(element: JsonElement, type: Type, context: JsonDeserializationContext): HoverEvent<*> {
        val `object` = element.asJsonObject
        val action = HoverEvent.Action.valueOf(`object`.get("action").asString)
        when (action) {
            HoverEvent.Action.SHOW_TEXT -> return HoverEvent.showText(context.deserialize<Component<*>>(`object`.get("value"), object : TypeToken<Component<Text>>() {

            }.type))
            HoverEvent.Action.SHOW_ACHIEVEMENT -> return HoverEvent.showAchievement(`object`.get("value").asString)
            HoverEvent.Action.SHOW_ITEM -> return HoverEvent.showItem(`object`.get("value").asString)
            else -> throw JsonParseException("invalid action?")
        }
    }

    override fun serialize(event: HoverEvent<*>, type: Type, context: JsonSerializationContext): JsonElement {
        val `object` = JsonObject()
        `object`.addProperty("action", event.action.name.toLowerCase(Locale.US))
        `object`.add("value", context.serialize(event.value))
        return `object`
    }

}
