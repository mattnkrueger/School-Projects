package easter;

public class JulianEaster extends Easter {
    // The following attributes reflect variables used in Wikipedia article
    private int a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int m;
    private int n;

    public JulianEaster() {
        this(0);
    }

    public JulianEaster(int year) {
        super(year);
    }

    public void calculateEaster() {
        // Julian methods
        setA();
        setB();
        setC();
        setD();
        setE();
        setM();
        setN();

        // super methods
        setMonthInt(m);
        setMonthStr(m);
        setDay(n);
        setEasterDate();
    }

    private void setA() {
        this.a = super.getYear() % 4;
    }

    private void setB() {
        this.b = super.getYear() % 7;
    }

    private void setC() {
        this.c = super.getYear() % 19;
    }

    private void setD() {
        this.d = (((19*c) + 15) % 30);
    }

    private void setE() {
        this.e = ((2*a) + (4*b) - d + 34) % 7;
    }

    private void setM() {
        this.m = (d + e + 114) / 31;
    }

    private void setN() {
        this.n = ((d + e + 114) % 31) + 1;
    }

    @Override
    public String toString() {
        String easterString = super.toString();
        return "Julian " + easterString;
    }
}
