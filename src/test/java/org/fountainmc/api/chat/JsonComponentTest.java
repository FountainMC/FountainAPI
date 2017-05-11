package org.fountainmc.api.chat;

import java.util.Collections;
import java.util.List;

import org.fountainmc.api.chat.values.Text;
import org.fountainmc.api.chat.values.Translatable;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class JsonComponentTest {

    @Test
    public void simpleJsonDeserialize() {
        List<Component<?>> deserialized = Components.INSTANCE.fromJson("[{\"text\":\"Hello, world!\"}]");
        assertThat(deserialized, hasSize(1));
        assertThat(deserialized.get(0).getValue(), instanceOf(Text.class));
        assertThat(((Text) deserialized.get(0).getValue()).getText(), equalTo("Hello, world!"));
    }

    @Test
    public void simpleJsonDeserializeWithColor() {
        List<Component<?>> deserialized = Components.INSTANCE.fromJson("[{\"text\":\"Hello, world!\",\"color\":\"gray\"}]");
        assertThat(deserialized, hasSize(1));
        assertThat(deserialized.get(0).getValue(), instanceOf(Text.class));
        assertThat(deserialized.get(0).getColor(), is(ChatColor.GREY));
        assertThat(((Text) deserialized.get(0).getValue()).getText(), equalTo("Hello, world!"));
    }

    @Test
    public void arrayComponentDeserialize() {
        List<Component<?>> deserialized = Components.INSTANCE.fromJson("[\"\",{\"text\":\"Hello, world!\",\"bold\":true}]");
        assertThat(deserialized, hasSize(2)); // there's an empty component!
        assertThat(deserialized.get(0).getValue(), instanceOf(Text.class));
        assertThat(((Text) deserialized.get(0).getValue()).getText(), equalTo(""));

        assertThat(deserialized.get(1).getValue(), instanceOf(Text.class));
        assertThat(deserialized.get(1).isBold(), is(true));
        assertThat(((Text) deserialized.get(1).getValue()).getText(), equalTo("Hello, world!"));
    }

    @Test
    public void jsonDeserializeWithExtra() {
        List<Component<?>> deserialized = Components.INSTANCE.fromJson("[{\"text\":\"\",\"extra\":[{\"text\":\"Hello, world!\"}]}]");
        assertThat(deserialized, hasSize(1));
        assertThat(deserialized.get(0).getValue(), instanceOf(Text.class));
        assertThat(deserialized.get(0).getExtra(), hasSize(1));
        Component<?> extra = deserialized.get(0).getExtra().get(0);
        assertThat(extra.getValue(), instanceOf(Text.class));
        assertThat(((Text) extra.getValue()).getText(), equalTo("Hello, world!"));
    }

    @Test
    public void translatableDeserialize() {
        List<Component<?>> deserialized = Components.INSTANCE.fromJson("[{\"translate\":\"demo.day.2\"}]");
        assertThat(deserialized, hasSize(1));
        assertThat(deserialized.get(0).getValue(), instanceOf(Translatable.class));
        assertThat(((Translatable) deserialized.get(0).getValue()).getMessage(), equalTo("demo.day.2"));
    }

    @Test
    public void translatableDeserializeWith() {
        List<Component<?>> deserialized = Components.INSTANCE.fromJson("[{\"translate\":\"multiplayer.player.joined\",\"with\":[\"Notch\"]}]");
        assertThat(deserialized, hasSize(1));
        assertThat(deserialized.get(0).getValue(), instanceOf(Translatable.class));
        assertThat(((Translatable) deserialized.get(0).getValue()).getMessage(), equalTo("multiplayer.player.joined"));
        assertThat(((Translatable) deserialized.get(0).getValue()).getSubstitutions(), equalTo(
                Collections.singletonList("Notch")));
    }

}
