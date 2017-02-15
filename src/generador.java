/**
 * Created by calamarte on 01/02/2017.
 */
public class generador {
    public static void main(String[] args) {

        Polynomial po = new Polynomial("2x^2 + 2x");
        Polynomial tal = new Polynomial("2x");
        System.out.println(po.div(tal)[0].toString());
        System.out.println(po.div(tal)[1].toString());



    }
}
