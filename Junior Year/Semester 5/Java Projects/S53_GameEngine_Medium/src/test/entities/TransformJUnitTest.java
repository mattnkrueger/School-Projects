// add to package
package entities;

// import junit dependencies
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// import src dependencies
import entities.Transform;

/**
 * Test Transform class
 */
public class TransformJUnitTest {

    /**
     * Test to ensure the Transform object initializes correctly
     */
    @Test
    public void testTransformInitialization() {
        Transform transform = new Transform();
        assertNotNull(transform);
    }

    /**
     * Test toString() override
     */
    @Test
    public void testToString() {
        Transform transform = new Transform();
        assertEquals("Transform: xPos: 0, yPos: 0, rotation: 0.000000, speed: 0", transform.toString());
    }

    /**
     * Test all get methods for Transform attributes
     */
    @Test
    public void testGetMethods() {
        Transform transform = new Transform(10,20,30,40);
        assertEquals(10, transform.getXPos());
        assertEquals(20, transform.getYPos());
        assertEquals(30, transform.getRotation());
        assertEquals(40, transform.getSpeed());
    }

    /**
     * Run all TransformJUnitTest tests from an outside source.
     */
    @Test
    public void runAllTests() {
        // TransformJUnitTest tests
        testTransformInitialization();
        testToString();
        testGetMethods();
    }

}
