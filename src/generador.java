/**
 * Created by calamarte on 01/02/2017.
 */
public class generador {
    public static void main(String[] args) {
        int[] a = {1,1,1,-2};

        Polynomial po = new Polynomial("-6x^4 + 20x - 8");
        System.out.println(po.toString());



    }
}
