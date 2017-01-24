import java.nio.file.Files;
import java.nio.file.Paths;

public class Lab1 {
    //En bubble sortmetod
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
        //Läser in filen och sparar som en sträng med readAllBytes
        //Strängen delas sedan upp till en array vid varje sektion av "whitespaces"
        String file = new String(Files.readAllBytes(Paths.get(args[0])));
        String[] str = file.split("\\s+");


        int[] x = new int[str.length];

        //Sparar arrayen av strängar som ints
        for(int i=0; i < str.length; i++){
            x[i] = Integer.parseInt(str[i]);
        }

        //Skriver ut den osorterade arrayen
        String osorterad = "Osorterad array: ";
        for (int i=0; i < x.length; i++){
            osorterad = osorterad + " " + x[i];
        }
        System.out.println(osorterad);

        //Sorterar arrayen med ints
        sort(x);

        //Skriver ut den sorterade arrayen
        String resultat = "Sorterad array: ";
        for (int i=0; i < x.length; i++){
            resultat = resultat + " " + x[i];
        }
        System.out.println(resultat);
    }
}