package org.fountainmc.api.chat;

import org.fountainmc.api.chat.values.Text;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class JsonComponentTest {
    @Test
    public void simpleJsonDeserialize() {
        List<Component<?>> deserialized = Components.fromJson("[{\"text\":\"Hello, world!\"}]");
        assertThat(deserialized, hasSize(1));
        assertThat(deserialized.get(0).getValue(), instanceOf(Text.class));
        assertThat(((Text) deserialized.get(0).getValue()).getText(), equalTo("Hello, world!"));
    }

    @Test
    public void simpleJsonDeserializeWithColor() {
        List<Component<?>> deserialized = Components.fromJson("[{\"text\":\"Hello, world!\",\"color\":\"gray\"}]");
        assertThat(deserialized, hasSize(1));
        assertThat(deserialized.get(0).getValue(), instanceOf(Text.class));
        assertThat(deserialized.get(0).getColor(), is(ChatColor.GREY));
        assertThat(((Text) deserialized.get(0).getValue()).getText(), equalTo("Hello, world!"));
    }

    @Test
    public void arrayComponentDeserialize() {
        List<Component<?>> deserialized = Components.fromJson("[\"\",{\"text\":\"Hello, world!\",\"bold\":true}]");
        assertThat(deserialized, hasSize(2)); // there's an empty component!
        assertThat(deserialized.get(0).getValue(), instanceOf(Text.class));
        assertThat(((Text) deserialized.get(0).getValue()).getText(), equalTo(""));

        assertThat(deserialized.get(1).getValue(), instanceOf(Text.class));
        assertThat(deserialized.get(1).isBold(), is(true));
        assertThat(((Text) deserialized.get(1).getValue()).getText(), equalTo("Hello, world!"));
    }

    @Test
    public void jsonDeserializeWithExtra() {
        List<Component<?>> deserialized = Components.fromJson("[{\"text\":\"\",\"extra\":[{\"text\":\"Hello, world!\"}]}]");
        assertThat(deserialized, hasSize(1));
        assertThat(deserialized.get(0).getValue(), instanceOf(Text.class));
        assertThat(deserialized.get(0).getExtra(), hasSize(1));
        Component<?> extra = deserialized.get(0).getExtra().get(0);
        assertThat(extra.getValue(), instanceOf(Text.class));
        assertThat(((Text) extra.getValue()).getText(), equalTo("Hello, world!"));
    }
}
