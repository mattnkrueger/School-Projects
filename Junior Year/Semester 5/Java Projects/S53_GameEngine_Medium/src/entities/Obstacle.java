// add to package
package entities;

/**
 * Obstacle entity in game
 * Extends Entity
 */
public class Obstacle extends Entity {
    /**
     * size of the object
     */
    private int size;

    /**
     * Obstacle (non-argument constructor)
     * calls tetradic constructor with defaults: name = 'Obstacle', transform = 'new Transform()', emoji = '
     */
    public Obstacle() {
        this("Obstacle", new Transform(), "default-red", 50);
    }

    /**
     * Obstacle (tetradic constructor)
     * calls super (Entity) and initializes additional Obstacle attributes
     *
     * @param name
     * @param transform
     * @param emoji
     * @param size
     */
    public Obstacle(String name, Transform transform, String emoji, int size) {
        super(name, transform, emoji);
        this.size = size;
    }

    /**
     * @param size
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * @return size of the object
     */
    public int getSize() {
        return size;
    }

    /**
     * OVERRIDE: Entity .toString()
     * @return string representation of Object
     */
    @Override
    public String toString() {
        String obstacleName = getName();
        int size = getSize();
        String emoji = getEmojiUnicode();
        String transformString= getTransformString();
        String obstacleFormat = "Obstacle: %s %s\n";
        String sizeFormat = "Size: %d";
        String obstacleString = String.format(obstacleFormat, obstacleName, emoji);
        String sizeString = String.format(sizeFormat, size);
        String obstacleAndTransformString = obstacleString + "|_ " + transformString + "\n|_ " + sizeString + "\n";
        return obstacleAndTransformString;
    }
}
