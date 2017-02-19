import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by calamarte on 01/02/2017.
 */
public class generador {
    public static void main(String[] args) {

        Polynomial tal = new Polynomial("4x^3 -x^2 +20x + 100");
        Polynomial po = new Polynomial("125x^13 - 125");
        System.out.println(Arrays.toString(tal.roots()));
        System.out.println(Arrays.toString(po.roots()));
    }
}
