package numbers;

/**
 * RationalNumber
 * explore rational numbers
 */
public class RationalNumber {
    /**
     * numerator of rational number
     */
    private int numerator;

    /**
     * simplest numberator of rational number (after simplify())
     */
    private int simplestNumerator;

    /**
     * denominator of rational number
     */
    private int denominator;

    /**
     * simplest denominator of rational number (after simplify())
     */
    private int simplestDenominator;

    /**
     * RationalNumber (non-argument constructor)
     * 1. calls dyadic constructor with defaults: numerator = 0, denominator = 1
     */
    public RationalNumber() {
        this(0, 1);
    }

    /**
     * RationalNumber (dyadic constructor)
     * 1. initializes attributes to user input
     * 2. checks validity of rational number (if denominator != 0)
     * 3. output simplest form if valid
     *
     * @param numerator
     * @param denominator
     */
    public  RationalNumber(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;

        // last line of defence. Should never get flagged when using Main.java provided in S12
        if (denominator == 0) {
            throw new ArithmeticException("User inputted a non-rational number; denominator cannot be 0");
        } else {
            simplify();
            ensurePositiveSimplifiedDenominator();
        }
    }

    /**
     * @param numerator
     */
    private void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    /**
     * @return numerator
     */
    public int getNumerator() {
        return numerator;
    }

    /**
     * @param denominator
     */
    private void setDenominator(int denominator) {
        this.denominator = denominator;
    }

    /**
     * @return denominator
     */
    public int getDenominator() {
        return denominator;
    }

    /**
     * @param simplestNumerator
     */
    private void setSimplestNumerator(int simplestNumerator) {
        this.simplestNumerator = simplestNumerator;
    }

    /**
     * @return simplestNumerator
     */
    public int getSimplestNumerator() {
        return simplestNumerator;
    }

    /**
     * @param simplestDenominator
     */
    private void setSimplestDenominator(int simplestDenominator) {
        this.simplestDenominator = simplestDenominator;
    }

    /**
     * @return simplestDenominator
     */
    public int getSimplestDenominator() {
        return simplestDenominator;
    }

    /**
     * @return the definition of a rational number (paraphrased)
     */
    public static String getDefinition() {
        String definition = "A rational number is a number that can be expressed as a fraction where both the numerator and denominator are integers.";
        return definition;
    }

    /**
     * converts rational number to the simplest form
     */
    private void simplify() {
        // calculate gcd
        int gcd;
        gcd = calculateGreatestCommonDivisor(numerator, denominator);

        // initialize simplest values
        this.simplestNumerator = (numerator / gcd);
        this.simplestDenominator = (denominator / gcd);
    }

    /**
     * return the largest common factor between two positive, real integers a & b
     *
     * @param a (either numerator or denominator)
     * @param b (either numerator or denominator)
     */
    private static int calculateGreatestCommonDivisor(int a, int b) { // using a/b in place of x/y (as taught in Discrete Structures course)
        int remainder;
        while (b != 0) {
            remainder = a % b;
            a = b;
            b = remainder;
        }
        return a;
    }

    /**
     * changes sign parity of simplified values if denominator = 0
     */
    private void ensurePositiveSimplifiedDenominator() {
        if (this.simplestDenominator < 0) {
            this.simplestNumerator = (-1 * simplestNumerator);
            this.simplestDenominator = (-1 * simplestDenominator);
        }
    }

    /**
     * @return value of the rational number (numerator/denominator)
     */
    private double getValue() {
        double value;
        value = (double) numerator / denominator;
        return value;
    }

    /**
     * @return true if in original values in simplest form
     */
    private boolean isSimplestForm() {
        return ((numerator == simplestNumerator) && (denominator == simplestDenominator));
    }

    /**
     * OVERRIDE: base Object class
     * @return string representation of rational number
     */
    @Override
    public String toString() {
        String stringRepresentation;
        String originalRationalValues;
        String simplifiedRationalValues;
        String calculatedRationalValue;

        // original
        originalRationalValues = "Original Values:\n" + "|__ numerator: " + numerator + "\n|__ denominator: " + denominator + "\n";

        // simplified
        String simplestMessage;
        if (isSimplestForm()) {
            simplestMessage = "\n * original values are in simplest form * \n";
        } else {
            simplestMessage = "\n";
        }
        simplifiedRationalValues = "\n" + "Simplified Values:\n" + "|__ numerator: " + simplestNumerator+ "\n|__ denominator: " + simplestDenominator + simplestMessage;

        // calculated
        calculatedRationalValue = "\n" + "Calculated Rational Value:\n" + "|__ Ratio (numerator/denominator): " + getValue() + "\n";

        // combine all strings
        stringRepresentation = originalRationalValues + simplifiedRationalValues + calculatedRationalValue;
        return stringRepresentation;
    }
}
