public class RandomTest {
    public static void main(String[] arg) {
        Integer[] a = new Integer[30];
        for (int i = 0; i < a.length; i++)
            a[i] = new Integer(i+1);
        Tools.randomOrder(a);
        System.out.println("Nu skall talen 1 till " + a.length + " skrivas ut i slumpmĆ¤ssig ordning");
        for (int i = 0; i < a.length; i++)
            System.out.print(a[i] + " ");
        System.out.println();
        System.out.println("Nu skall talen 1 till " + a.length + " skrivas ut i en annan slumpmĆ¤ssig ordning");
        Tools.randomOrder(a);
        for (int i = 0; i < a.length; i++)
            System.out.print(a[i] + " ");
        System.out.println();
    }
}