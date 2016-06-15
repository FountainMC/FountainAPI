package org.fountainmc.api.chat.values;

import com.google.common.base.Preconditions;

public class Text implements ComponentValue {
    private final String text;

    private Text(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public static Text of(String text) {
        return new Text(Preconditions.checkNotNull(text, "text"));
    }
}
