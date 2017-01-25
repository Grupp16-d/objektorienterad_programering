public class RatNum {
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
}
        //Steg 2

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