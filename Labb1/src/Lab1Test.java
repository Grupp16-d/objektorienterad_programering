import java.util.Arrays;

class Lab1Test {
    public static void main(String[] args) {
        for (int len = 0; len < 20; len++) {
            int[] a = new int[len];
            int[] acopy1 = new int[len];
            int[] acopy2 = new int[len];
            for (int repeat = 0; repeat < 100; repeat++) {
                for (int i = 0; i < len; i++) {
                    int v = java.util.concurrent.ThreadLocalRandom.current().nextInt(0, len);
                    a[i] = acopy1[i] = acopy2[i] = v;
                }
                Lab1.sort(acopy1);
                Arrays.sort(acopy2);
                if (!Arrays.equals(acopy1, acopy2)) {
                    System.out.println("Fel i Lab1.sort hittad:");
                    System.out.println("Ursprunglig array:" + Arrays.toString(a));
                    System.out.println("Lab1.sort ger:" + Arrays.toString(acopy1));
                    System.out.println("Korrekt sortering:" + Arrays.toString(acopy2));
                    return;
                }
            }
        }
        System.out.println(20 * 100 + " tester gjorda utan att fel hittats i Lab1.sort");
    }
}