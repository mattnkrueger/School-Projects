// add to package
package styling;

// import junit dependencies
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// import src dependencies


/**
 * Test UnicodeEmoji class
 */
public class UnicodeEmojiJUnitTest {

    /**
     * Test to ensure the UnicodeEmoji object initializes correctly
     */
    @Test
    public void testUnicodeEmojiInitialization() {
        UnicodeEmoji unicodeEmoji = new UnicodeEmoji();
        assertNotNull(unicodeEmoji);
    }

    /**
     * Test getEmoji()
     */
    @Test
    public void testGetEmoji() {
        UnicodeEmoji unicodeEmoji = new UnicodeEmoji();

        // key present in the hashmap
        assertEquals("\uD83C\uDFF0", unicodeEmoji.getEmoji("castle"));

        // key not present in the hashmap
        assertEquals("\uD83D\uDFE5", unicodeEmoji.getEmoji("this is not a key in the hashmap"));
    }

    /**
     * Run all UnicodeEmojiJUnitTest tests from an outside source.
     */
    @Test
    public void runAllTests() {
        // UnicodeEmojiJUnitTest tests
        testUnicodeEmojiInitialization();
        testGetEmoji();
    }

}
