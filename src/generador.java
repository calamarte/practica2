/**
 * Created by calamarte on 01/02/2017.
 */
public class generador {
    public static void main(String[] args) {
        int[] a = {1,1,1,-2};

        Polynomial po = new Polynomial("-x^2");
        System.out.println(po.monomyalAbsoluteToString(2));


    }
}
