import java.util.Scanner;
import java.io.File;

public class Lab1 {
    public static void sort(int[] a) {
        int temp;
        for (int i = a.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (a[j] - a[j+1] > 0){
                    temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(new File(args[0]));
        int[] x = new int[s.nextInt()];
        for (int i = 0; i < x.length; i++){
            x[i] = s.nextInt();
        }

        sort(x);
        System.out.println(x[0]);
    }
}