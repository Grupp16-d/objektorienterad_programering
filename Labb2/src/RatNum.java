public class RatNum {
   // public static int sgd(int m, int n){
      public static int sgd(int m, int n){
        if(m==0 || n==0) {
            throw new IllegalArgumentException();
        }

        int absM = Math.abs(m);
        int absN = Math.abs(n);

        int r = 1;
        while(r != 0){
            r = absM / absN;
            absM = absN;
            absN = r;
        }

        m = m / absN;
        n = n / absN;
        /*--------------------------------- FIXA RETURN -----------------------*/
        return m, n;
    }
    //Steg 2

    //steg 3
    //En metod toString som returnerar det aktuella talet som en text
    private toString(){

    }

    //beräkna ett närmevärde på n/m
    private double toDouble(double m, double n){
        double x;
        x = m/n;
        return x;
    }

    //

}

return new MyResult(number1, number2);

    Dividera m med n och låt r vara resten vid divisionen.
    Om r = 0 så är beräkningen klar och resultatet finns i n.
    Sätt annars m till n och n till r och gå tillbaka till steg 1.