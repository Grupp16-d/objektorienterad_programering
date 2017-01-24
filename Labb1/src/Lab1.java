import java.nio.file.Files;
import java.nio.file.Paths;

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
        String file = new String(Files.readAllBytes(Paths.get(args[0])));
        String[] str = file.split("\\s+");

        int[] x = new int[str.length];

        for(int i=0; i < str.length; i++){
            x[i] = Integer.parseInt(str[i]);
        }

        String osorterad = "Osorterad lista: ";
        for (int i=0; i < str.length; i++){
            osorterad = osorterad + " " + x[i];
        }
        System.out.println(osorterad);

        sort(x);

        String resultat = "Sorterad lista: ";
        for (int i=0; i < x.length; i++){
            resultat = resultat + " " + x[i];
        }
        System.out.println(resultat);
    }
}