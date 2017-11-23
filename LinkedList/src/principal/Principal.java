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
        
    public void insertAtTheBegginning(int value) {
        ListNode temp = new ListNode(value); // we create the node to be added before the first node
        temp.next = this.head; // we point head since head will point to the new node
        this.head = temp; // head point to the new node that point to preview head, now 2nd in the list node
    }
    
    public void addAtTheEnd(int value) {
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
        if (this.head == null || this.head.next == null) return;
        ListNode helper = new ListNode(0); //new starter of the sorted list
	ListNode actual = this.head; //the node will be inserted
	ListNode preview = helper; //insert node between pre and pre.next
	ListNode next = null; //the next node will be inserted
	//not the end of input list
	while( actual != null ){
            next = actual.next;
            //find the right place to insert
            while( preview.next != null && preview.next.value < actual.value ){
            	preview = preview.next;
            }
            //insert between pre and pre.next
            actual.next = preview.next;
            preview.next = actual;
            preview = helper;
            actual = next;
	}
        this.head = helper;
        /*
        for (ListNode i = this.head; i.next != null; i = i.next) {
            for (ListNode j = i.next; j != null; j = j.next) {
                if (i.value > j.value) {
                    int temp = i.value;
                    i.value = j.value;
                    j.value = temp;
                }
            }  
        }
        */
    }
    
    public void removeNumFromList(int num) {
        if (this.head == null) return;
        while (this.head.value == num) { // check if first item has to be removed, if true, then check i the second has to
            this.head = this.head.next;
            if (this.head == null) return; // check if we have arrived to the end of the list | avoid <null>.next err
        }
        ListNode actual = this.head; // to know were we are
        ListNode preview = null; // can return to last node
        while (actual != null) { // check if we have arrived to the end of the list
            if (actual.value == num) { // the node we are has the vlue we have to remove
                preview.next = actual.next; // we tell the last node.next to our current node.next, since we don`t need this mode
                actual = actual.next; // keep iterating trough the list
            } else {
                preview = actual; // set the preview node to the actual
                actual = actual.next; // keep iterating trough the list
            }
        }
    }
    
    public void searchElement(int num) {
        ListNode actual = this.head; // secure our head,
        while (actual != null) { // check if we have items in linked list, and iterate trought all elements in the list
            if (actual.value == num){ // if we found the value we output a message saying we found it, and break the function
                System.out.println("El elemento se encuenta en la lista");
                return;
            }
            actual = actual.next; // keep iterating
        }
        System.out.println("No se enconto en la lista"); // say we didn`t found the element searched
    }
    
    public void printLinkedList() {
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
                    lista.insertAtTheBegginning(input.nextInt());
                    break;
                case 2: // print elements
                    lista.printLinkedList();
                    break;
                case 3: // add at the end
                    System.out.print("Ingrese el nuevo valor: ");
                    lista.addAtTheEnd(input.nextInt());
                    break;
                case 4: // search element
                    System.out.print("Ingrese el valor a buscar: ");
                    lista.searchElement(input.nextInt());
                    break;
                case 5: // Sort elements
                    lista.sortList();
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
