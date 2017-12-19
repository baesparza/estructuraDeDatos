package tree;

/**
 *
 * @author baesparza
 */
class Node {
    public Node left, right;
    public int val;
    public Node (int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

class Tree {
    
    private Node root;
    
    public Tree() {
        this.root = null;
    }
    
    public void addNode(int value){
        // create fist node
        if (this.root == null) {
            this.root = new Node(value);
            return;
        }
        // tree already has nodes
        this.addNode(this.root, value);
    }
    
    public void addNode(Node tree, int value){
        // when need to be added to the left
        if (value <= tree.val) {
            // create a new node
            if (tree.left == null) {
                tree.left = new Node(value);
                return;
            }
            // go to left tree
            this.addNode(tree.left, value);
            return;
        }
        
        if (tree.right == null) {
            tree.right = new Node(value);
            return;
        }
        // go to right tree
        this.addNode(tree.right, value);
    }
    
    public String printInOrder() {
        if (this.root == null) return "void tree";
        return "Tree: " + this.printInOrder(this.root);
    }
    
    public String printInOrder(Node tree) {
        String res = "";
        if (tree.left != null) res += this.printInOrder(tree.left);
        res += " " + tree.val;
        if (tree.right != null) res += this.printInOrder(tree.right);
        return res;
    }
}

class main {
    public static void main(String[] args) {
        Tree tree = new Tree();
        tree.addNode(0);
        tree.addNode(1);
        tree.addNode(2);
        tree.addNode(7);
        tree.addNode(4);
        tree.addNode(3);
        System.out.println(tree.printInOrder());
    }
}
