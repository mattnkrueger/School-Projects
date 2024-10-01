// package module
package test;

// import junit dependencies
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// import src dependencies
import numbers.RationalNumber;

/**
 * Testing for RationalNumber class
 */
public class RationalNumberJUnitTest {

    /**
     * Test to ensure the object initializes correctly
     */
    @Test
    void testRationalNumberInitialization() {
        RationalNumber rationalNumber = new RationalNumber(4, 2);
        assertNotNull(rationalNumber);
    }

    /**
     * Test to ensure that an error is thrown if a 0 is passed as denominator
     */
    @Test
    public void testRationalNumberWithZeroDenominator() {
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            new RationalNumber(1, 0);
        });
    }

    /**
     * Test for the simplify() (includes calculatesGreatestCommonDivisor() gcd method) method to obtain simplestNumerator and simplestDenominator
     */
    @Test
    public void testSimplification() {
        RationalNumber rationalNumber = new RationalNumber(40, 12);
        assertEquals(10, rationalNumber.getSimplestNumerator());
        assertEquals(3, rationalNumber.getSimplestDenominator());
    }

    /**
     * Test for the output of the rational number definition
     */
    @Test
    public void testGetDefinition() {
        String definition = RationalNumber.getDefinition();
        assertEquals("A rational number is a number that can be expressed as a fraction where both the numerator and denominator are integers.", definition);
    }

    /**
     * Test for ensure that if a negative integer is entered into the denominator, both numerator and denominator will be multiplied by -1
     */
    @Test
    public void testEnsurePositiveDenominator() {
        RationalNumber rationalNumber = new RationalNumber(2, -5); // this is set in simplest form to test sole the function of changing the sign. See test for simplification.
        assertEquals(-2, rationalNumber.getSimplestNumerator());
        assertEquals(5, rationalNumber.getSimplestDenominator());
    }

    /**
     * Run all RationalNumberJUnitTest tests from an outside source.
     */
    @Test
    public void runAllTests() {
        // RationalNumberJUnitTest tests
        testRationalNumberInitialization();
        testRationalNumberWithZeroDenominator();
        testSimplification();
        testGetDefinition();
        testEnsurePositiveDenominator();
    }

}
