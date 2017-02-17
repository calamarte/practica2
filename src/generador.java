import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by calamarte on 01/02/2017.
 */
public class generador {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        System.out.println ("Introduzca un Numero: ");
        int n = s.nextInt();

        for (int i = 1 ; i <= n ; i++) {
            if (n % i == 0) {
                System.out.println(i);
            }
        }
    }
}
