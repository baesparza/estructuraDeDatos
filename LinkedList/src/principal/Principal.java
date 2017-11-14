package principal;

/**
 *
 * @author baesparza
 */
public class Principal {

    public static void main(String[] args) {
        ListNode head;
        head = null;
        System.out.println(head);
    }
    
    class ListNode<T> {
        ListNode(T x) {
            value = x;
        }
        T value;
        ListNode<T> next;
    }
}
