public class RatNum {
    public int a,b;

    //Steg 2
    public RatNum(){
        this.a = 0;
        this.b = 1;
    }

    public RatNum(int a){
        this.a = a;
        this.b = 1;
    }

    public RatNum(int a, int b){
        if(b == 0) {
            throw new NumberFormatException("Denominator = 0");
        }else if(a == 0){
            this.a = a;
            this.b = 1;
        }else if(b < 0) {
            a = a * -1;
            b = b * -1;
            this.a = a / sgd(a, b);
            this.b = b / sgd(a, b);
        }else {
            this.a = a / sgd(a, b);
            this.b = b / sgd(a, b);
        }
    }

    public RatNum(RatNum x){
        this.a = x.getNumerator();
        this.b = x.getDenominator();
    }

    public int getNumerator(){
        return a;
    }

    public int getDenominator(){
        return b;
    }

    //Steg 1
    //största gemensama delare metod
    static int sgd(int m, int n) {
        if (m == 0 || n == 0) {
            throw new IllegalArgumentException();
        }
        m = Math.abs(m);
        n = Math.abs(n);

        while (n != 0) {
            int r = n;
            n = m % n;
            m = r;
        }
        return m;
    }

    //Steg 3
    //Parses a RatNum to string
    public String toString(RatNum x){
        a = x.getNumerator();
        b = x.getDenominator();
        String strRatNum = a + "/" + b;
        return strRatNum;
    }

    //Parses a RatNum to a double
    public double toDouble(RatNum x){
        a = x.getNumerator();
        b = x.getDenominator();
        double ab = a/b;
        double rounded = Math.round(ab *100) / 100;
        return rounded;
    }

    public static RatNum parse(String s){
        // Splits at "/" and checks if there's 2 indexes which are numbers, returns the numbers as RatNum
        String[] str = s.split("/");
        boolean numbericA = isNumeric(str[0]);
        boolean numbericB = isNumeric(str[1]);

        if(str.length == 2 && numbericA && numbericB){
            int a = Integer.parseInt(str[0]);
            int b = Integer.parseInt(str[1]);
            return new RatNum(a, b);
        }else{
            throw new NumberFormatException("This is not a valid number!");

        }
    }

    // Checks if a str is numberic
    private static boolean isNumeric(String str) {
        try {
            double d = Integer.parseInt(str);
        }
        catch(NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}


