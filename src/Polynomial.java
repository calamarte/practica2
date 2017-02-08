import java.util.Arrays;

public class Polynomial {

    private float [] polynomial = {0};

    // Constructor per defecte. Genera un polinomi zero
    public Polynomial() {

    }


    // Constructor a partir dels coeficients del polinomi en forma d'array
    public Polynomial(float[] cfs) {

        float[] polynomial = new float[cfs.length];

        for (int i = cfs.length - 1, x = 0; i >= 0; i--, x++) {

            polynomial[i] =  cfs[x];
        }
        this.polynomial = polynomial;
    }

    // Constructor a partir d'un string
    public Polynomial(String s) {
        StringBuilder sb = new StringBuilder();

        s = s.replaceAll(" ", "");

        // Separa el polinomio en monomios y se envian a monomyal
        for (int i = 0; i < s.length(); i++) {
            if((i == s.length()-1) || (s.charAt(i+1) == '+') || (s.charAt(i+1) == '-')){
                sb.append(s.charAt(i));
                monomyal(sb.toString());
                sb.setLength(0);

            }else{
                sb.append(s.charAt(i));
            }
        }

    }


    // Suma el polinomi amb un altre. No modifica el polinomi actual (this). Genera un de nou
    public Polynomial add(Polynomial p) {

        float[] p1 = this.polynomial;
        float[] p2 = p.polynomial;

         if (p1.length > p2.length){
             p2 = equalsLength(p1,p2);
         }else{
             p1 = equalsLength(p2,p1);
         }

         float[] result = new float[p1.length];

        for (int i = 0, x = result.length-1; i < result.length; i++,x--) {

            result[x] = p1[i] + p2[i];
        }

        Polynomial poly = new Polynomial(result);

        return  poly;
    }

    private  float[] equalsLength(float[] mayor,float[] menor){

        float[] result = new float[mayor.length];

        for (int i = 0; i < menor.length ; i++) {

            result[i] = menor[i];
        }
        return result;
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

    //Clasifica los monomios
    private void monomyal(String m){
        int[] mon = new int[2];
        StringBuilder sb = new StringBuilder();

        //Se filtran los monomios según sus características y se guarda su exponente y su valor en un array
        if (m.contains("x")){
            if (m.contains("^")){
                for (int i = 0; i < m.length() ; i++) {
                    if (m.charAt(i) == '^'){
                        sb.append(' ');

                    }else{
                        sb.append(m.charAt(i));

                    }
                }
                mon = noX(sb.toString());

            }else{
                for (int i = 0; i < m.length() ; i++) {
                    if (m.charAt(i) == 'x'){
                        if ((i == 0) || (m.charAt(i-1) == '+') || (m.charAt(i-1) == '-')){
                            sb.append('1');
                        }

                    }else{
                        sb.append(m.charAt(i));
                    }
                }
                m = sb.toString();

                mon[0] = Integer.parseInt(m);
                mon[1] = 1;
            }

        }else{

            mon[0] = Integer.parseInt(m);
            mon[1] = 0;
        }

        //Adapta el array
        if ((this.polynomial.length  < mon[1] +1)){
            float[] polynomial = new float[mon[1] +1];

            for (int i = 0; i <this.polynomial.length ; i++) {
                polynomial[i] = this.polynomial[i];
            }

            this.polynomial = polynomial;
        }


        //La información del monomyal se pasa al array principal donde estará el polinomio completo
        //Si es necesario se suman los polinomios para que no se pisen
        this.polynomial[mon[1]] += mon[0];
    }

    //Extrae la información de unos monomios concretos
    private int[] noX(String m){
        int[] mon = new int[2];
        StringBuilder sb = new StringBuilder();

        //Se editan los monomios
        for (int i = 0; i < m.length(); i++) {
            if (m.charAt(i) == 'x'){

                //-x^,x^,+x^
                if ((i == 0) || (m.charAt(i-1) == '+') || (m.charAt(i-1) == '-')) {
                    sb.append("1");
                }

            }else{
                sb.append(m.charAt(i));

            }
        }
        m = sb.toString();
        sb.setLength(0);

        //Divide el exponente del valor
        for (int i = 0; i < m.length() ; i++) {
            sb.append(m.charAt(i));

            if ((i != m.length()-1) && (m.charAt(i+1) == ' ')){
                mon[0] = Integer.parseInt(sb.toString());
                sb.setLength(0);

            }
            if(i == m.length()-1){
                mon[1] = Integer.parseInt(sb.toString().replaceAll(" ",""));
            }
        }

        return mon;
    }

    // Torna "true" si els polinomis són iguals. Això és un override d'un mètode de la classe Object
    @Override
    public boolean equals(Object o) {
        Polynomial polynomial = (Polynomial) o;
        return polynomial.toString().equals(this.toString());
    }

    // Torna la representació en forma de String del polinomi. Override d'un mètode de la classe Object
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        boolean b = true;

        //Crea un array ajustado a la información que se contiene
        for (int i = this.polynomial.length-1; i >= 0 ; i--) {
            if (this.polynomial[i] != 0) {
                float[] poly = new float[i + 1];

                for (int j = 0; j < poly.length; j++) {
                    poly[j] = this.polynomial[j];
                }
                this.polynomial = poly;
                break;
            }

        }

        //Comprueba si el array en su totalidad tiene valor 0
        for (int i = 0; i < this.polynomial.length ; i++) {

            if (polynomial[i] != 0){
                b = false;
            }
        }

        if (b)return "0";

        //Estructura el String
        for (int i = this.polynomial.length -1; i >= 0 ; i--) {

            if(polynomial[i] == 0){
                continue;
            }

            if ((i == this.polynomial.length -1) && (polynomial[i] < 0)){
                sb.append("-");

            }

            if (i !=  this.polynomial.length-1) {
                sb.append(sign(polynomial[i])+ monomyalAbsoluteToString(i));
            }else{
                sb.append(monomyalAbsoluteToString(i));
            }

        }

        return sb.toString();
    }

    //Define el signo en el String
    private static String sign(float numero){
        String s;
        if (numero < 0){
             s = " - ";
        }else{
            s = " + ";
        }
        return s;
    }

    //Devuelve el monomio en valor absoluto en un String
    private String monomyalAbsoluteToString (int position){
        StringBuilder sb = new StringBuilder();
        int numero = (int) this.polynomial[position];

        if (numero < 0){numero *= -1;}

        if (numero == 0){sb.append(0);}

        if (numero == 1){
            switch (position) {
                case 1: {
                    sb.append("x");
                    break;
                }
                case 0: {
                    sb.append(numero);
                    break;
                }
                default: {
                    sb.append("x^" + position);
                }
            }
        }

        if (numero > 1) {
            switch (position) {
                case 1: {
                    sb.append(numero + "x");
                    break;
                }
                case 0: {
                    sb.append(numero);
                    break;
                }
                default: {
                    sb.append(numero + "x^" + position);
                }
            }
        }


        return sb.toString();
    }
}
