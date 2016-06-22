package org.fountainmc.api;

/**
 * Get the material <p>Plugins should keep in mind that forge mods may add new
 * materials.</p>
 */
public interface Material {

    int getId();

    String getName();

    boolean isBlock();

    boolean isEdible();

    /**
     * Return if the material is present in unmodified vanilla minecraft
     *
     * @return if in vanilla minecraft
     */
    default boolean isVanilla() {
        return getName().startsWith("minecraft:");
    }

}
