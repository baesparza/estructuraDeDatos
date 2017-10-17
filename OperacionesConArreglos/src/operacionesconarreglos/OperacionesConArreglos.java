package operacionesconarreglos;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author baesparza
 */
public class OperacionesConArreglos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Dialog dialog = new Dialog();
        Metodos metodos = new Metodos();
                
        int opcion, opcion2;

        int[] arreglo = new int[Integer.parseInt(dialog.inputMessage("Ingrese el tamaño del arreglo: "))];
        
        do {
            opcion = Integer.parseInt(dialog.inputMessage(metodos.opciones()));
            switch (opcion) {
                case(1):
                    arreglo = metodos.ingresarArreglo(arreglo);
                    break;
                case(2):
                    metodos.presentarArreglo(arreglo);
                    break;
                case(3):
                    arreglo = metodos.ordenarArreglo(arreglo);
                    metodos.presentarArreglo(arreglo);
                    break;
                case(4):
                    arreglo = metodos.insertarEnArreglo(arreglo);
                    metodos.presentarArreglo(arreglo);
                    break;
                case(5):
                    arreglo = metodos.ordenarArreglo(arreglo);
                    do {
                        opcion2 = Integer.parseInt(dialog.inputMessage(metodos.opcionesBusqueda()));
                    } while (opcion2 < 0 || opcion2 > 2);
                    switch (opcion2) {
                        case(1):
                            dialog.showMessage("El numeo esta en la posicion "+
                            metodos.busquedaBinaria(
                                    arreglo,
                                    0, arreglo.length,
                                    Integer.parseInt(dialog.inputMessage("Ingrese el numero a buscar: "))
                                    ));
                            break;
                        case(2):
                            dialog.showMessage(String.format(
                                    "El numero se encuentra en la posicion %d",
                                    metodos.busquedaLineal(arreglo,
                                            Integer.parseInt(dialog.inputMessage(
                                                    "Ingrese un numero a buscar: ")))));
                            break;
                        default:
                            break;
                    }
                    metodos.presentarArreglo(arreglo);
                    break;
                case(6):
                    arreglo = metodos.eliminarElemento(arreglo);
                    metodos.presentarArreglo(arreglo);
                    break;
            }
            
        }while(opcion != 0);
        
        dialog.showMessage("Programa termino correctamente");
    }
    
    public static class Metodos {
        
        private Dialog dialog = new Dialog();
        
        public Metodos() {
            
        }
        
        public String opciones() {
            return String.format(
                    "INGRESE EL NUMERO DE LA OPCION\n"
                            + "1. Ingresar Arreglo\n"
                            + "2. Presentar Arreglo\n"
                            + "3. Ordenar Arreglo\n"
                            + "4. Ingresar Elemento\n"
                            + "5. Buscar Elemento y Ordenar Arreglo\n"
                            + "6. Eliminar Elementeo\n"
                            + "0. Salir...\n"
                            + "-> ");
        }
        
        public String opcionesBusqueda() {
            return String.format(
                    "INGRESE EL NUMERO DE LA OPCION\n"
                            + "1. Busqueda Binaria\n"
                            + "2. Busqueda Secuencial\n"
                            + "0. Salir...\n"
                            + "-> ");
        }
        
        public int[] ingresarArreglo(int[] arreglo) {
            for (int i = 0; i < arreglo.length; i++) {
                arreglo[i] = Integer.parseInt(this.dialog.inputMessage(String.format(
                        "Ingrese un numero para la posision %d: ", i)));
            }
            
            return arreglo;
        }
        
        public void presentarArreglo(int[] arreglo) {
            this.dialog.showMessage(Arrays.toString(arreglo));
        }
        
        public int[] ordenarArreglo(int[] arreglo) {
            
            for (int i = 0; i < arreglo.length; i++) {
                for (int j = 0; j < arreglo.length - i - 1; j++) {
                    if (arreglo[j] > arreglo[j + 1]) {
                        int temp = arreglo[j];
                        arreglo[j] = arreglo[j + 1];
                        arreglo[j + 1] = temp;
                    }
                }
            }
            
            return arreglo;
        }
        
        public int[] insertarEnArreglo (int[] arreglo) {
            int index;
            do {
                index = Integer.parseInt(this.dialog.inputMessage("Ingrese un indice valido: "));
            } while (index < 0 || index >= arreglo.length);
            for (int i = arreglo.length - 1; i > index; i--) {
                arreglo[i] = arreglo[i - 1];
            }
            arreglo[index] = Integer.parseInt(this.dialog.inputMessage("Ingrese el nuevo elemento: "));
            return arreglo;
        }
        
        public int busquedaLineal(int[] arreglo, int num) {
            for (int i = 0; i < arreglo.length; i++) {
                if (num == arreglo[i]) {
                    return i;
                }
            }
            return -1;
        }
        
        public int busquedaBinaria(int[] arreglo, int start, int end, int num) {
            int mid = (end + start) / 2;
            if (end - start <= 1) {
                if (arreglo[end] == num) {
                    return end;
                } 
                if (arreglo[start] == num) {
                    return start;
                }
                return -1;
            }
            
            if (num <= arreglo[mid]) {
                return this.busquedaBinaria(arreglo, start, mid, num);
            }
            return this.busquedaBinaria(arreglo, mid, end, num);
        }
        
        public int[] eliminarElemento(int[] arreglo) {
            
            int[] newArreglo = new int[arreglo.length - 1];
            int index;
            
            do {
                index = Integer.parseInt(
                        this.dialog.inputMessage("Ingrese un indice valido del elemento a eliminar: "));
            } while (index < 0 || index >= arreglo.length);
            
            for (int i = 0, j = 0; j < newArreglo.length; i++, j++) {
                if (i == index) {
                    i++;
                }
                newArreglo[j] = arreglo[i];
            }
            
            return newArreglo;
        }
    }
    
    public static class Dialog {
        private Scanner input = new Scanner(System.in);
        
        public String inputMessage(String message) {
            System.out.print(message);
            return this.input.nextLine();
        }
        
        public void showMessage(String message) {
            System.out.println(message);
        }
        
    }
    
}
