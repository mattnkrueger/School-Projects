// add to package
package entities;

// import package dependencies
import styling.*;

/**
 * Superclass for all entities in game
 */
public class Entity {
    /**
     * name of entity
     */
    private String name;

    /**
     * transform of entity
     * see Transform.java
     */
    private Transform transform;

    /**
     * unicode emoji representation of the entity
     * see UnicodeEmoji.java
     */
    private String emojiUnicode;

    /**
     * Entity (non-argument constructor)
     * calls triadic constructor with defaults: name = 'Entity', transform = 'new Transform()', emojiUnicode = 'default-red'
     */
    public Entity() {
        this("Entity", new Transform(), "default-red");
    }

    /**
     * Entity (triadic constructor)
     * initializes entity attributes to user input
     *
     * @param name
     * @param transform
     * @param emojiUnicode
     */
    public Entity(String name, Transform transform, String emojiUnicode) {
        UnicodeEmoji unicodeEmojiDict = new UnicodeEmoji();
        this.name = name;
        this.transform = transform;
        this.emojiUnicode = unicodeEmojiDict.getEmoji(emojiUnicode);
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param transform
     */
    public void setTransform(Transform transform) {
        this.transform = transform;
    }

    /**
     * @param emojiUnicode
     */
    public void setEmojiUnicode(String emojiUnicode) {
        UnicodeEmoji unicodeEmojiDict = new UnicodeEmoji();
        this.emojiUnicode = unicodeEmojiDict.getEmoji(emojiUnicode);
    }

    /**
     * @return name of entity
     */
    public String getName() {
        return name;
    }

    /**
     * @return transform object of entity
     */
    public Transform getTransform() {
        return transform;
    }

    /**
     * @return emoji of entity
     */
    public String getEmojiUnicode() {
        return emojiUnicode;
    }

    /**
     * @return transform representation of entity
     */
    public String getTransformString() {
        String transformString = transform.toString();
        return transformString;
    }

    /**
     * OVERRIDE: base Object toString
     * @return string representation of entity
     */
    @Override
    public String toString() {
        String entityName = getName();
        String emoji = getEmojiUnicode();
        String transformString = getTransformString();
        String format = "Entity: %s %s\n";
        String entityString = String.format(format, entityName, emoji);
        String entityAndTransformString = entityString + transformString + "\n";
        return entityAndTransformString;
    }
}