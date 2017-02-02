public class RatNum {
    private int a,b;

    //Constructors for RatNums
    public RatNum(){
        this.a = 0;
        this.b = 1;
    }

    public RatNum(int a){
        this.a = a;
        this.b = 1;
    }

    //When RatNum is called upon with 2 parameters an error is return if the b value is equal to 0. If a is equal to zero
    //the same logic applies as the empty constructor above. If b is negative both values are multiplied by -1 return being
    //divided by the greatest common divisor.
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

    // A method that returns the greatest common divisor of two ints
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

    //Parses a RatNum to a string
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

    //Parses a string as a Ratnum if the string contains a valid expression of a RatNum
    public static RatNum parse(String s){
        //Removes whitespaces
        s = s.replaceAll("\\s","");
        //Checks if the string contains a "/". If it does then the string is split at the "/" and if the resulting array
        //contains 2 indexes the method tries to convert the value of these to ints and call on the RatNum constructor
        //together with the two values. If the string does not contain a "/" then the string is tried as an int immediately.
        if(s.contains("/")){
            try{
                String[] str = s.split("/");
                if(str.length == 2) {
                    if(str[0] !=null || str[1] !=null){
                        try {
                            int a = Integer.parseInt(str[0]);
                            int b = Integer.parseInt(str[1]);
                            return new RatNum(a, b);
                        }
                        catch(NumberFormatException nfe) {
                            throw new NumberFormatException("This expression contains non ints");
                        }
                    }else{
                        throw new NumberFormatException("This expression contains non ints");
                    }

                }else{
                    throw new NumberFormatException("This is not a valid expression!");
                }
            }
            catch(ArrayIndexOutOfBoundsException e){
                throw new NumberFormatException("This is not a valid expression!");
            }
        }else{
            if(s !=null){
                try {
                    int a = Integer.parseInt(s);
                    int b = 1;
                    return new RatNum(a, b);
                }
                catch(NumberFormatException nfe) {
                    throw new NumberFormatException("This expression contains non ints");
                }
            }else{
                throw new NumberFormatException("This expression contains non ints");
            }
        }
    }

    //Returns the selected RatNum as an Object
    @Override
    public Object clone() {
        return new RatNum(this);
    }


    //Checks if the RatNums are equals
    public boolean equals(RatNum y){
        boolean eq = a == y.getNumerator() && b == y.getDenominator();
        return eq;
    }

    //Checks if the selected RatNum is less then the parameter RatNum
    public boolean lessThan(RatNum y){
        double a = this.toDouble();
        double b = y.toDouble();

        return (a < b);
    }

    //Adds the parameter RatNum to the selected RatNum
    public RatNum add(RatNum y){
        int xa = a * y.getDenominator();
        int ya = y.getNumerator() * b;
        a = xa + ya;
        b = b * y.getDenominator();

        return new RatNum(a, b);
    }

    //Subtracts the parameter RatNum to the selected RatNum
    public RatNum sub(RatNum y){
        int xa = a * y.getDenominator();
        int ya = y.getNumerator() * b;
        int xaya = xa - ya;
        int xbyb= b * y.getDenominator();

        return new RatNum(xaya, xbyb);
    }

    //Multiplies the two RatNums
    public RatNum mul(RatNum y){
        int numA = a * y.getNumerator();
        int numB = b * y.getDenominator();

        return new RatNum(numA, numB);
    }

    //Divides the selected RatNum with the parameter RatNum
    public RatNum div(RatNum y){
        int numA = a * y.getDenominator();
        int numB = b * y.getNumerator();

        return new RatNum(numA, numB);
    }

    //Returns the a value of the selected RatNum
    public int getNumerator(){
        return a;
    }

    //Returns the b value of the selected RatNum
    public int getDenominator(){
        return b;
    }

}


