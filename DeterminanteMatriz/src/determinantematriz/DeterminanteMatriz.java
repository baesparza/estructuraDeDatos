package determinantematriz;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author baesparza
 */
public class DeterminanteMatriz {

    public static void main(String[] args) {
        
        int[][] matriz = ingresarMatriz();
        
        System.out.println(calcularDeterminante(matriz));
        presentarMatriz(matriz);
    }
    
    public static int[][] ingresarMatriz() {
        
        Scanner input = new Scanner(System.in);
        
        int size = 0;
        int[][] matriz;
        
        System.out.print("Ingrese el tamaño de la matriz: ");
        size = input.nextInt();
        
        matriz = new int[size][size];
        
        for(int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j ++) {
                System.out.print("Ingrese un valor: ");
                matriz[i][j] = input.nextInt();
            }
        }
        
        return matriz;
    }
    
    public static void presentarMatriz(int[][] matriz) {
        for (int[] row: matriz) {
            Arrays.toString(row);
        }
    }
    
    public static int calcularDeterminante(int[][] matriz) {
        return calcular(matriz, 0, 1) + calcular(matriz, matriz.length - 1, -1);
    }
    
    
    public static int calcular(int[][] matriz, int start, int iterator) {
        int result = 0;
        for (int i = 0; i < matriz.length; i++) {
            int temp = 1;
            for (int loop = 0, j = i, k = 0 , l = start;  loop < matriz.length; loop++, j++, k++, l+=iterator) {
                if (j == matriz.length) {
                    j = 0; 
                } 
                System.out.println(String.format("i = %d; j = %d; l = %d; loop = %d value = %d", i,j,l, loop, matriz[j][l]));
                temp *= matriz[j][l];
            }
            System.out.println("temp: " + temp);
            result += temp;
        }
        return result * iterator;
    }
}
