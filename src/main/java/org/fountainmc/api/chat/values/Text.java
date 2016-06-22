package org.fountainmc.api.chat.values;

import com.google.common.base.Preconditions;

/**
 * {@link Text} represents a plain text component value.
 */
public final class Text implements ComponentValue {

    private final String text;

    private Text(String text) {
        this.text = text;
    }

    /**
     * Creates a new {@link Text} instance.
     *
     * @param text the text to use
     * @return the value
     */
    public static Text of(String text) {
        return new Text(Preconditions.checkNotNull(text, "text"));
    }

    /**
     * Returns the component's text.
     *
     * @return the text in this value
     */
    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Text text1 = (Text) o;

        return text.equals(text1.text);
    }

    @Override
    public int hashCode() {
        return text.hashCode();
    }

    @Override
    public String toString() {
        return "Text{"
                + "text='" + text + '\''
                + '}';
    }

}
