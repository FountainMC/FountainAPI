package org.fountainmc.api.chat;

import org.fountainmc.api.chat.values.Text;

import java.util.Collections;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class Components {
    public static final Component<Text> EMPTY_TEXT = new Component<>(null, null, null, null, null, null, null, null, null, null, Text.of(""),
            Collections.emptyList());

    public static Component<Text> empty() {
        return EMPTY_TEXT;
    }

    public static Component<Text> forPlainText(String text) {
        return EMPTY_TEXT.withValue(Text.of(checkNotNull(text, "text")));
    }

    public static List<Component<Text>> forLegacyText(String text) {
        LegacyChatConverter converter = new LegacyChatConverter(checkNotNull(text, "text"));
        converter.parse();
        return converter.toComponents();
    }
}
