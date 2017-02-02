import java.util.Arrays;
import java.util.Random;

public class Tools {

    public static void randomOrder(Object[] x){
        Random ran = new Random();
        Object[] tempArray = new Object[x.length];
        int i = 0;

        while(Arrays.asList(tempArray).contains(null)) {
            int randomIndex = ran.nextInt(x.length);
            if (tempArray[randomIndex] == null) {
                tempArray[randomIndex] = x[i];
                i++;
            }
        }
        System.arraycopy(tempArray, 0, x, 0, x.length);
    }
}
