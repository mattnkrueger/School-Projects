package test;

import easter.GregorianEaster;
import utils.Month;

// import junit dependencies
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GregorianEasterJUnitTest {
    @Test
    void testInitialization() {
        GregorianEaster gregorianEaster = new GregorianEaster(2024);
        assertNotNull(gregorianEaster);
    }

    @Test
    public void testEasterDate() {
        GregorianEaster gregorianEaster = new GregorianEaster(2024);
        gregorianEaster.calculateEaster();
        assertEquals("Gregorian Easter for Year 2024: March 31", gregorianEaster.toString());
    }

    @Test
    public void testNegativeValue() {
        GregorianEaster gregorianEaster = new GregorianEaster(-1000);
        gregorianEaster.calculateEaster();
        assertEquals("Gregorian Easter for Year -1000: Error: Year Entered is Invalid", gregorianEaster.toString());
    }

    @Test
    public void testCycleCounts() {
        for (int i = 1; i < 5700001; i++) {
            GregorianEaster newGregorianEaster = new GregorianEaster(i);
            newGregorianEaster.calculateEaster();
            Month.updateCounts(newGregorianEaster.getMonthInt(), newGregorianEaster.getDay());
        }
        assertEquals(0, Month.getMarchCounts()[19]);
        assertEquals(189525, Month.getMarchCounts()[30]);
        assertEquals(189525, Month.getAprilCounts()[19]);
        assertEquals(0, Month.getAprilCounts()[29]);
    }
}
