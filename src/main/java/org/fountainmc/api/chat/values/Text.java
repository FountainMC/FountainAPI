package org.fountainmc.api.chat.values;

import com.google.common.base.Preconditions;

/**
 * {@link Text} represents a plain text component value.
 */
public class Text implements ComponentValue {
    private final String text;

    private Text(String text) {
        this.text = text;
    }

    /**
     * Returns the component's text.
     * @return the text in this value
     */
    public String getText() {
        return text;
    }

    /**
     * Creates a new {@link Text} instance.
     * @param text the text to use
     * @return the value
     */
    public static Text of(String text) {
        return new Text(Preconditions.checkNotNull(text, "text"));
    }
}
