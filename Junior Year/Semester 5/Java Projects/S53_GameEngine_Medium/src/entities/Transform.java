// add to package
package entities;

/**
 * Transform statistics of Entity
 */
public class Transform {
    /**
     * x position of entity
     */
    private int xPos;

    /**
     * y position of entity
     */
    private int yPos;

    /**
     * rotation of entity
     */
    private float rotation;

    /**
     * speed of entity
     */
    private int speed;

    /**
     * Transform (non-argument constructor)
     * calls tetradic constructor with defaults: xPos = 0, yPos = 0 , rotation = 0, speed = 0
     */
    public Transform() {
        this(0, 0, 0, 0);
    }

    /**
     * Transform (tetradic constructor)
     * initializes Transform attributes to user inputs
     *
     * @param xPos
     * @param yPos
     * @param rotation
     * @param speed
     */
    public Transform(int xPos, int yPos, float rotation, int speed) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.rotation = rotation;
        this.speed = speed;
    }

    /**
     * @param xPos
     */
    public void setXPos(int xPos) {
        this.xPos = xPos;
    }

    /**
     * @param yPos
     */
    public void setYPos(int yPos) {
        this.yPos = yPos;
    }

    /**
     * @param rotation
     */
    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    /**
     * @param speed
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * @return x position of entity
     */
    public int getXPos() {
        return xPos;
    }

    /**
     * @return y position of entity
     */
    public int getYPos() {
        return yPos;
    }

    /**
     * @return rotation of entity
     */
    public float getRotation() {
        return rotation;
    }

    /**
     * @return speed of entity
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * OVERRIDE: base Object .toString
     * @return string representation of the Entity's Transform
     */
    @Override
    public String toString() {
        int xPos = getXPos();
        int yPos= getYPos();
        float rotation = getRotation();
        int speed = getSpeed();
        String format = "Transform: xPos: %d, yPos: %d, rotation: %f, speed: %d";
        String transformString = String.format(format, xPos, yPos, rotation, speed);
        return transformString;
    }
}
