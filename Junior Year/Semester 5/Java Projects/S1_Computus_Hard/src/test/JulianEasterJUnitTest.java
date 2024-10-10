package test;

import easter.JulianEaster;
import utils.Month;

// import junit dependencies
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JulianEasterJUnitTest {
    @Test
    void testInitialization() {
        JulianEaster julianEaster = new JulianEaster(2024);
        assertNotNull(julianEaster);
    }

    @Test
    public void testEasterDate() {
        JulianEaster julianEaster = new JulianEaster(2024);
        julianEaster.calculateEaster();
        assertEquals("Julian Easter for Year 2024: April 22", julianEaster.toString());
    }

    @Test
    public void testNegativeValue() {
        JulianEaster julianEaster = new JulianEaster(-1000);
        julianEaster.calculateEaster();
        assertEquals("Julian Easter for Year -1000: Error: Year Entered is Invalid", julianEaster.toString());
    }

    @Test
    public void testCycleCounts() {
        for (int i = 1; i < 532; i++) {
            JulianEaster newJulianEaster = new JulianEaster(i);
            newJulianEaster.calculateEaster();
            Month.updateCounts(newJulianEaster.getMonthInt(), newJulianEaster.getDay());
        }
        assertEquals(0, Month.getMarchCounts()[19]);
        assertEquals(20, Month.getMarchCounts()[30]);
        assertEquals(16, Month.getAprilCounts()[19]);
        assertEquals(0, Month.getAprilCounts()[29]);
    }
}
