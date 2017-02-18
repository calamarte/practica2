import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by calamarte on 01/02/2017.
 */
public class generador {
    public static void main(String[] args) {

        Polynomial tal = new Polynomial("4x^5 + 4");
        System.out.println(Arrays.toString(tal.roots()));
    }
}
