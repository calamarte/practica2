/**
 * Created by calamarte on 01/02/2017.
 */
public class generador {
    public static void main(String[] args) {

        Polynomial po = new Polynomial("2x^-1500 - 4x^3 + 2x");
        Polynomial tal = new Polynomial("x + 2");

        System.out.println(po.div(tal)[0].toString());
        System.out.println(po.div(tal)[1].toString());



    }
}
