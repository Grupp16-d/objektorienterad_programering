import java.lang.String;

public class RatNum {
    public int a,b;

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

    //Steg 3
    public String toString(RatNum x){
        a = x.getNumerator();
        b = x.getDenominator();
        String strRatNum = a + "/" + b;
        return strRatNum;
    }

    public double toDouble(RatNum x){
        a = x.getNumerator();
        b = x.getDenominator();
        double ab = a/b;
        double rounded = Math.round(ab *100) / 100;
        return rounded;
    }

    public static RatNum parse(String s){
        String[] str = s.split("/");
        if(str.length == 2){
            str.matches("-?\\d+(\\.\\d+)?");
        }
    }

}
