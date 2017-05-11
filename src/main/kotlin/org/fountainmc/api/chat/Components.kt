package org.fountainmc.api.chat

import com.google.common.collect.ImmutableList
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.fountainmc.api.chat.events.ClickEvent
import org.fountainmc.api.chat.events.ClickEventSerializer
import org.fountainmc.api.chat.events.HoverEvent
import org.fountainmc.api.chat.events.HoverEventSerializer
import org.fountainmc.api.chat.values.ComponentValue
import org.fountainmc.api.chat.values.Text

object Components {

    /**
     * An empty component with no text.
     */
    @Suppress("DEPRECATION")
    val EMPTY_TEXT = Component(value = Text.of(""))
    private val GSON = GsonBuilder()
            .registerTypeAdapter(Component::class.java, ComponentSerializer())
            .registerTypeAdapter(HoverEvent::class.java, HoverEventSerializer())
            .registerTypeAdapter(ClickEvent::class.java, ClickEventSerializer())
            .registerTypeAdapter(ComponentValue::class.java, ComponentValueSerializer())
            .create()

    /**
     * Returns the empty component ([.EMPTY_TEXT]).

     * @return the empty component
     */
    fun empty(): Component<Text> {
        return EMPTY_TEXT
    }

    /**
     * Creates a component for some plain text.

     * @param text the plain text to use
     * *
     * @return the component created
     */
    fun forPlainText(text: String): Component<Text> {
        return EMPTY_TEXT.withValue(Text.of(text))
    }

    /**
     * Creates components from legacy text with color codes.

     * @param text the text to convert
     * *
     * @return the components created
     */
    fun forLegacyText(text: String): List<Component<Text>> {
        val converter = LegacyChatConverter(text)
        converter.parse()
        return converter.toComponents()
    }

    /**
     * Converts components into Minecraft JSON representation.

     * @param component the components to convert
     * *
     * @return JSON representation of components
     */
    fun toJson(component: List<Component<*>>): String {
        return GSON.toJson(component)
    }

    /**
     * Deserializes Minecraft chat component JSON into a component.

     * @param json the JSON to deserialize
     * *
     * @return the deserialized components
     */
    fun fromJson(json: String): List<Component<*>> {
        if (!json.startsWith("[")) {
            return ImmutableList.of(GSON.fromJson<Component<*>>(json, object : TypeToken<Component<*>>() {

            }.type))
        }
        return GSON.fromJson<List<Component<*>>>(json, object : TypeToken<List<Component<*>>>() {

        }.type)
    }

}
