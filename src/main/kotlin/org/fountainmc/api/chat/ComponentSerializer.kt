package org.fountainmc.api.chat

import com.google.common.collect.ImmutableList
import java.lang.reflect.Type
import java.util.HashSet
import java.util.Locale

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParseException
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer

import org.fountainmc.api.chat.events.ClickEvent
import org.fountainmc.api.chat.events.HoverEvent
import org.fountainmc.api.chat.values.ComponentValue

class ComponentSerializer : JsonSerializer<Component<*>>, JsonDeserializer<Component<*>> {

    @Throws(JsonParseException::class)
    override fun deserialize(element: JsonElement, type: Type, context: JsonDeserializationContext): Component<*> {
        if (element.isJsonPrimitive) {
            // It's probably a regular old component.
            return Components.forPlainText(element.asString)
        }
        val obj = element.asJsonObject

        // Deserialize formatting.
        var color: ChatColor? = null
        var bold: Boolean? = null
        var italic: Boolean? = null
        var underlined: Boolean? = null
        var strikethrough: Boolean? = null
        var obfuscated: Boolean? = null
        var insertion: String? = null
        var clickEvent: ClickEvent? = null
        var hoverEvent: HoverEvent<*>? = null
        val extra = ImmutableList.builder<Component<*>>()

        if (obj.has("color")) {
            color = context.deserialize<ChatColor>(obj.get("color"), ChatColor::class.java)
        }
        if (obj.has("bold")) {
            bold = obj.get("bold").asBoolean
        }
        if (obj.has("italic")) {
            italic = obj.get("italic").asBoolean
        }
        if (obj.has("underlined")) {
            underlined = obj.get("underlined").asBoolean
        }
        if (obj.has("strikethrough")) {
            strikethrough = obj.get("strikethrough").asBoolean
        }
        if (obj.has("obfuscated")) {
            obfuscated = obj.get("obfuscated").asBoolean
        }
        if (obj.has("insertion")) {
            insertion = obj.get("insertion").asString
        }
        if (obj.has("clickEvent")) {
            clickEvent = context.deserialize<ClickEvent>(obj.get("clickEvent"), ClickEvent::class.java)
        }
        if (obj.has("hoverEvent")) {
            hoverEvent = context.deserialize<HoverEvent<*>>(obj.get("hoverEvent"), HoverEvent::class.java)
        }
        if (obj.has("extra")) {
            val extraArray = obj.getAsJsonArray("extra")
            for (extraElement in extraArray) {
                extra.add(context.deserialize<Component<*>>(extraElement, Component::class.java))
            }
        }

        // Now, figure out the component value.
        val value = context.deserialize<ComponentValue>(obj, ComponentValue::class.java)

        @Suppress("DEPRECATION") // We know what we're doing
        return Component<ComponentValue>(
                parent = null,
                isBoldRaw = bold,
                isItalicRaw = italic,
                isUnderlinedRaw = underlined,
                isStrikethroughRaw = strikethrough,
                isObfuscatedRaw = obfuscated,
                colorRaw = color,
                hoverEventRaw = hoverEvent,
                clickEventRaw = clickEvent,
                insertionRaw = insertion,
                value = value,
                extra = extra.build()
        )
    }

    override fun serialize(component: Component<*>, type: Type, context: JsonSerializationContext): JsonElement {
        val invocation = DESERIALIZED_THIS_INVOCATION.get()
        val first = invocation.isEmpty()

        try {
            check(invocation.add(component)) { "Component loop detected" }
            val `object` = JsonObject()
            if (component.colorRaw != null) {
                `object`.addProperty("color", component.colorRaw.toString().toLowerCase(Locale.US))
            }
            if (component.isBoldRaw != null) {
                `object`.addProperty("bold", component.isBoldRaw)
            }
            if (component.isItalicRaw != null) {
                `object`.addProperty("italic", component.isItalicRaw)
            }
            if (component.isUnderlinedRaw != null) {
                `object`.addProperty("underlined", component.isUnderlinedRaw)
            }
            if (component.isStrikethroughRaw != null) {
                `object`.addProperty("strikethrough", component.isStrikethroughRaw)
            }
            if (component.isObfuscatedRaw != null) {
                `object`.addProperty("obfuscated", component.isObfuscatedRaw)
            }
            if (component.insertionRaw != null) {
                `object`.addProperty("insertion", component.insertionRaw)
            }
            if (component.hoverEventRaw != null) {
                `object`.add("hoverEvent", context.serialize(component.hoverEventRaw))
            }
            if (component.clickEventRaw != null) {
                `object`.add("hoverEvent", context.serialize(component.clickEventRaw))
            }

            // Serialize values.
            val value = context.serialize(component.value).asJsonObject
            for ((key, value1) in value.entrySet()) {
                `object`.add(key, value1)
            }
            return `object`
        } finally {
            invocation.remove(component)
            if (first) {
                invocation.clear()
            }
        }
    }

    companion object {
        private val DESERIALIZED_THIS_INVOCATION = ThreadLocal.withInitial { HashSet<Component<*>>() }
    }
}
