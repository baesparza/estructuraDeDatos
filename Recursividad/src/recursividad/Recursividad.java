package recursividad;

/**
 *
 * @author baesparza
 */
public class Recursividad {

    public static void main(String[] args) {
        System.out.println("Factoria1: " + fact1(5));
        System.out.println("Factoria2: " + fact2(5));
        System.out.println("Fibonaci: " + fib(5));
        System.out.println("Binary: " + transformBinary(9));
        
        int[] arreglo = new int[5];
        System.out.println("Busqueda binaria: " + BusquedaBinaria(arreglo, 0, arreglo.length, 2));
    }
    
    public static int fact1(int num) {
        if (num <= 1) return 1;
        return num * fact1(num-1);
    }
    
    public static int fact2(int num) {
        int result = 1;
        for (int i = num; i > 1; i--) result *= i;
        return result;
    }
    
    public static int fib(int num) {
        if (num <= 1) return 1;
        return fib(num-1) + fib(num-2);
    }
    
    public static String transformBinary(int num) {
        if (num/2 == 0) return "1";
        return transformBinary(num/2)+num%2;
    }
    
    public static String BusquedaBinaria(int[] arreglo, int start, int end, int num) {
        int mid = (end + start) / 2; // medio
        /* changes algorithim */
        if (end < start) return "El numero no se encuentra";
        if (arreglo[mid] == num) return String.format("El numero esta en la posicion %d", mid);
        /* algorithim can simplify
        if (end - start <= 1) { // verificar si numero se puede dividir
            if (arreglo[end] == num) return String.format("El numero esta en la posicion %d", end);
            if (arreglo[start] == num) return String.format("El numero esta en la posicion %d", start);
            return String.format("El numero no se encuentra en el arreglo"); // no se encuentra
        }*/
        // en que parte del arreglo se encuentra
        if (num <= arreglo[mid]) return BusquedaBinaria(arreglo, start, mid, num); // el umero esta antes de la mitad
        return BusquedaBinaria(arreglo, mid, end, num); // el numero esta despues de la mitad
    }
}
