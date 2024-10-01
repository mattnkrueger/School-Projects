// add to package
package test;

// import numbers test modules
import numbers.RationalNumber;

/**
 * Test all modules included in /test
 */
public class JUnitTesterS12 {

    /**
     * call all runAllTests for testing modules in project
     */
    public static void runS12JUnitTests() {
        RationalNumberJUnitTest rationalNumberJUnitTest = new RationalNumberJUnitTest();
        System.out.println("running S12 JUnit tests...");
        System.out.println("-----------------------------------------------------");
        rationalNumberJUnitTest.runAllTests();
        System.out.println("-----------------------------------------------------");
        System.out.println("Success!");
        System.out.println("S12_RationalNumber_Medium: All JUnit Tests Passed");
    }

    /**
     * run all tests from inside this module
     * @param args
     */
    public static void main(String[] args) {
        runS12JUnitTests();
    }
}
