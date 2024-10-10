package utils;

import java.util.HashMap;

public final class Month {
    private static final HashMap<Integer, String> months = new HashMap<>() {{
        put(1, "January");
        put(2, "February");
        put(3, "March");
        put(4, "April");
        put(5, "May");
        put(6, "June");
        put(7, "July");
        put(8, "August");
        put(9, "September");
        put(10, "October");
        put(11, "November");
        put(12, "December");
    }};

    private static final int[] januaryCounts = new int[31];
    private static final int[] februaryCounts = new int[29];
    private static final int[] marchCounts = new int[31];
    private static final int[] aprilCounts = new int[30];
    private static final int[] mayCounts = new int[31];
    private static final int[] juneCounts = new int[30];
    private static final int[] julyCounts = new int[31];
    private static final int[] augustCounts = new int[31];
    private static final int[] septemberCounts = new int[30];
    private static final int[] octoberCounts = new int[31];
    private static final int[] novemberCounts = new int[30];
    private static final int[] decemberCounts = new int[31];

    public static HashMap<Integer, String> getMonths() {
        return months;
    }

    public static void incrementJanuaryCounts(int day) {
        januaryCounts[day-1] = januaryCounts[day-1] + 1;
    }

    public static void incrementFebruaryCounts(int day) {
        februaryCounts[day-1] = februaryCounts[day-1] + 1;
    }

    public static void incrementMarchCounts(int day) {
        marchCounts[day-1] = marchCounts[day-1] + 1;
    }

    public static void incrementAprilCounts(int day) {
        aprilCounts[day-1] = aprilCounts[day-1] + 1;
    }

    public static void incrementMayCounts(int day) {
        mayCounts[day-1] = mayCounts[day-1] + 1;
    }

    public static void incrementJuneCounts(int day) {
        juneCounts[day-1] = juneCounts[day-1] + 1;
    }

    public static void incrementJulyCounts(int day) {
        julyCounts[day-1] = julyCounts[day-1] + 1;
    }

    public static void incrementAugustCounts(int day) {
        augustCounts[day-1] = augustCounts[day-1] + 1;
    }

    public static void incrementSeptemberCounts(int day) {
        septemberCounts[day-1] = septemberCounts[day-1] + 1;
    }

    public static void incrementOctoberCounts(int day) {
        octoberCounts[day-1] = octoberCounts[day-1] + 1;
    }

    public static void incrementNovemberCounts(int day) {
        novemberCounts[day-1] = novemberCounts[day-1] + 1;
    }

    public static void incrementDecemberCounts(int day) {
        decemberCounts[day-1] = decemberCounts[day-1] + 1;
    }

    public static int[] getJanuaryCounts() {
        return januaryCounts;
    }

    public static int[] getFebruaryCounts() {
        return februaryCounts;
    }

    public static int[] getMarchCounts() {
        return marchCounts;
    }

    public static int[] getAprilCounts() {
        return aprilCounts;
    }

    public static int[] getMayCounts() {
        return mayCounts;
    }

    public static int[] getJuneCounts() {
        return juneCounts;
    }

    public static int[] getJulyCounts() {
        return julyCounts;
    }

    public static int[] getAugustCounts() {
        return augustCounts;
    }

    public static int[] getSeptemberCounts() {
        return septemberCounts;
    }

    public static int[] getOctoberCounts() {
        return octoberCounts;
    }

    public static int[] getNovemberCounts() {
        return novemberCounts;
    }

    public static int[] getDecemberCounts() {
        return decemberCounts;
    }

    public static void updateCounts(int month, int day) {
        if (month == 1) {
            incrementJanuaryCounts(day);
        } else if (month == 2) {
            incrementFebruaryCounts(day);
        } else if (month == 3) {
            incrementMarchCounts(day);
        } else if (month == 4) {
            incrementAprilCounts(day);
        } else if (month == 5) {
            incrementMayCounts(day);
        } else if (month == 6) {
            incrementJuneCounts(day);
        } else if (month == 7) {
            incrementJulyCounts(day);
        } else if (month == 8) {
            incrementAugustCounts(day);
        } else if (month == 9) {
            incrementSeptemberCounts(day);
        } else if (month == 10) {
            incrementOctoberCounts(day);
        } else if (month == 11) {
            incrementNovemberCounts(day);
        } else if (month == 12) {
            incrementDecemberCounts(day);
        } else {
            throw new IllegalArgumentException("Invalid month: " + month);
        }
    }

    public static String getCountsAsString(int month) {
        String monthStr = months.get(month);
        int[] countArray;
        if (month == 1) {
            countArray = getJanuaryCounts();
        } else if (month == 2) {
            countArray = getFebruaryCounts();
        } else if (month == 3) {
            countArray = getMarchCounts();
        } else if (month == 4) {
            countArray = getAprilCounts();
        } else if (month == 5) {
            countArray = getMayCounts();
        } else if (month == 6) {
            countArray = getJuneCounts();
        } else if (month == 7) {
            countArray = getJulyCounts();
        } else if (month == 8) {
            countArray = getAugustCounts();
        } else if (month == 9) {
            countArray = getSeptemberCounts();
        } else if (month == 10) {
            countArray = getOctoberCounts();
        } else if (month == 11) {
            countArray = getNovemberCounts();
        } else if (month == 12) {
            countArray = getDecemberCounts();
        } else {
            throw new IllegalArgumentException("Invalid month: " + month);
        }

        int day;
        StringBuilder countsString = new StringBuilder();
        for (int i = 0; i < countArray.length; i++) {
            day = i + 1;
            String dateCount = "|__ " + monthStr + " " + day + ": " + countArray[i] + "\n";
            countsString.append(dateCount);
        }
        return countsString.toString();
    }
}