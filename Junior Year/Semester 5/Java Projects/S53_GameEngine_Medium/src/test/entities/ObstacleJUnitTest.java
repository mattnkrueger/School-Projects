// add to package
package entities;

// import junit dependencies
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// import src dependencies
import entities.Transform;
import entities.Entity;
import entities.Obstacle;

/**
 * Test Location class
 */
public class ObstacleJUnitTest {

    /**
     * Test to ensure the Obstacle object initializes correctly
     */
    @Test
    public void testObstacleInitialization() {
        Obstacle obstacle = new Obstacle();
        assertNotNull(obstacle);
    }

    /**
     * Test toString() override
     */
    @Test
    public void testToString() {
        Obstacle obstacle = new Obstacle();
        assertEquals("Obstacle: Obstacle 🟥\n|_ Transform: xPos: 0, yPos: 0, rotation: 0.000000, speed: 0\n|_ Size: 50\n", obstacle.toString());
    }

    /**
     * Test getSize()
     */
    @Test
    public void testGetObstacle() {
        Obstacle obstacle = new Obstacle("Name", new Transform(), "default-red", 400);
        assertEquals(400, obstacle.getSize());
    }

    /**
     * Run all ObstacleJUnitTest tests from an outside source.
     */
    @Test
    public void runAllTests() {
        // ObstacleJUnitTest tests
        testObstacleInitialization();
        testToString();
        testGetObstacle();
    }

}
