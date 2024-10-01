// add to package
package styling;

// import junit dependencies
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// import src dependencies
import styling.ColorDict;

/**
 * Test ColorDict class
 */
public class ColorDictJUnitTest {

    /**
     * Test to ensure the ColorDict object initializes correctly
     */
    @Test
    public void testColorDictInitialization() {
        ColorDict colorDict = new ColorDict();
        assertNotNull(colorDict);
    }


    /**
     * Test getCLIColor()
     */
    @Test
    public void testGetCLIColor() {
        ColorDict colorDict = new ColorDict();

        // key present in hashmap
        assertEquals("\u001B[0m", colorDict.getCLIColor("RESET"));

        // key not present in hashmap
        assertEquals("\u001B[0m", colorDict.getCLIColor("this is not a key in the hash map"));

    }

    /**
     * Run all ColorDictJUnitTest tests from an outside source.
     */
    @Test
    public void runAllTests() {
        // ColorDictJUnitTest tests
        testColorDictInitialization();
        testGetCLIColor();
    }

}
