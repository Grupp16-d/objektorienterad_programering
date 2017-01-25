public class RatNum {
    //största gemensama delare metod
   static int sgd(int m, int n)
   {
       while(m!=0 && n!=0) // until either one of them is 0
       {
           int c = m;
           n = m%n;
           m = c;
       }
       return m+n; // either one is 0, so return the non-zero value

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