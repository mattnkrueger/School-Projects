// import project packages
import numbers.RationalNumber;

// import java util dependencies
import java.util.InputMismatchException;
import java.util.Scanner;

import test.JUnitTesterS12;

public class Main {
    /**
     * is the program running?
     */
    private static boolean programRunning = true;

    /**
     * user numerator input
     */
    private static int numerator;

    /**
     * user denominator input
     */
    private static int denominator;

    /**
     * declare and initialized scanner object for user input
     */
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * obtain new numerator and denominator for use in new RationalNumber object
     */
    private static void setUserValues() {
        boolean validNumerator = false;
        boolean validDenominator = false;

        while (!validNumerator) {
            System.out.println("Enter a numerator: ");
            try {
                numerator = scanner.nextInt();
                validNumerator = true;
            } catch (InputMismatchException e) {
                System.out.println("Error: Numerator must be an Integer.");
                scanner.nextLine();
            }
        }

        while (!validDenominator) {
            System.out.println("Enter a denominator: ");
            try {
                denominator = scanner.nextInt();
                if (denominator != 0) {
                    validDenominator = true;
                } else {
                    throw new RuntimeException("Error: Denominator cannot be 0.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Denominator must be an Integer.");
                scanner.nextLine();
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static int getUserAction() {
        int actionChosen = 0;
        boolean validAction = false;

        while (!validAction) {
            System.out.println("Choose an Action:\n   1. Enter Values to Check if Rational\n   2. View Definition of Rational Numbers\n   3. Run JUnit Testing\n   4. END PROGRAM\n Input: ");
            try {
                actionChosen = scanner.nextInt();
                validAction = true;
            } catch (InputMismatchException e) {
                System.out.println("Error: action must be an Integer.\nEnter a new value:");
                scanner.nextLine();
            }
        }
        return actionChosen;
    }

    /**
     * define user interaction loop
     * @param args
     */
    public static void main(String[] args) {
        // initialize rational number object
        RationalNumber rationalNumber;

        // user interaction loop
        int actionChosen;
        while (programRunning) {
            actionChosen = getUserAction();
            if (actionChosen == 1) {
                setUserValues();
                rationalNumber = new RationalNumber(numerator, denominator);
                System.out.println(rationalNumber);
            } else if (actionChosen == 2) {
                System.out.println(RationalNumber.getDefinition());
            } else if (actionChosen == 3) {
                System.out.println("\n");
                JUnitTesterS12.runS12JUnitTests();
                System.out.println("\n");
            } else if (actionChosen == 4) {
                programRunning = false;
            } else {
                System.out.println("Enter valid Action");
            }
        }
    }
}
