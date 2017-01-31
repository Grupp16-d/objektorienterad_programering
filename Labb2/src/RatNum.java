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

    public RatNum(String s){
        this.a = parse(s).getNumerator();
        this.b = parse(s).getDenominator();
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

    //Steg s
    //Parses a RatNum to string
    @Override
    public String toString(){
        a = getNumerator();
        b = getDenominator();
        String strRatNum = a + "/" + b;
        return strRatNum;
    }

    //Parses a RatNum to a double
    public double toDouble(){
        return (double)a/b;
    }

    public static RatNum parse(String s){
        // Splits at "/" and checks if there's 2 indexes which are numbers, returns the numbers as RatNum
        s = s.replaceAll("\\s","");
        String[] str = s.split("/");

        if(str.length == 1){
            boolean numbericA = isNumeric(str[0]);
            if(numbericA){
                int a = Integer.parseInt(str[0]);
                int b = 1;
                return new RatNum(a, b);
            }else{
                throw new NumberFormatException("This is not a valid number!");

            }
        }else if(str.length == 2){
            boolean numbericA = isNumeric(str[0]);
            boolean numbericB = isNumeric(str[1]);
            if(numbericA && numbericB) {
                int a = Integer.parseInt(str[0]);
                int b = Integer.parseInt(str[1]);
                return new RatNum(a, b);
            }
        }
        throw new NumberFormatException("This is not a valid number!");

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

    @Override
    public Object clone() {
        return new RatNum(a, b);
    }


    public boolean equals(RatNum y){
        boolean eq = a == y.getNumerator() && b == getDenominator();
        return eq;
    }

    public boolean lessThan(RatNum y){
        double a = getRatNum().toDouble();
        double b = y.toDouble();

        return (a < b);
    }

    public RatNum add(RatNum y){
        int xa = a * y.getDenominator();
        int ya = y.getNumerator() * b;
        a = xa + ya;
        b = b * y.getDenominator();

        return new RatNum(a, b);
    }

    public RatNum sub(RatNum y){
        int xa = a * y.getDenominator();
        int ya = y.getNumerator() * b;
        int xaya = xa - ya;
        int xbyb= b * y.getDenominator();

        return new RatNum(xaya, xbyb);
    }

    public RatNum mul(RatNum y){
        int numA = a * y.getNumerator();
        int numB = b * y.getDenominator();

        return new RatNum(numA, numB);
    }

    public RatNum div(RatNum y){
        int numA = a * y.getDenominator();
        int numB = b * y.getNumerator();

        return new RatNum(numA, numB);
    }

    public int getNumerator(){
        return a;
    }

    public int getDenominator(){
        return b;
    }

    public RatNum getRatNum(){
        return new RatNum(a, b);
    }
}


