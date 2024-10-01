// add to package
package entities;

/**
 * Represents an Actor entity in game
 *
 * extends Entity
 */
public class Actor extends Entity {
    /**
     * health of actor
     */
    private float health;

    /**
     * non-argument constructor: sets default health = 100
     */
    public Actor() {
        // implicitly calls super()
        this.health = 100;
    }

    /**
     * tetradic constructor: initialize entity (super) and actor attributes
     *
     * @param name
     * @param transform
     * @param emoji
     * @param health
     */
    public Actor(String name, Transform transform, String emoji, float health) {
        super(name, transform, emoji);
        this.health = health;
    }

    /**
     * @param health
     */
    public void setHealth(float health) {
        this.health = health;
    }

    /**
     * @return health of actor
     */
    public float getHealth() {
        return health;
    }

    /**
     * OVERRIDES: toString() of Entity
     * @return string representation of actor
     */
    @Override
    public String toString() {
        String actorName = getName();
        float health = getHealth();
        String emoji = getEmojiUnicode();
        String transformString = getTransformString();
        String actorFormat = "Actor: %s %s\n";
        String healthFormat = "Health: %f";
        String actorString = String.format(actorFormat, actorName, emoji);
        String healthString = String.format(healthFormat, health);
        String actorAndTransformString = actorString + "|_ " + transformString + "\n|_ " + healthString + "\n";
        return actorAndTransformString;
    }
}
