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

        // gen new array with the size the user whants
        int[] arreglo = new int[Integer.parseInt(dialog.inputMessage("Ingrese el tamaño del arreglo: "))];
        
        do { // keep program asking the user what to do
            do { // check if valid option
                opcion = Integer.parseInt(dialog.inputMessage(metodos.opciones()));
            } while (opcion < 0 || opcion > 6);
            switch (opcion) {
                case(1): // input values for the array
                    arreglo = metodos.ingresarArreglo(arreglo);
                    break;
                case(2): // present array value/items
                    metodos.presentarArreglo(arreglo);
                    break;
                case(3): // sort and print array                    
                    arreglo = metodos.ordenarArreglo(arreglo);
                    metodos.presentarArreglo(arreglo);
                    break;
                case(4): // insert element and print array
                    arreglo = metodos.insertarEnArreglo(arreglo);
                    metodos.presentarArreglo(arreglo);
                    break;
                case(5): // sort array and ask the type of search user whants
                    arreglo = metodos.ordenarArreglo(arreglo);
                    do { // check if valid option
                        opcion2 = Integer.parseInt(dialog.inputMessage(metodos.opcionesBusqueda()));
                    } while (opcion2 < 0 || opcion2 > 2);
                    switch (opcion2) { // ask for num to be searched, then print position
                        case(1): // binary rearch and print position, also send parameters to work
                            dialog.showMessage("El numero esta en la posicion "+
                            metodos.busquedaBinaria(
                                    arreglo,
                                    0, arreglo.length,
                                    Integer.parseInt(dialog.inputMessage("Ingrese el numero a buscar: "))
                                    ));
                            break;
                        case(2): // normal search 
                            dialog.showMessage(String.format(
                                    "El numero se encuentra en la posicion %d",
                                    metodos.busquedaLineal(arreglo,
                                            Integer.parseInt(dialog.inputMessage(
                                                    "Ingrese un numero a buscar: ")))));
                            break;
                    }
                    metodos.presentarArreglo(arreglo); // present array after search
                    break;
                case(6): // delete elenent
                    arreglo = metodos.eliminarElemento(arreglo);
                    metodos.presentarArreglo(arreglo);
                    break;
            }
        }while(opcion != 0);
        dialog.showMessage("Programa termino correctamente"); // end of main program
    }
    
    public static class Metodos {
        
        private Dialog dialog = new Dialog();
        
        public Metodos() { } // constructor
        
        public String opciones() {
            // show principal
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
            // show search type options
            return String.format(
                    "INGRESE EL NUMERO DE LA OPCION\n"
                            + "1. Busqueda Binaria\n"
                            + "2. Busqueda Secuencial\n"
                            + "0. Salir...\n"
                            + "-> ");
        }
        
        public int[] ingresarArreglo(int[] arreglo) {
            // ask for the value of every array
            for (int i = 0; i < arreglo.length; i++) {
                arreglo[i] = Integer.parseInt(this.dialog.inputMessage(String.format(
                        "Ingrese un numero para la posision %d: ", i)));
            }
            return arreglo;
        }
        
        public void presentarArreglo(int[] arreglo) {
            // print array with the Array toString method
            // instead of generating our ouwn string
            this.dialog.showMessage(Arrays.toString(arreglo));
        }
        
        public int[] ordenarArreglo(int[] arreglo) {
            // sort array with bubble sort methos
            for (int i = 0; i < arreglo.length; i++) { // limit the range of the second loop
                for (int j = 0; j < arreglo.length - i - 1; j++) { // try to push the higher values to the end
                    if (arreglo[j] > arreglo[j + 1]) { // if number is higher swap values
                        int temp = arreglo[j];
                        arreglo[j] = arreglo[j + 1];
                        arreglo[j + 1] = temp;
                    }
                }
            }
            return arreglo; // return sorted array
        }
        
        public int[] insertarEnArreglo (int[] arreglo) {
            int index; // check and use index
            do { // check if index is in range
                index = Integer.parseInt( // ask where the new item is going to be
                        this.dialog.inputMessage("Ingrese un indice valido: "));
            } while (index < 0 || index >= arreglo.length);
            for (int i = arreglo.length - 1; i > index; i--) { // moves items to the left to inser the new item 
                arreglo[i] = arreglo[i - 1];
            }
            arreglo[index] = Integer.parseInt( // ask for the new values
                    this.dialog.inputMessage("Ingrese el nuevo elemento: "));
            return arreglo; // return array with new item
        }
        
        public int busquedaLineal(int[] arreglo, int num) {
            // search item while looping through the array
            for (int i = 0; i < arreglo.length; i++) {
                if (num == arreglo[i]) { // return the index if item exists
                    return i;
                }
            }
            return -1; // return -1 if item doesn`t exist
        }
        
        public int busquedaBinaria(int[] arreglo, int start, int end, int num) {
            // binary search method
            int mid = (end + start) / 2; // find middle to divide the array
            if (end - start <= 1) { // check if array can`t be devided
                // return i if num is in the array
                if (arreglo[end] == num) {
                    return end;
                } 
                if (arreglo[start] == num) {
                    return start;
                }
                return -1; // if the num doent exist
            }
            // check in which part of the array may be the num
            if (num <= arreglo[mid]) {
                return this.busquedaBinaria(arreglo, start, mid, num); // if num is in the first part
            }
            return this.busquedaBinaria(arreglo, mid, end, num); // if num is in the second part
        }
        
        public int[] eliminarElemento(int[] arreglo) {
            // delete an element and return an new array with size -1 if possible
            int[] newArreglo;
            int index;
            if (arreglo.length == 0) { // check if array still have num to be deleted
                this.dialog.showMessage("No se puede eliminar mas elementos.");
                return arreglo;
            }
            newArreglo = new int[arreglo.length - 1]; // create new array
            do { // check if index is valid
                index = Integer.parseInt(
                        this.dialog.inputMessage("Ingrese un indice valido del elemento a eliminar: "));
            } while (index < 0 || index >= arreglo.length);
            // copy all elent to the new array 
            for (int i = 0, j = 0; j < newArreglo.length; i++, j++) {
                if (i == index) { // jump element that need to be deleted
                    i++;
                }
                newArreglo[j] = arreglo[i];
            }
            return newArreglo; // return new array 
        }
    }
    
    public static class Dialog {
        // class that handle dialog with user
        // easy if want to change to JOptionPane
        private Scanner input = new Scanner(System.in);
        
        public Dialog() { } // constructor
        
        public String inputMessage(String message) {
            // print mesage and return input of the user
            System.out.print(message);
            return this.input.nextLine();
        }
        
        public void showMessage(String message) {
            System.out.println(message);
        }
    }
}