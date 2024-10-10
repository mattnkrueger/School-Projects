package easter;

import utils.Month;

public class Easter {
    // attributes

    private int year;
    private int monthInt;
    private String monthStr;
    private int day;
    private String easterDate;
    private boolean invalidYear;

    // constructor
    public Easter() {
        this(0);
    }

    public Easter(int year) {
        // check for negative value
        if (year < 0) {
            this.invalidYear = true;
        }
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonthInt(int monthInt) {
        this.monthInt = monthInt;
    }

    public int getMonthInt() {
        return monthInt;
    }

    public void setMonthStr(int monthInt) {
        this.monthStr = Month.getMonths().get(monthInt);
    }

    public String getMonthStr() {
        return monthStr;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getDay() {
        return day;
    }

    public void setEasterDate() {
        this.easterDate = monthStr + " " + day;
    }

    public String getEasterDate() {
        return easterDate;
    }

    @Override
    public String toString() {
        String header;
        String format;
        String body;

        // header
        format = "Easter for Year %d: ";
        header = String.format(format, year);

        // body
        body = easterDate;

        if (invalidYear) {
            return header + "Error: Year Entered is Invalid";
        } else {
            return header + body;
        }
    }
}
