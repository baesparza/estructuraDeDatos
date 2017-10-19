package operacionesconarreglos;

import java.util.Scanner;

/**
 *
 * @author baesparza
 */
public class OperacionesConArreglos {

    public static void main(String[] args) {
        int opcion, opcion2;
        Dialog dialog = new Dialog();
        Metodos metodos = new Metodos();
        
        do { // keep program asking the user what to do
            do { // check if valid option
                opcion = dialog.inputMessage(dialog.opciones());
            } while (opcion < 0 || opcion > 6);
            switch (opcion) {
                case(1): // input values for the array
                    metodos.ingresarArreglo();
                    break;
                case(2): // present array value/items
                    metodos.presentarArreglo();
                    break;
                case(3): // sort and print array                    
                    metodos.ordenarArreglo();
                    metodos.presentarArreglo();
                    break;
                case(4): // insert element and print array
                    if (metodos.PuedeInsertarElemento()) {// check if available space
                        dialog.showMessage("ARREGLO LLENO!!");
                        return;
                    }
                    do { // check if index is in range
                        opcion2 = dialog.inputMessage(dialog.opcionesInsertar());// ask where the new item is going to be
                    } while (opcion2 < 1 || opcion2 > 2);
                    switch (opcion2) {
                        case(1): // push to the end
                            metodos.insertarFinal();
                            break;
                        case(2): // ask for the index
                            metodos.insertarEnArreglo();
                            break;
                    }
                    metodos.presentarArreglo();
                    break;
                case(5): // sort array and ask the type of search user whants
                    metodos.ordenarArreglo();
                    do { // check if valid option
                        opcion2 = dialog.inputMessage(dialog.opcionesBusqueda());
                    } while (opcion2 < 0 || opcion2 > 2);
                    switch (opcion2) { // ask for num to be searched, then print position
                        case(1): // binary rearch and print position, also send parameters to work
                            dialog.showMessage("El numero esta en la posicion "+
                                    metodos.busquedaBinaria(dialog.inputMessage("Ingrese el numero a buscar: ")));
                            break;
                        case(2): // normal search 
                            dialog.showMessage(String.format(
                                    "El numero se encuentra en la posicion %d",
                                    metodos.busquedaLineal(dialog.inputMessage("Ingrese un numero a buscar: "))));
                            break;
                    }
                    metodos.presentarArreglo(); // present array after search
                    break;
                case(6): // delete elenent
                    metodos.eliminarElemento();
                    metodos.presentarArreglo();
                    break;
            }
        }while(opcion != 0);
        dialog.showMessage("Programa termino correctamente"); // end of main program
    }
    
    public static class Metodos {
        
        Dialog dialog = new Dialog();
        int size;// keep track of the las index user pushed a value
        int[] arreglo;
        
        public Metodos() {// constructor
            // gen new array with the size the user whants
            this.arreglo = new int[dialog.inputMessage("Ingrese el tamaño del arreglo: ")];
            this.size = 0;
        } 
        
        public void ingresarArreglo() {
            this.size = 0; // reset the value, new array is coming
            int opc = 1;
            for (int i = 0; 
                    (i < this.arreglo.length) && opc == 1; // loop while spaces in array are valid and user whants to continue
                    i++, this.size++) { // know how much items in the array
                arreglo[i] = this.dialog.inputMessage(String.format(
                        "Ingrese un numero para la posision %d: ", i));
                do { // veryfy if option is valis ::1 si; 2 no::
                    opc = this.dialog.inputMessage(this.dialog.opcionesIngresarArreglo());
                } while (opc < 0 || opc > 1);
            }
            if (this.size == this.arreglo.length) { // tell the user ther is no more room for more values
                this.dialog.showMessage("ARREGLO LLENO!!");
            }
        }
        
        public void presentarArreglo() {
            // print array with the inserted values
            String values = "";
            for (int i = 0; i < this.size; i++) { 
                values += (this.arreglo[i] + ", ");
            }
            this.dialog.showMessage("["+values+"]");
        }
        
        public void ordenarArreglo() {
            // sort array with bubble sort methos
            for (int i = 0; i < this.size; i++) { // limit the range of the second loop
                for (int j = 0; j < this.size - i - 1; j++) { // try to push the higher values to the end
                    if (this.arreglo[j] > this.arreglo[j + 1]) { // if number is higher swap values
                        int temp = this.arreglo[j];
                        this.arreglo[j] = this.arreglo[j + 1];
                        this.arreglo[j + 1] = temp;
                    }
                }
            }
        }
        
        public Boolean PuedeInsertarElemento() {
            return this.size == this.arreglo.length;
        }
        
        public void insertarEnArreglo () {
            int index;
            do { // check if index is in range
                index = this.dialog.inputMessage(// ask where the new item is going to be
                        String.format("Ingrese un indice valido(0 a %d): ", this.size));
            } while (index < 0 || index > this.size);
            for (int i = this.size; i > index; i--) {// moves items to the left to inser the new item 
                arreglo[i] = arreglo[i - 1];
            }
            arreglo[index] = this.dialog.inputMessage("Ingrese el nuevo elemento: ");// ask for the new values
            this.size++; // increment the size of array
            return;
        }
        
        public void insertarFinal() {
            this.arreglo[this.size] = this.dialog.inputMessage("Ingrese el nuevo elemento: ");// ask for the new values
            this.size++;
        }
        
        public int busquedaLineal(int num) {
            // search item while looping through the array
            for (int i = 0; i < this.size; i++) {
                if (num == arreglo[i]) { // return the index if item exists
                    return i;
                }
            }
            return -1; // return -1 if item doesn`t exist
        }
        
        public int busquedaBinaria(int num) {
            return this.startBusquedaBinaria(this.arreglo, 0, this.size, num);
        }
        
        private int startBusquedaBinaria(int[] arreglo, int start, int end, int num) {
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
                return this.startBusquedaBinaria(arreglo, start, mid, num); // if num is in the first part
            }
            return this.startBusquedaBinaria(arreglo, mid, end, num); // if num is in the second part
        }
        
        public void eliminarElemento() {
            // delete an element and return an new array with size -1 if possible
            int index;
            if (this.size == 0) { // check if array still have num to be deleted
                this.dialog.showMessage("No se puede eliminar mas elementos.");
                return;
            }
            do { // check if index is valid
                index = this.dialog.inputMessage(
                        String.format("Ingrese un indice valido del elemento a eliminar (0 a %d): ", this.size - 1));
            } while (index < 0 || index >= this.size);
            // copy all elent to the new array 
            for (int i = index; i < this.size - 1; i++) {
                this.arreglo[i] = arreglo[i + 1];
            }
            this.size--; // delete one element
        }
    }
    
    public static class Dialog {
        // class that handle dialog with user
        // easy if want to change to JOptionPane
        private Scanner input = new Scanner(System.in);
        
        public Dialog() { } // constructor
        
        public int inputMessage(String message) {
            // print mesage and return input of the user
            System.out.print(message);
            return Integer.parseInt(this.input.nextLine());
        }
        
        public void showMessage(String message) {
            System.out.println(message);
        }
        
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
        
        public String opcionesIngresarArreglo() {
            // show search type options
            return String.format(
                    "Desea ingresar otro elemento\n"
                            + "1. SI\n"
                            + "0. NO\n"
                            + "-> ");
        }
        
        public String opcionesInsertar() {
            // show search type options
            return String.format(
                    "Donde desea insertar nuevo numero\n"
                            + "1. Al final\n"
                            + "2. Insertar entre varios elementos\n"
                            + "-> ");
        }
    }
}