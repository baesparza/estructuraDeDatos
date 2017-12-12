package principal;

import java.util.Scanner;

/**
 *
 * @author baesparza
 */
public class Principal {
    
    private ListNode head, tail;
    
    public Principal() {
        this.head = null;
        this.tail = null;
    }
        
    public void addBeggining(int value) {
        ListNode temp = new ListNode(value);
        if (this.head == null && this.tail == null) {
            this.head = temp;
            this.tail = temp;
        } else {
            temp.next = this.head;
            this.head.prev = temp;
            this.head = temp;
        }
    }
    
    public void addEnding(int value) {
        ListNode temp = new ListNode(value);
        if (this.head == null && this.tail == null) {
            this.head = temp;
            this.tail = temp;
        } else {
            temp.prev = this.tail;
            this.tail.next = temp;
            this.tail = temp;
        }
    }
    
    public void deleteLastElement() {
    }
    
    public void sortArr(int[] arr) {
        if (arr.length != 0) return; // check if there are items to sort
        ListNode newList = new ListNode(0);
        ListNode current = null;
        for (int num: arr) {
            current = newList;
            while (current.next != null && num > current.next.value) current = current.next;
            // stop were item need to be inserted
            ListNode newItem = new ListNode(num);
            newItem.next = current.next;
            current.next = newItem;
        } 
        this.head = newList.next;
        /*
        if (arr.length != 0) {
            ListNode newList = new ListNode(0);
            ListNode current = null;
            for (int num: arr) {
                current = newList;
                while (current.next != null && num > current.next.value) current = current.next;
                ListNode newItem = new ListNode(num);
                newItem.next = current.next;
                current.next = newItem;
            } 
            this.head = newList.next;
        }
        */
    }
    
    public void removeNumFromList(int num) {
    }
    
    public void searchElement(int num) {
    }
    
    public void printHead() {
        ListNode current = this.head; // we secure our head
        while (current != null) {
            System.out.print(String.format("[%d] -> ", current.value)); // iterate and print each node value
            current = current.next; // iterate until we arrive the last node
        } 
        System.out.println("null"); // print pointer of last node, null
    }
    
    public void printTail() {
        ListNode current = this.tail; // we secure our head
        while (current != null) {
            System.out.print(String.format("[%d] -> ", current.value)); // iterate and print each node value
            current = current.prev; // iterate until we arrive the last node
        }
        System.out.println("null"); // print pointer of last node, null
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
                        + "\t3. Añadir nodo al final\n"
                        + "\t4. Buscar nodo\n"
                        + "\t5. Ordenar lista\n"
                        + "\t6. Eliminar nodo con valor\n"
                        + "\t7. Eliminar ultimo nodo\n"
                        + "\t0. Salir\n"
                        + "\t-> ");
                opcion = input.nextInt();
            } while (opcion < 0 || opcion > 7);
            switch(opcion) {
                case 1: // insert at the beggining
                    System.out.print("Ingrese el nuevo valor: ");
                    lista.addBeggining(input.nextInt());
                    break;
                case 2: // print elements
                    lista.printHead();
                    lista.printTail();
                    break;
                case 3: // add at the end
                    System.out.print("Ingrese el nuevo valor: ");
                    lista.addEnding(input.nextInt());
                    break;
                case 4: // search element
                    System.out.print("Ingrese el valor a buscar: ");
                    lista.searchElement(input.nextInt());
                    break;
                case 5: // Sort elements
                    int[] arr = {3,5,1,2,0,4,-1};
                    lista.sortArr(arr);
                    break;
                case 6: // remove element form array
                    System.out.print("Ingrese el valor a eliminar: ");
                    lista.removeNumFromList(input.nextInt());
                    break;
                case 7: // remove last node
                    lista.deleteLastElement();
                    break;
            }  
        } while (opcion != 0);
    }
}
