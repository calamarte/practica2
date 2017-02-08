/**
 * Created by calamarte on 01/02/2017.
 */
public class generador {
    public static void main(String[] args) {
        int[] a = {1,1,1,-2};

        Polynomial po = new Polynomial("3x^2 + 2");
        Polynomial tal = new Polynomial("x^2 + 5x -5");
        System.out.println(po.add(tal).toString());



    }
}
