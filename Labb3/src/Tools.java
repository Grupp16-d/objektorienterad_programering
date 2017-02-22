import java.util.Random;

import static java.util.Arrays.*;

//Randomise an array of objects
public class Tools {
    //A new temp array that randomise objects inside an array
    public static void randomOrder(Object[] x){
        Random ran = new Random();
        Object[] tempArray = new Object[x.length];
        int i = 0;

        while(asList(tempArray).contains(null)) {
            int randomIndex = ran.nextInt(x.length);
            if (tempArray[randomIndex] == null) {
                tempArray[randomIndex] = x[i];
                i++;
            }
        }
        System.arraycopy(tempArray, 0, x, 0, x.length);
    }
}
