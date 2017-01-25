public class RatNum {
    public int a,b;

    //största gemensama delare metod
    static int sgd(int m, int n) {
        if (m == 0 || n == 0) {
            throw new IllegalArgumentException();
        }
        m = Math.abs(m);
        n = Math.abs(n);

        while (n != 0) {
            int c = n;
            n = m % n;
            m = c;
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
        if(b == 0){
            throw new NumberFormatException("Denominator = 0");
        }
        if(a == 0){
            this.a = a;
            this.b = 1;
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

}

        //steg 3
        //En metod toString som returnerar det aktuella talet som en text
  /*  private toString() {

        }

        //beräkna ett närmevärde på n/m

    private double toDouble(double m, double n) {
        double x;
        x = m / n;
        return x;
    }

    //


}*/