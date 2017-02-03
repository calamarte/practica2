import java.util.Arrays;

public class Polynomial {

    private int [] polynomial = {0};

    // Constructor per defecte. Genera un polinomi zero
    public Polynomial() {

    }

    // Constructor a partir dels coeficients del polinomi en forma d'array
    public Polynomial(float[] cfs) {
        int[] polynomial = new int[cfs.length];

        for (int i = cfs.length - 1, x = 0; i >= 0; i--, x++) {

            polynomial[i] =  (int) cfs[x];
        }
        this.polynomial = polynomial;
    }

    // Constructor a partir d'un string
    public Polynomial(String s) {

        StringBuilder sb = new StringBuilder();
        s = s.replaceAll(" ", "");

        for (int i = 0; i < s.length(); i++) {
            if((i == s.length()-1) || (s.charAt(i+1) == '+') || (s.charAt(i+1) == '-')){
                sb.append(s.charAt(i));
                monomio(sb.toString());
                sb.setLength(0);
            }else{
                sb.append(s.charAt(i));
            }
        }

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

    void monomio(String m){
        int[] mon = new int[2];
        StringBuilder sb = new StringBuilder();

        if (m.contains("x")){
            if (m.contains("^")){
                for (int i = 0; i < m.length() ; i++) {
                    if (m.charAt(i) == '^'){
                        sb.append(' ');

                    }else{
                        sb.append(m.charAt(i));

                    }

                    m = nox(sb.toString());

                }

            }
        }else{
            mon[0] = Integer.parseInt(m);
            mon[1] = 0;
        }
        System.out.println(m);
        System.out.println(Arrays.toString(mon));

    }

    private String nox(String m){
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m.length(); i++) {
            if (m.charAt(i) == 'x'){

                if ((i == 0) || (m.charAt(i-1) == '+') || (m.charAt(i-1) == '-')){
                    sb.append("1");

                }else{
                    sb.append("");
                }

            }else{
                sb.append(m.charAt(i));
            }
        }
        m = sb.toString();

        return m;
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
