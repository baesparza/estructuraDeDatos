package principal;

import java.util.Scanner;

/**
 *
 * @author baesparza
 */
public class Principal {
    
    private ListNode head;
    
    public Principal() {
        this.head = null;
    }
        
    public void insertNode(int value) {
        ListNode temp = new ListNode(value); // we create the node to be added before the first node
        temp.next = this.head; // we point head since head will point to the new node
        this.head = temp; // head point to the new node that point to preview head, now 2nd in the list node
    }
    
    public void addNode(int value) {
        if (this.head == null) { // chech if there are available nodes
            this.head = new ListNode(value); // since we dont have nodes, we create a ne one and break
            return;
        }
        ListNode actual = this.head; // we secure out head, with an temporal pointer
        while(actual.next != null) actual = actual.next; // iterate until we arrive the last node
        actual.next = new ListNode(value); // last node point to new node 
    }
    
    public void deleteLastElement() {
        if (this.head == null) return; // check if we have node to delete
        if (this.head.next == null) { // if we have only one node, whe sould delete it
            this.head = null; // point to null head
            return;
        }
        ListNode actual = this.head;
        while(actual.next.next != null) actual = actual.next; // iterate until we arrive to almost last node  
        actual.next = null; // point last node to null, before old node that point to null
    }
    
    public void sortList() {
        ListNode actual = this.head;
    }
    
    public void removeElementFromList(int num) {
        
    }
    
    public void searchElement(int num) {
        if (this.head == null) {
            System.out.println("Lista Vacia");
            return;
        }
        ListNode actual = this.head;
        while (actual != null) {
            if (actual.value == num){
                System.out.println("El elemento se encuenta en la lista");
                return;
            }
            actual = actual.next;
        }
        System.out.println("No se enconto en la lista");
    }
    
    public void printAll() {
        ListNode actual = this.head; // we secure our head
        while (actual != null) {
            System.out.print(String.format("[%d] -> ", actual.value)); // iterate and print each node value
            actual = actual.next; // iterate until we arrive the last node
        } 
        System.out.println(actual); // print pointer of last node, null
    }
    
    public static void main(String[] args) {
        Principal lista = new Principal();
        int opcion = 0;
        
        Scanner input = new Scanner(System.in);
        
        do {// keep asking the user what to do
            do { // check input is in range of options
                System.out.print("Ingese una opcion\n"
                        + "\t1. Insertar nuevo nodo al principio\n"
                        + "\t2. Presentar lista\n"
                        + "\t3. Añadir nodo\n"
                        + "\t3. Buscar valor\n"
                        + "\t0. Salir\n"
                        + "\t-> ");
                opcion = input.nextInt();
            } while (opcion < 0 || opcion > 4);
            switch(opcion) {
                case 1:
                    // ask for the new value and add new node
                    System.out.print("Ingrese el nuevo valor: ");
                    lista.insertNode(input.nextInt());
                    break;
                case 2:
                    lista.printAll();
                    break;
                case 3:
                    System.out.print("Ingrese el nuevo valor: ");
                    lista.addNode(input.nextInt());
                    break;
                case 4:
                    System.out.print("Ingrese el valor a buscar: ");
                    lista.searchElement(input.nextInt());
                    break;
            }  
        } while (opcion != 0);
    }
}
