// add to package
package entities;

// import junit dependencies
import actions.Action;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// import src dependencies
import entities.Actor;

/**
 * Test Actor class
 */
public class ActorJUnitTest {

    /**
     * Test to ensure the Actor object initializes correctly
     */
    @Test
    public void testActorInitialization() {
        Actor actor = new Actor();
        assertNotNull(actor);
    }

    /**
     * Test toString() override
     */
    @Test
    public void testToString() {
        Actor actor = new Actor();
        assertEquals("Actor: Entity 🟥\n|_ Transform: xPos: 0, yPos: 0, rotation: 0.000000, speed: 0\n|_ Health: 100.000000\n", actor.toString());
    }

    /**
     * Test getHealth()
     */
    @Test
    public void testGetHealth() {
        Actor actor = new Actor("Actor", new Transform(), "default-red", 100);
        assertEquals(100, actor.getHealth());
    }

    /**
     * Run all ActorJUnitTest tests from an outside source.
     */
    @Test
    public void runAllTests() {
        // ActorJUnitTest tests
        testActorInitialization();
        testToString();
        testGetHealth();
    }

}
