package org.fountainmc.api.chat.values;

import java.util.Collection;
import java.util.List;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

/**
 * Represents a translatable Minecraft message.
 */
public class Translatable implements ComponentValue {

    private final String message;
    private final List<String> substitutions;

    private Translatable(String message, List<String> substitutions) {
        this.message = message;
        this.substitutions = substitutions;
    }

    /**
     * Creates a translatable message with a specific message ID (i.e. demo.day.2).
     * @param message the message ID to use
     * @return a Translatable
     */
    public static Translatable withId(String message) {
        return new Translatable(Preconditions.checkNotNull(message, "message"), ImmutableList.of());
    }

    /**
     * Retrieves the message ID for this translatable.
     * @return message ID
     */
    public String getMessage() {
        return message;
    }

    /**
     * Retrieves an immutable list of substitutions used for the message.
     * @return the list of substitutions
     */
    public List<String> getSubstitutions() {
        return substitutions;
    }

    /**
     * Creates a new instance of {@code Translatable} with new substitutions.
     * @param substitutions substitutions to use
     * @return a new {@code Translatable} with new substitutions
     */
    public Translatable withSubstitutions(Collection<String> substitutions) {
        return new Translatable(message, ImmutableList.copyOf(substitutions));
    }

    /**
     * Creates a new instance of {@code Translatable} with new substitutions.
     * @param substitutions substitutions to use
     * @return a new {@code Translatable} with new substitutions
     */
    public Translatable withSubstitutions(String... substitutions) {
        return new Translatable(message, ImmutableList.copyOf(substitutions));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Translatable that = (Translatable) o;
        return Objects.equal(message, that.message) &&
                Objects.equal(substitutions, that.substitutions);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(message, substitutions);
    }

    @Override
    public String toString() {
        return "Translatable{" +
                "message='" + message + '\'' +
                ", substitutions=" + substitutions +
                '}';
    }

}
