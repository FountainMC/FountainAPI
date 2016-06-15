package org.fountainmc.api;

/**
 * Get the material <p>Plugins should keep in mind that forge mods may add new
 * materials.</p>
 */
public interface Material {

    public int getId();

    public String getName();

    public boolean isBlock();

    public boolean isEdible();

    /**
     * Return if the material is present in unmodified vanilla minecraft
     *
     * @return if in vanilla minecraft
     */
    public default boolean isVanilla() {
        return getName().startsWith("minecraft:");
    }

}
