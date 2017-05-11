package org.fountainmc.api.chat

import java.lang.reflect.Type
import java.util.ArrayList

import com.google.gson.JsonArray
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParseException
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer

import org.fountainmc.api.chat.values.ComponentValue
import org.fountainmc.api.chat.values.Text
import org.fountainmc.api.chat.values.Translatable

class ComponentValueSerializer : JsonSerializer<ComponentValue>, JsonDeserializer<ComponentValue> {

    @Throws(JsonParseException::class)
    override fun deserialize(element: JsonElement, type: Type, context: JsonDeserializationContext): ComponentValue {
        val obj = element.asJsonObject
        var value: ComponentValue? = null
        if (obj.has("text")) {
            value = Text.of(obj.get("text").asString)
        } else if (obj.has("translate")) {
            var translatable = Translatable.withId(obj.get("translate").asString)
            if (obj.has("with")) {
                val with = ArrayList<String>()
                for (a in obj.getAsJsonArray("with")) {
                    with.add(a.asString)
                }
                translatable = translatable.withSubstitutions(with)
            }
            value = translatable
        }

        if (value == null) {
            throw JsonParseException("Invalid value")
        }

        return value
    }

    override fun serialize(value: ComponentValue, type: Type, context: JsonSerializationContext): JsonElement {
        val obj = JsonObject()
        if (value is Text) {
            obj.addProperty("text", value.text)
        } else if (value is Translatable) {
            obj.addProperty("translate", value.message)
            val with = value.substitutions
            if (!with.isEmpty()) {
                val array = JsonArray()
                with.forEach { array.add(it) }
                obj.add("with", array)
            }
        } else {
            throw RuntimeException("Unable to serialize component value")
        }
        return obj
    }

}
