// add to package
package entities;

// import junit dependencies
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// import src dependencies
import entities.Transform;
import entities.Entity;

/**
 * Test Entity class
 */
public class EntityJUnitTest {

    /**
     * Test to ensure the Entity object initializes correctly
     */
    @Test
    public void testEntityInitialization() {
        Entity entity = new Entity();
        assertNotNull(entity);
    }

    /**
     * Test toString() override
     */
    @Test
    public void testToString() {
        Entity entity = new Entity();
        assertEquals("Entity: Entity 🟥\nTransform: xPos: 0, yPos: 0, rotation: 0.000000, speed: 0\n", entity.toString());
    }

    /**
     * Test getName()
     */
    @Test
    public void testGetName() {
        Entity entity = new Entity("Name", new Transform(), "default-red");
        assertEquals("Name", entity.getName());
    }

    /**
     * Test getTransform()
     */
    @Test
    public void testGetTransform() {
        Entity entity = new Entity("Name", new Transform(1,2,3,4), "default-red");
        assertEquals("Transform: xPos: 1, yPos: 2, rotation: 3.000000, speed: 4", entity.getTransform().toString());
    }

    /**
     * Test getEmojiUnicode()
     */
    @Test
    public void getEmojiUnicode() {
        Entity entity = new Entity("Name", new Transform(), "default-red");
        assertEquals("🟥", entity.getEmojiUnicode());
    }

    /**
     * Run all EntityJUnitTest tests from an outside source.
     */
    @Test
    public void runAllTests() {
        // EntityJUnitTest tests
        testEntityInitialization();
        testToString();
        testGetName();
        testGetTransform();
        getEmojiUnicode();
    }

}
