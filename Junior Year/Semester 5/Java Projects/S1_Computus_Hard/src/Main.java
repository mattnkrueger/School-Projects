import easter.GregorianEaster;
import easter.JulianEaster;
import utils.CountHistogram;
import utils.Month;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    private static boolean running = true;

    private static void setRunning(boolean flag) {
        running = flag;
    }

    private static boolean isRunning() {
        return running;
    }

    private static int getUserAction() {
        int actionChosen = 0;
        boolean validAction = false;

        while (!validAction) {
            System.out.println("Choose an Action:\n   1. Compute Gregorian Easter for Specified Date\n   2. Compute Julian Easter for Specified Date\n   3. Gregorian 5,700,000 Year Cycle Histogram\n   4. Julian 532 Year Cycle Histogram\n   5. EXIT PROGRAM\n");
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

    private static String getCountsOutput() {
        return "March Dates:\n" + Month.getCountsAsString(3) + "\nApril Dates:\n" + Month.getCountsAsString(4);
    }

    private static void getGregorianEasterCycleHistograms() {
        for (int i = 1; i < 5700001; i++) {
            GregorianEaster newGregorianEaster = new GregorianEaster(i);
            newGregorianEaster.calculateEaster();
            Month.updateCounts(newGregorianEaster.getMonthInt(), newGregorianEaster.getDay());
        }
        CountHistogram marchHistogram = new CountHistogram("Gregorian 5,700,000 Cycle - March Counts", Month.getMarchCounts());
        CountHistogram aprilHistogram = new CountHistogram("Gregorian 5,700,000 Cycle - April Counts", Month.getAprilCounts());

        marchHistogram.showHistogram();
        aprilHistogram.showHistogram();
    }

    private static void getJulianEasterCycleHistograms() {
        for (int i = 1; i < 533; i++) {
            JulianEaster newJulianEaster = new JulianEaster(i);
            newJulianEaster.calculateEaster();
            Month.updateCounts(newJulianEaster.getMonthInt(), newJulianEaster.getDay());
        }
        CountHistogram marchHistogram = new CountHistogram("Julian 532 Year Cycle - March Counts", Month.getMarchCounts());
        CountHistogram aprilHistogram = new CountHistogram("Julian 532 Year Cycle - April Counts", Month.getAprilCounts());

        marchHistogram.showHistogram();
        aprilHistogram.showHistogram();
    }

    private static int getYearSelected() {
        int yearSelected = 0;
        boolean validAction = false;
        while (!validAction) {
            System.out.println("Enter a Year: ");
            try {
                yearSelected = scanner.nextInt();
                validAction = true;
            } catch (InputMismatchException e) {
                System.out.println("Error: Year Must be an Integer.");
                scanner.nextLine();
            }
        }
        return yearSelected;
    }

    public static void main(String[] args) {
        int action;
        String toPrint;
        int yearSelected;
        while (isRunning()) {
            action = getUserAction();
            if (action == 1) {
                GregorianEaster gregorianEaster = new GregorianEaster(getYearSelected());
                gregorianEaster.calculateEaster();
                toPrint = gregorianEaster.toString();
            } else if (action == 2) {
                JulianEaster julianEaster = new JulianEaster(getYearSelected());
                julianEaster.calculateEaster();
                toPrint = julianEaster.toString();
            } else if (action == 3) {
                toPrint = "Printing Gregorian Easter Histograms...";
                getGregorianEasterCycleHistograms();
            } else if (action == 4) {
                toPrint = "Printing Julian Easter Histograms...";
                getJulianEasterCycleHistograms();
            } else if (action == 5) {
                setRunning(false);
                toPrint = "Ending Program...";
            } else {
                toPrint = "Invalid action chosen; Read Directions Carefully.";
            }
            System.out.println(toPrint);
        }
    }
}