package easter;

public class GregorianEaster extends Easter {
    private int a;
    // The following attributes reflect variables used in Wikipedia article
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;

    public GregorianEaster() {
        this(0);
    }

    public GregorianEaster(int year) {
        super(year);
    }

    public void calculateEaster() {
        // Gregorian methods
        setA();
        setB();
        setC();
        setD();
        setE();
        setF();
        setG();
        setH();
        setI();
        setK();
        setL();
        setM();
        setN();
        setO();

        // super methods
        setMonthInt(n);
        setMonthStr(n);
        setDay(o+1);
        setEasterDate();
    }

    private void setA() {
        this.a = this.getYear() % 19;
    }

    private void setB() {
        this.b = this.getYear() / 100;
    }

    private void setC() {
        this.c = this.getYear() % 100;
    }

    private void setD() {
        this.d = b / 4;
    }

    private void setE() {
        this.e = b % 4;
    }

    private void setF() {
        this.f = (b + 8) / 25;
    }

    private void setG() {
        this.g = (b - f + 1) / 3;
    }

    private void setH() {
        this.h = ((19*a) + b - d - g + 15) % 30;
    }

    private void setI() {
        this.i = c / 4;
    }

    private void setK() {
        this.k = c % 4;
    }

    private void setL() {
        this.l = (32 + (2*e) + (2*i) - h - k) % 7;
    }

    private void setM() {
        this.m = (a + (11*h) + (22*l)) / 451;
    }

    private void setN() {
        this.n = (h + l - (7*m) + 114) / 31;
    }

    private void setO() {
        this.o = (h + l - (7*m) + 114) % 31;
    }

    public int getA() {
        return this.a;
    }

    public int getB() {
        return this.b;
    }

    public int getC() {
        return this.c;
    }

    public int getD() {
        return this.d;
    }

    public int getE() {
        return this.e;
    }

    public int getF() {
        return this.f;
    }

    public int getG() {
        return this.g;
    }

    public int getH() {
        return this.h;
    }

    public int getI() {
        return this.i;
    }

    public int getK() {
        return this.k;
    }

    public int getL() {
        return this.l;
    }

    public int getM() {
        return this.m;
    }

    public int getN() {
        return this.n;
    }

    public int getO() {
        return this.o;
    }

    @Override
    public String toString() {
        String easterString = super.toString();
        return "Gregorian " + easterString;
    }
}
