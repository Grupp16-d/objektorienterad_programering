/**
 * Created by Max on 18/01/17.
 */
public class Lab1 {
    public static void sort(int[] a) {
        for (int i = a.length - 1; i > 0; i--) { 
            for (int j = 0; j < i; j++) { 
                if (a[j] - a[j+1] > 0) { 
                    int temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                } 
            }
        }
    }

    public static void main(String[] args) throws Exception {
        int[] x = {6,2,1,10,7};
        sort(x);
        System.out.println(x);
    }
}

