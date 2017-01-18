import java.util.Scanner;
import java.io.File;
import java.util.*;

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
        Scanner s = new Scanner(System.in);
        System.out.println("hej");
        List<Integer> x = new ArrayList<Integer>();

        /* ---------------------------------------------------------------------------------------------------------- */

        while (s.hasNextInt()) {
            x.add(s.nextInt());
        }
        System.out.println("hej");

        int[] xArray = new int[x.size()];
        for (int i=0; i < xArray.length; i++)
        {
            xArray[i] = x.get(i).intValue();
        }
        System.out.println("hej");
        sort(xArray);

        for (int i=0; i < xArray.length; i++){
            System.out.println(xArray[i]);
        }
    }
}