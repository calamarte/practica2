/**
 * Created by calamarte on 01/02/2017.
 */
public class generador {
    public static void main(String[] args) {
        int[] a = {1,1,1,-2};

        Polynomial po = new Polynomial("2x^2 + x + 2x");
        Polynomial tal = new Polynomial("2x");
        System.out.println(po.mult(tal).toString());



    }
}
