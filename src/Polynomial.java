import java.util.Arrays;

public class Polynomial {

    private float[] polynomial = {0};

    // Constructor per defecte. Genera un polinomi zero
    public Polynomial() {

    }

    // Constructor a partir dels coeficients del polinomi en forma d'array
    public Polynomial(float[] cfs) {
        float[] polynomial = new float[cfs.length];

        for (int i = cfs.length -1,x = 0; i >= 0 ; i--,x++) {

            polynomial[i] = cfs[x];
        }
        this.polynomial = polynomial;
    }

    // Constructor a partir d'un string
    public Polynomial(String s) {
        s = s.replaceAll(" ","");

        for (int i = 0; i <s.length() ; i++) {
            
        }

        System.out.println(s);



    }

    // Suma el polinomi amb un altre. No modifica el polinomi actual (this). Genera un de nou
    public Polynomial add(Polynomial p) {
        return null;
    }

    // Multiplica el polinomi amb un altre. No modifica el polinomi actual (this). Genera un de nou
    public Polynomial mult(Polynomial p2) {
        return null;
    }

    // Divideix el polinomi amb un altre. No modifica el polinomi actual (this). Genera un de nou
    // Torna el quocient i també el residu (ambdós polinomis)
    public Polynomial[] div(Polynomial p2) {
       return null;
    }

    // Troba les arrels del polinomi, ordenades de menor a major
    public float[] roots() {
        return null;
    }

    // Torna "true" si els polinomis són iguals. Això és un override d'un mètode de la classe Object
    @Override
    public boolean equals(Object o) {
        return false;
    }

    // Torna la representació en forma de String del polinomi. Override d'un mètode de la classe Object
    @Override
    public String toString() {
        return "";
    }
}
