// add to package
package actions;

// import junit dependencies
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// import src dependencies
import entities.Transform;
import entities.Actor;
import actions.Action;
import actions.ActionCalculation;

/**
 * Test Action and ActionCalculation (helper class)
 */
public class ActionJUnitTest {
    //////////////////////////////////////////////////////////////////////////////
    // Action (class) tests:
    /**
     * Test to ensure the Action object initializes correctly
     */
    @Test
    public void testActionInitialization() {
        Action action = new Action();
        assertNotNull(action);
    }

    /**
     * Test that getTarget().getName()
     */
    @Test
    public void testGetTarget() {
        // actors to pass into action
        Actor actor1 = new Actor("PLAYER", new Transform(), "default-red", 1000);
        Actor actor2 = new Actor("Target", new Transform(), "default-red", 1000);

        // action
        Action action = new Action(actor1, actor2, "EAT");
        assertEquals("Target", action.getTarget().getName());
    }

    /**
     * Test getPlayer().getName()
     */
    @Test
    public void testGetPlayer() {
        // actors to pass into action
        Actor actor1 = new Actor("PLAYER", new Transform(), "default-red", 1000);
        Actor actor2 = new Actor("Target", new Transform(), "default-red", 1000);

        // action
        Action action = new Action(actor1, actor2, "EAT");
        assertEquals("PLAYER", action.getPlayer().getName());
    }

    /**
     * Test createAndPrintAction()
     */
    @Test
    public void testCreateAndPrintAction() {
        // actors to pass into action
        Actor actor1 = new Actor();
        Actor actor2 = new Actor();

        // action
        Action action = new Action(actor1, actor2, "EAT");
        assertEquals("EAT", action.getActionType());
    }
    //////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////
    // ActionCalculation (class) tests:
    /**
     * Test to ensure the ActionCalculation object initializes correctly
     */
    @Test
    public void testActionCalculationInitialization() {
        Action action = new Action();
        ActionCalculation actionCalculation = new ActionCalculation();
        assertNotNull(actionCalculation);
    }

    /**
     * Test calculateEatStatsAndActionString()
     */
    @Test
    public void testCalculateEatStatsAndActionString() {
        // actors to pass into action
        ActionCalculation actionCalculation = new ActionCalculation();
        assertNotNull(actionCalculation.calculateEatStatsAndActionString());
    }

    /**
     * Test calculateMoveStatsAndActionString()
     */
    @Test
    public void testCalculateMoveStatsAndActionString() {
        // actors to pass into action
        ActionCalculation actionCalculation = new ActionCalculation();
        assertNotNull(actionCalculation.calculateMoveStatsAndActionString());
    }

    /**
     * Test calculateAttackStatsAndActionString()
     */
    @Test
    public void testCalculateAttackStatsAndActionString() {
        // actors to pass into action
        ActionCalculation actionCalculation = new ActionCalculation();
        assertNotNull(actionCalculation.calculateAttackStatsAndActionString());
    }

    /**
     * Test calculateSpeakActionString()
     */
    @Test
    public void testCalculateSpeakActionString() {
        // actors to pass into action
        ActionCalculation actionCalculation = new ActionCalculation();
        assertNotNull(actionCalculation.calculateSpeakActionString());
    }
    //////////////////////////////////////////////////////////////////////////////

    /**
     * Run all Action & ActionCalculation tests from an outside source.
     */
    @Test
    public void runAllTests() {
        // Action tests
        testActionInitialization();
        testGetTarget();
        testGetPlayer();
        testCreateAndPrintAction();

        // ActionCalculation tests
        testActionCalculationInitialization();
        testCalculateEatStatsAndActionString();
        testCalculateMoveStatsAndActionString();
        testCalculateAttackStatsAndActionString();
        testCalculateSpeakActionString();
    }
}