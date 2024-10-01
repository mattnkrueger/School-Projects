// add to package
package entities;

// import junit dependencies
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// import src dependencies
import entities.Transform;
import entities.Entity;
import entities.Location;

/**
 * Test Location class
 */
public class LocationJUnitTest {

    /**
     * Test to ensure the Location object initializes correctly
     */
    @Test
    public void testLocationInitialization() {
        Location location = new Location();
        assertNotNull(location);
    }

    /**
     * Test toString() override
     */
    @Test
    public void testToString() {
        Location location = new Location();
        assertEquals("Location: Location 🟥\n|_ Transform: xPos: 0, yPos: 0, rotation: 0.000000, speed: 0\n|_ Address: Address\n", location.toString());
    }

    /**
     * Test getLocation()
     */
    @Test
    public void testGetLocation() {
        Location location = new Location("Name", new Transform(), "default-red", "ADDRESS");
        assertEquals("ADDRESS", location.getLocation());
    }

    /**
     * Run all LocationJUnitTest tests from an outside source.
     */
    @Test
    public void runAllTests() {
        // LocationJUnitTest tests
        testLocationInitialization();
        testToString();
        testGetLocation();
    }

}
