// add to package
package styling;

// import junit dependencies
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// import src dependencies
import styling.CenterCLIMessage;

/**
 * Test CenterCLIMessage class
 */
public class CenterCLIMessageJUnitTest {

    /**
     * Test to ensure the CenterCLIMessage object initializes correctly
     */
    @Test
    public void testCenterCLIMessageInitialization() {
        CenterCLIMessage centerCLIMessage = new CenterCLIMessage("Text To Center", 900);
        assertNotNull(centerCLIMessage);
    }


    /**
     * Test getTextToCenter()
     */
    @Test
    public void testGetTextToCenter() {
        CenterCLIMessage centerCLIMessage = new CenterCLIMessage("Text To Center", 900);
        assertEquals("Text To Center", centerCLIMessage.getTextToCenter());
    }

    /**
     * Test getTerminalWidth()
     */
    @Test
    public void testGetTerminalWidth() {
        CenterCLIMessage centerCLIMessage = new CenterCLIMessage("Text To Center", 900);
        assertEquals(900, centerCLIMessage.getTerminalWidth());
    }

    /**
     * Run all CenterCLIMessageJUnitTest tests from an outside source.
     */
    @Test
    public void runAllTests() {
        // CenterCLIMessageJUnitTest tests
        testCenterCLIMessageInitialization();
        testGetTextToCenter();
        testGetTerminalWidth();
    }

}
