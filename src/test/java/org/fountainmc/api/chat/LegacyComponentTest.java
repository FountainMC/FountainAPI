package org.fountainmc.api.chat;

import org.fountainmc.api.chat.events.ClickEvent;
import org.fountainmc.api.chat.values.Text;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class LegacyComponentTest {
    @Test
    public void simpleLegacyText() {
        List<Component<Text>> converted = Components.forLegacyText("Hello, world!");
        assertThat(converted, hasSize(1));
        assertThat(converted.get(0).getValue().getText(), equalTo("Hello, world!"));
    }

    @Test
    public void simpleLegacyTextWithColor() {
        List<Component<Text>> converted = Components.forLegacyText("\u00a7cHello, world!");
        assertThat(converted, hasSize(1));
        assertThat(converted.get(0).getColor(), equalTo(ChatColor.RED));
        assertThat(converted.get(0).getValue().getText(), equalTo("Hello, world!"));
    }

    @Test
    public void simpleLegacyTextTwoColors() {
        List<Component<Text>> converted = Components.forLegacyText("\u00a7cHello, \u00a7eworld!");
        assertThat(converted, hasSize(2));
        assertThat(converted.get(0).getColor(), equalTo(ChatColor.RED));
        assertThat(converted.get(0).getValue().getText(), equalTo("Hello, "));
        assertThat(converted.get(1).getColor(), equalTo(ChatColor.YELLOW));
        assertThat(converted.get(1).getValue().getText(), equalTo("world!"));
    }

    @Test
    public void simpleLegacyTextFormattingAndColor() {
        List<Component<Text>> converted = Components.forLegacyText("\u00a7c\u00a7lHello, world!");
        assertThat(converted, hasSize(1));
        assertThat(converted.get(0).getColor(), equalTo(ChatColor.RED));
        assertThat(converted.get(0).isBold(), is(true));
        assertThat(converted.get(0).getValue().getText(), equalTo("Hello, world!"));
    }

    @Test
    public void simpleLegacyTextWithOverlappingColors() {
        // The last color (&e) should be used.
        List<Component<Text>> converted = Components.forLegacyText("\u00a7c\u00a7eHello, world!");
        assertThat(converted, hasSize(1));
        assertThat(converted.get(0).getColor(), equalTo(ChatColor.YELLOW));
        assertThat(converted.get(0).getValue().getText(), equalTo("Hello, world!"));
    }

    @Test
    public void simpleLegacyTextWithManyFormattingCodes() {
        // The last color (&e) should be used.
        List<Component<Text>> converted = Components.forLegacyText("\u00a7c\u00a7l\u00a7nHello, world!");
        assertThat(converted, hasSize(1));
        assertThat(converted.get(0).getColor(), equalTo(ChatColor.RED));
        assertThat(converted.get(0).isBold(), is(true));
        assertThat(converted.get(0).isUnderlined(), is(true));
        assertThat(converted.get(0).getValue().getText(), equalTo("Hello, world!"));
    }

    @Test
    public void simpleLegacyTextWithColorTextThenFormat() {
        List<Component<Text>> converted = Components.forLegacyText("\u00a7cHello, \u00a7lworld!");
        assertThat(converted, hasSize(2));
        assertThat(converted.get(0).getColor(), equalTo(ChatColor.RED));
        assertThat(converted.get(0).isBold(), is(false));
        assertThat(converted.get(0).getValue().getText(), equalTo("Hello, "));

        assertThat(converted.get(1).getColor(), equalTo(ChatColor.RED));
        assertThat(converted.get(1).isBold(), is(true));
        assertThat(converted.get(1).getValue().getText(), equalTo("world!"));
    }

    @Test
    public void simpleLegacyTextReset() {
        List<Component<Text>> converted = Components.forLegacyText("\u00a7c\u00a7lHello, \u00a7rworld!");
        assertThat(converted, hasSize(2));
        assertThat(converted.get(0).getColor(), equalTo(ChatColor.RED));
        assertThat(converted.get(0).isBold(), is(true));
        assertThat(converted.get(0).getValue().getText(), equalTo("Hello, "));

        assertThat(converted.get(1).getColor(), equalTo(ChatColor.WHITE));
        assertThat(converted.get(1).isBold(), is(false));
        assertThat(converted.get(1).getValue().getText(), equalTo("world!"));
    }

    @Test
    public void simpleLegacyTextUrl() {
        List<Component<Text>> converted = Components.forLegacyText("http://fountainmc.org");
        assertThat(converted, hasSize(1));
        ClickEvent event = converted.get(0).getClickEvent();
        assertThat(event != null, is(true));
        assertThat(event.getAction(), equalTo(ClickEvent.Action.OPEN_URL));
        assertThat(event.getValue(), equalTo("http://fountainmc.org"));
        assertThat(converted.get(0).getValue().getText(), equalTo("http://fountainmc.org"));
    }

    @Test
    public void simpleLegacyTextUrlBetweenText() {
        List<Component<Text>> converted = Components.forLegacyText("abc mumble http://fountainmc.org grumble xyz");
        assertThat(converted, hasSize(3));

        assertThat(converted.get(0).getValue().getText(), equalTo("abc mumble "));

        ClickEvent event = converted.get(1).getClickEvent();
        assertThat(event != null, is(true));
        assertThat(event.getAction(), equalTo(ClickEvent.Action.OPEN_URL));
        assertThat(event.getValue(), equalTo("http://fountainmc.org"));
        assertThat(converted.get(1).getValue().getText(), equalTo("http://fountainmc.org"));

        assertThat(converted.get(2).getValue().getText(), equalTo(" grumble xyz"));
    }

    @Test
    public void simpleLegacyTextUrlPreservesFormatting() {
        List<Component<Text>> converted = Components.forLegacyText("\u00a7ctest http://fountainmc.org tset");
        assertThat(converted, hasSize(3));

        assertThat(converted.get(0).getValue().getText(), equalTo("test "));
        assertThat(converted.get(0).getColor(), equalTo(ChatColor.RED));

        ClickEvent event = converted.get(1).getClickEvent();
        assertThat(event != null, is(true));
        assertThat(event.getAction(), equalTo(ClickEvent.Action.OPEN_URL));
        assertThat(event.getValue(), equalTo("http://fountainmc.org"));
        assertThat(converted.get(1).getValue().getText(), equalTo("http://fountainmc.org"));
        assertThat(converted.get(1).getColor(), equalTo(ChatColor.RED));

        assertThat(converted.get(2).getValue().getText(), equalTo(" tset"));
        assertThat(converted.get(2).getColor(), equalTo(ChatColor.RED));
    }

    @Test
    public void simpleLegacyTextUrlSeparateFormatting() {
        List<Component<Text>> converted = Components.forLegacyText("\u00a7ctest \u00a7rhttp://fountainmc.org tset");
        assertThat(converted, hasSize(3));

        assertThat(converted.get(0).getValue().getText(), equalTo("test "));
        assertThat(converted.get(0).getColor(), equalTo(ChatColor.RED));

        ClickEvent event = converted.get(1).getClickEvent();
        assertThat(event != null, is(true));
        assertThat(event.getAction(), equalTo(ClickEvent.Action.OPEN_URL));
        assertThat(event.getValue(), equalTo("http://fountainmc.org"));
        assertThat(converted.get(1).getValue().getText(), equalTo("http://fountainmc.org"));
        assertThat(converted.get(1).getColor(), equalTo(ChatColor.WHITE));
    }
}
