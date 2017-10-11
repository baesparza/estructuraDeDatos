/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arreglos;

import javax.swing.JOptionPane;

/**
 *
 * @author baesparza
 */
public class Arreglos {

    public static void main(String[] args) {
        Metodos app = new Metodos();
        app.start();
    }

    private static class Metodos {
        
        private final Dialog dialog = new Dialog();
        private int len;
        private int[] arreglo;
        private String message = "";
        
        public Metodos() { }
        
        public void start() {
            this.len = this.dialog.input("Ingrese el limite del arreglo");
            this.arreglo = new int[len];
            
            this.inputArray();
            
            do {
                this.printArray();
                this.options();
            } while (true);
        }
        
        public void inputArray() {
            for (int i = 0; i < this.len; i++) {
                this.arreglo[i] = this.dialog.input("Ingrese un valor para la posicion " + (i + 1));
            } 
        }
        
        public void options() {
            switch (this.dialog.selectOption()) {
                case "Mostrar arreglo": 
                    break;
                case "Insertar elemento": 
                    this.insertar();
                    break;
                case "Eliminar elemento": 
                    this.eliminar();
                    break;
                case "Ordenar arreglo":
                    this.bubbleSort();
                    break;
                case "Buscar elemento": 
                    this.buscar();
                    break;
                case "Modificar elemento": 
                    this.modificar();
                    break;
                case "Maximo elemento": 
                    this.printMax();
                    break;
                case "Minimo elemnto": 
                    this.printMin();
                    break;
                default: 
                    this.dialog.throwErr("Error, por favor pongase en contacto con el administrador");
                    break;
            }
        }
        
        public void printArray() {
            this.message += "[";
            for (int i = 0; i < this.len; i++) {
                this.message += (arreglo[i] + ", ");
            }
            this.message += "]";
            
            this.dialog.print(this.message);
            this.message = "";
        }
        
        public void printMax() {
            int max = this.arreglo[0];
            for (int i = 1; i < this.len; i++) {
                if (this.arreglo[i] > max) {
                    max = this.arreglo[i];
                }
            }
            
            this.dialog.print("El valor maximo es: " + max);
        }
        
        public void printMin() {
            int min = this.arreglo[0];
            for (int i = 1; i < this.len; i++) {
                if (this.arreglo[i] < min) {
                    min = this.arreglo[i];
                }
            }
            
            this.dialog.print("El valor minimo es: " + min);
        }
        
        public void insertar() {
            int index = this.dialog.input("Ingrese el index");
            
            // start from the end of the array to swap items easy
            // if the index doesn`t exist we trough an err
            if (index >= this.len || index < 0) {
                this.dialog.throwErr("Indice fuera de rango");
                this.insertar();
                return;
            }
            for (int i = this.len - 1; i >= 0; i--) {
                if (i == index) {
                    arreglo[i] = this.dialog.input("Ingrese el valor");
                    return;
                }
                this.arreglo[i] = this.arreglo[i - 1];
            }
        }
        
        public void buscar() {
            int num = this.dialog.input("Ingrese el numero a buscar");

            for (int i = this.len - 1; i >= 0; i--) {
                if (this.arreglo[i] == num) {
                    this.dialog.print("El numero ingresado si se encuentra en el arreglo");        
                    return;
                }
            }
            this.dialog.print("El numero ingresado no se encuentra en el arreglo");        
        }

        public void eliminar() {
            int index = this.dialog.input("Ingrese el indice");
            
            // swap values and reduce the len
            if (index >= this.len || index < 0) {
                this.dialog.throwErr("Indice fuera de rango");
                this.eliminar();
                return;
            }
            if (this.len <= 0){
                this.dialog.throwErr("no se pueden eliminar mas items");
                return;
            }
            for (int i = index; i < this.len - 1; i++) {
                this.arreglo[i] = this.arreglo[i + 1];
            }
            this.len--;
        }
        
        public void modificar() {
            int index = this.dialog.input("Ingrse el indice del elemento");
            if (index >= this.len || index < 0) {
                this.dialog.throwErr("Indice fuera de rango");
                this.modificar();
                return;
            }
            this.arreglo[index] = this.dialog.input("Ingrese un valor");
        }
        
        public void bubbleSort() {
            // iterate the array
            for (int i = 0; i < this.len; i++) {
                // iterate from the begining until the last one
                // try to push bigger nums to the back
                for (int j = 0; j < this.len - i - 1; j++) {
                    // swap if num is higher
                    if (this.arreglo[j] > this.arreglo[j + 1]) {
                        int temp = this.arreglo[j];
                        this.arreglo[j] = this.arreglo[j + 1];
                        this.arreglo[j + 1] = temp;
                    }
                }
            }
        }
    }

    private static class Dialog {

        public Dialog() { }
        
        public String selectOption() {
            String[] options = {
                "Mostrar arreglo",
                "Insertar elemento",
                "Eliminar elemento",
                "Ordenar arreglo",
                "Buscar elemento",
                "Modificar elemento",
                "Maximo elemento",
                "Minimo elemnto"
            };
            return (String)JOptionPane.showInputDialog(null, "Seleccione una carrera a cursar", "Carrera", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        }   
        
        public int input(String message) {
            return Integer.parseInt(JOptionPane.showInputDialog(message));
        }
        
        public void print(String mensaje) {
            JOptionPane.showMessageDialog(null, mensaje);
        }
        
        private void throwErr(String err) {
            JOptionPane.showMessageDialog(null, err, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
        
}

