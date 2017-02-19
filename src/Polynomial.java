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

        // Separa el polinomio en monomios y se envian a monomial
        for (int i = 0; i < s.length(); i++) {
            if((i == s.length()-1) || (s.charAt(i+1) == '+') || (s.charAt(i+1) == '-')){
                sb.append(s.charAt(i));
                monomial(sb.toString());
                sb.setLength(0);

            }else{
                sb.append(s.charAt(i));
            }
        }

        this.polynomial = ArraysCut(this.polynomial);

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

        //suma
        for (int i = 0; i < p1.length; i++) {

            p1[i] += p2[i];
        }

        Polynomial poly = new Polynomial(ArraysRevers(p1));

        return  poly;
    }

    //Cambia la longitud de un array tomando como referencia otro array
    private  float[] equalsLength(float[] mayor,float[] menor){

        float[] result = new float[mayor.length];

        for (int i = 0; i < menor.length ; i++) {

            result[i] = menor[i];
        }
        return result;
    }

    // Multiplica el polinomi amb un altre. No modifica el polinomi actual (this). Genera un de nou
    public Polynomial mult(Polynomial p2) {
        float[] p1;
        float[] p3;
        float[] result = {0};

        if (this.polynomial.length > p2.polynomial.length){
            p1 = this.polynomial;
            p3 = p2.polynomial;
        }else {
            p3 = this.polynomial;
            p1 = p2.polynomial;
        }


        for (int i = 0; i < p1.length ; i++) {
            for (int j = 0; j < p3.length ; j++) {
                int[] mon = new int[2];
                mon[0] = (int) (p1[i] * p3[j]);
                mon[1] = j + i;
                result = ArraysAdaptative(result,mon);
                result[mon[1]] += mon[0];
            }
        }


        Polynomial poly = new Polynomial(ArraysRevers(result));

        return poly;
    }

    private float[] ArraysRevers(float[] polynomial){
        float[] revers = new float[polynomial.length];

        for (int i = 0,x = revers.length-1; i < revers.length ; i++,x--) {
            revers[x] = polynomial[i];
        }

        return revers;
    }

    // Divideix el polinomi amb un altre. No modifica el polinomi actual (this). Genera un de nou
    // Torna el quocient i també el residu (ambdós polinomis)
    public Polynomial[] div(Polynomial p2) {
        float[] dividendo = this.polynomial;
        float[] divisor = p2.polynomial;
        float[] cociente = {0};
        float expodivisor = exponenteMax(divisor);
        float expodividendo;

        //La división en si
        for (int i = dividendo.length-1; i >= 0 ; i--) {

            expodividendo = exponenteMax(dividendo);

            if (expodividendo < expodivisor){
                break;
            }

            float[] resto;

            int[] mondividendo = {(int)dividendo[i],i};
            int[] mondivisor = {(int) divisor[divisor.length-1],divisor.length-1};
            int[] moncociente = mondiv(mondividendo,mondivisor);


            cociente = ArraysAdaptative(cociente, moncociente);
            cociente[moncociente[1]] = moncociente[0];
            resto = restoCreator(divisor,moncociente);

            Polynomial Dividendo = new Polynomial(ArraysRevers(dividendo));
            Polynomial Resto = new Polynomial(ArraysRevers(resto));

            dividendo = Dividendo.add(Resto).polynomial;


        }

        Polynomial Resto = new Polynomial(ArraysRevers(dividendo));
        Polynomial Cociente = new Polynomial(ArraysRevers(cociente));
        Polynomial[] res =  {Cociente,Resto};

       return res;
    }

    //Comprueba cual es el exponete mayor con valor distinto de 0
    private float exponenteMax(float[] polynomial){

        float exponente = 0;

        for (int i = polynomial.length-1; i >= 0; i--) {
            if (polynomial[i] != 0){
                exponente = i;
                break;
            }
        }

        return exponente;
    }

    //Crea el resto
    private float[] restoCreator(float[] divisor, int[] moncociente){
        float[] resto = {0};

        for (int i = 0; i < divisor.length ; i++) {
            int[] aux = new int[2];

            aux[0] = ((int) divisor[i] * moncociente[0]) * (-1);
            aux[1] = i + moncociente[1];
            resto = ArraysAdaptative(resto,aux);
            resto[aux[1]] = aux[0];

        }

        return resto;

    }

    //divide 2 monomios
    private int[] mondiv(int[] mondividendo,int[] mondivisor){
        int[] cociente = new int[2];

        cociente[0] = mondividendo[0] / mondivisor[0];
        cociente[1] = mondividendo[1] - mondivisor[1];

         return cociente;
    }

    // Troba les arrels del polinomi, ordenades de menor a major
    public float[] roots() {
        float[] polynomial = this.polynomial;
        int ceros = 0;

        //Asegura que el polynomio simepre esté en positivo
        if (polynomial[polynomial.length-1] < 0){
            for (int i = 0; i < polynomial.length ; i++) {
                polynomial[i] *= -1;
            }
        }

        //Cuenta los ceros que hay dentro de un Array
        for (int i = 0; i < polynomial.length; i++) {
            if (polynomial[i] == 0 ) ceros++;
        }

        //Ecuaciones simples
        if (polynomial[0] != 0 && ceros == polynomial.length-2 && polynomial.length-1 != 2) {
            return basicRoots(polynomial);
        }


        //Ecuaciones de segundo grado
        if (polynomial.length == 3) return cuadrada(polynomial);


        //Ecuaciones bicuadradas
        if (polynomial.length == 5 && polynomial[3] == 0 && polynomial[1] == 0 ) {
            return bicuadrada(polynomial);

        }

        //Por ruffini
        if (polynomial.length >= 4)  return ruffini(polynomial);


        return null;
    }

    private float[] basicRoots(float[] polynomial){

        //Es par?
        if ((polynomial.length -1) % 2 == 0){
            if (polynomial[0] > 0){//raiz negativa
                return null;
            }else{
                float[] dos = new float[2];
                dos[0] = (float) (-1 * (Math.pow(polynomial[0]/polynomial[polynomial.length-1] * -1, (1.0/(polynomial.length-1)))));
                dos[1] = (float) (Math.pow(polynomial[0]/polynomial[polynomial.length-1] * -1, (1.0/(polynomial.length-1))));
                Arrays.sort(dos);
                return dos;
            }
        }else {
            float[] uno = new float[1];
            if (polynomial[0] < 0) {
                polynomial[0] *= -1;
                uno[0] = (float) (Math.pow(polynomial[0] / polynomial[polynomial.length - 1], (1.0 / (polynomial.length - 1))));
                return uno;
            }else {
                uno[0] = (float) (-1 * (Math.pow(polynomial[0] / polynomial[polynomial.length - 1], (1.0 / (polynomial.length - 1)))));
            }   return uno;
        }
    }

    //Realiza ecuacines mayores de segundo grado siempre que no sean
    //bicuadradas ni simples.
    private float[] ruffini(float[] polynomial){
        float[] divisores = divisores(polynomial[0]);
        float pivote;
        float aux = 0;
        float[] raices = {0};
        int tamañoraices = 0;

        //Todas las posibles raices
        for (int i = 0; i < divisores.length ; i++) {
            float[] resultado = new float[polynomial.length];
            pivote = divisores[i];

            //ruffini
            for (int j = polynomial.length-1; j >= 0; j--) {
                resultado[j] = polynomial[j] + aux;
                aux = pivote * resultado[j];
            }

            //guardar raiz
            if (resultado[0] == 0){
                int[] raiz = { (int) pivote,tamañoraices};
                raices = ArraysAdaptative(raices,raiz);
                raices[raiz[1]] = raiz[0];
                polynomial = resultado;
                tamañoraices++;
            }

            if (i == divisores.length -1 && raices[0] == 0)return null;

            aux = 0;
        }

        Arrays.sort(raices);

        return raices;
    }

    //Busca los divisores de un número
    private float[] divisores(float independiente) {
        float[] divisorespos = {0};
        int[] aux = new int[2];

        for (int i = 1, x = 0; i <= independiente ; i++) {
            if (independiente % i == 0) {
                aux[0] =  i;
                aux[1] = x;
                divisorespos = ArraysAdaptative(divisorespos,aux);
                divisorespos[aux[1]] = aux[0];
                x++;
            }
        }

        float[] alldivisores = new float[divisorespos.length * 2];

        for (int i = 0,x = 0; i < alldivisores.length ; i++,x++) {
            if (i > divisorespos.length -1){
                if (x > divisorespos.length-1){
                    x = 0;
                }
                alldivisores[i] = divisorespos[x] * -1;
                continue;
            }
            alldivisores[i] = divisorespos[x];
        }
        return alldivisores;
    }

    //Realiza las ecuciones bicuadradas
    private float[] bicuadrada(float[] polynomial) {
        float[] bicuadrada = {polynomial[0], polynomial[2], polynomial[4]};
        float[] raices = cuadrada(bicuadrada);

        if (raices == null) return null;

        if (raices.length == 1) {
            if (raices[0] < 0) {
                return null;

            } else {

                float[] dos = new float[2];

                dos[0] = (float) Math.sqrt(raices[0]);
                dos[1] = -1 * (float) Math.sqrt(raices[0]);
                Arrays.sort(dos);
                return dos;
            }
        } else {

            if (raices[0] < 0 && raices[1] < 0)return null;

            if (raices[0] < 0) {
                float[] dos = new float[2];

                dos[0] = (float) Math.sqrt(raices[1]);
                dos[1] = -1 * (float) Math.sqrt(raices[1]);
                Arrays.sort(dos);
                return dos;

            }

            float[] cuatro = new float[4];

            for (int i = 0, j = 0; i < raices.length; i++, j += 2) {

                cuatro[j] = (float) Math.sqrt(raices[i]);
                cuatro[j + 1] = -1 * (float) Math.sqrt(raices[i]);

            }
            Arrays.sort(cuatro);
            return cuatro;
        }
    }

    //Realiza ecuaciones de segundo grado
    private float[] cuadrada(float[] polynomial){

        //Poner barreras contra la raiz negativa

        float raizCuadrada = polynomial[1] * polynomial[1] - 4 * polynomial[2] * polynomial[0];

        if (raizCuadrada < 0)return null;

        raizCuadrada = (float) Math.sqrt(raizCuadrada);

        float[] raices = new float[2];

        raices[0] = ((-1) * polynomial[1] + raizCuadrada) / (polynomial[2] * 2);
        raices[1] = ((-1) * polynomial[1] - raizCuadrada) / (polynomial[2] * 2);

        if (raices[0] == raices[1]) {
            float[] igual = {raices[0]};
            return igual;
        }

        Arrays.sort(raices);

        return raices;
    }

    //Clasifica los monomios
    private void monomial(String m){
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
        this.polynomial = ArraysAdaptative(this.polynomial,mon);



        //La información del monomial se pasa al array principal donde estará el polinomio completo
        //Si es necesario se suman los polinomios para que no se pisen
        this.polynomial[mon[1]] += mon[0];
    }

    //Adapta el array según las necesidades del monomio
    private float[] ArraysAdaptative(float[] polynomial,int[] monomyal){
        float[] p = polynomial;

        if ((p.length  < monomyal[1] +1)){
            float[] poly = new float[monomyal[1] +1];

            for (int i = 0; i <p.length ; i++) {
                poly[i] = p[i];
            }

            p = poly;
        }

        return p;
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

    //Crea un array ajustado a la información que se contiene
    private float[] ArraysCut(float[] polynomial){
        float[] p = polynomial;
        boolean b = true;


        //Comprueba si el array en su totalidad tiene valor 0
        for (int i = 0; i < p.length ; i++) {

            if (p[i] != 0){
                b = false;
            }
        }

        if (b){
            float[] vacio = {0};
            return vacio;
        }

        for (int i = p.length-1; i >= 0 ; i--) {
            if (p[i] != 0) {
                float[] poly = new float[i + 1];

                for (int j = 0; j < poly.length; j++) {
                    poly[j] = p[j];
                }
                p = poly;
                break;
            }

        }

        return p;

    }

    // Torna la representació en forma de String del polinomi. Override d'un mètode de la classe Object
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        boolean b = true;

        this.polynomial = ArraysCut(this.polynomial);

        if (this.polynomial.length == 1 && this.polynomial[0] == 0) return "0";

        //Estructura el String
        for (int i = this.polynomial.length -1; i >= 0 ; i--) {

            if(polynomial[i] == 0){
                continue;
            }

            if ((i == this.polynomial.length -1) && (polynomial[i] < 0)){
                sb.append("-");

            }

            if (i !=  this.polynomial.length-1) {
                sb.append(sign(polynomial[i])+ monomialAbsoluteToString(i));
            }else{
                sb.append(monomialAbsoluteToString(i));
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
    private String monomialAbsoluteToString(int position){
        StringBuilder sb = new StringBuilder();
        int numero = (int) this.polynomial[position];

        if (numero < 0)numero *= -1;

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
