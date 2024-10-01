// add to package
package game;

// import junit dependencies
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// import src dependencies


/**
 * Test GameVariables class
 */
public class GameVariablesJUnitTest {

    /**
     * Test to ensure the GameVariables object initializes correctly
     */
    @Test
    public void testGameVariablesInitialization() {
        GameVariables gameVariables = new GameVariables();
        assertNotNull(gameVariables);
    }

    /**
     * Test getPlayer()
     */
    @Test
    public void testGetPlayer() {
        GameVariables gameVariables = new GameVariables();
        assertEquals("PLAYER", gameVariables.getPlayer().getName());
    }

    /**
     * Test isGameFinished()
     */
    @Test
    public void testIsGameFinished() {
        GameVariables gameVariables = new GameVariables();
        assertFalse(gameVariables.isGameFinished());
    }

    /**
     * Test getCurrentEntities
     */
    @Test
    public void TestGetCurrentEntities() {
        GameVariables gameVariables = new GameVariables();
        assertEquals("----------- START Current Entities ---------------------------------------\n" +
                "\n" +
                "Player:\n" +
                "--------------------------------------------------------------------\n" +
                "Actor: PLAYER \uD83E\uDD37\u200D♂\uFE0F ⭐\n" +
                "|_ Transform: xPos: 100, yPos: 300, rotation: 20.000000, speed: 100\n" +
                "|_ Health: 1000.000000\n" +
                "\n" +
                "Actors:\n" +
                "--------------------------------------------------------------------\n" +
                "Actor: Voldemort \uD83E\uDDD9\u200D♂\uFE0F\n" +
                "|_ Transform: xPos: 100, yPos: 100, rotation: 50.000000, speed: 800\n" +
                "|_ Health: 1000.000000\n" +
                "\n" +
                "Actor: Bellatrix Lestrange \uD83E\uDDD9\n" +
                "|_ Transform: xPos: 100, yPos: 100, rotation: 50.000000, speed: 5\n" +
                "|_ Health: 500.000000\n" +
                "\n" +
                "Actor: Dementor \uD83D\uDD74\uFE0F\n" +
                "|_ Transform: xPos: 100, yPos: 100, rotation: 50.000000, speed: 300\n" +
                "|_ Health: 100.000000\n" +
                "\n" +
                "Obstacle: The Veil \uD83E\uDEA8\n" +
                "|_ Transform: xPos: -1000, yPos: -5000, rotation: 32.000000, speed: 0\n" +
                "|_ Size: 100\n" +
                "\n" +
                "Obstacle: Whomping Willow \uD83C\uDF33\n" +
                "|_ Transform: xPos: 3000, yPos: 3000, rotation: 30.000000, speed: 25\n" +
                "|_ Size: 1000\n" +
                "\n" +
                "Locations:\n" +
                "--------------------------------------------------------------------\n" +
                "Location: Diagon Alley \uD83C\uDFEC\n" +
                "|_ Transform: xPos: 50, yPos: 50, rotation: 20.000000, speed: 0\n" +
                "|_ Address: Behind the Leaky Cauldron, London\n" +
                "\n" +
                "Location: Platform 9 & 3/4 \uD83D\uDE86\n" +
                "|_ Transform: xPos: 100, yPos: 100, rotation: 30.000000, speed: 0\n" +
                "|_ Address: King's Cross Station, London\n" +
                "\n" +
                "Location: Home \uD83C\uDFE1\n" +
                "|_ Transform: xPos: 0, yPos: 0, rotation: 0.000000, speed: 0\n" +
                "|_ Address: Number 4, Privet Drive, Little Whinging, Surrey\n" +
                "\n" +
                "Location: Hogwarts \uD83C\uDFF0\n" +
                "|_ Transform: xPos: 3000, yPos: 3000, rotation: 0.000000, speed: 0\n" +
                "|_ Address: Unknown, Scotland\n" +
                "\n" +
                "----------- END Current Entities ---------------------------------------\n" , gameVariables.getCurrentEntities());
    }

    /**
     * Run all GameVariablesJUnitTest tests from an outside source.
     */
    @Test
    public void runAllTests() {
        // GameVariablesJUnitTest tests
        testGameVariablesInitialization();
        testGetPlayer();
        testIsGameFinished();
        TestGetCurrentEntities();
    }

}
