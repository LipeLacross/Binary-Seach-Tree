public class BST implements EstruturaDeDados{

    private Node root;

    @Override
    public void insert(int key) {
        if (root == null){
            root = new Node(key);
        } else{
            insertNode(root, key);   
        }
    }

    private void insertNode(Node n, int key){
        if (key >= n.getValue()){
            //inserir na direita
            if (n.getRight() == null){
                Node newN = new Node(key);
                n.setRight(newN);
            } else {
                insertNode(n.getRight(), key);
            }
        } else {
            //inserir na esquerda
            if (n.getLeft() == null){
                Node newN = new Node(key);
                n.setLeft(newN);
            } else {
                insertNode(n.getLeft(), key);
            }
        }
    }

    @Override
    public void delete(int key) {
        deleteNode(this.root, key);
    }
    
    private Node deleteNode(Node node, int key) {
        if (node == null) {
            return null;
        }
    
        if (key < node.getValue()) {
            node.setLeft(deleteNode(node.getLeft(), key));
        } else if (key > node.getValue()) {
            node.setRight(deleteNode(node.getRight(), key));
        } else {
            if (node.getLeft() == null && node.getRight() == null) {
                node = null;
            } else if (node.getLeft() == null) {
                node = node.getRight();
            } else if (node.getRight() == null) {
                node = node.getLeft();
            } else {
                Node temp = findMinNode(node.getRight());
                node.setValue(temp.getValue());
                node.setRight(deleteNode(node.getRight(), temp.getValue()));
            }
        }
    
        return node;
    }
    
    private Node findMinNode(Node node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    @Override
    public boolean search(int key) {
        return searchNode(root, key);
    }
    
    private boolean searchNode(Node node, int key) {
        if (node == null) {
            return false;
        }
    
        if (node.getValue() == key) {
            return true;
        } else if (key < node.getValue()) {
            return searchNode(node.getLeft(), key);
        } else {
            return searchNode(node.getRight(), key);
        }
    }

    @Override
    public int minimum() {
        if (root == null) {
            return -1; // Return -1 if the tree is empty
        } else {
            Node current = root;
            while (current.getLeft() != null) {
                current = current.getLeft();
            }
            return current.getValue();
        }
    }
    
    @Override
    public int maximum() {
        if (root == null) {
            return -1; // Return -1 if the tree is empty
        } else {
            Node current = root;
            while (current.getRight() != null) {
                current = current.getRight();
            }
            return current.getValue();
        }
    }
    
    @Override
    public int successor(int key) {
        Node current = root;
        Node successor = null;
        while (current != null) {
            if (current.getValue() > key) {
                successor = current;
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }
        return (successor != null) ? successor.getValue() : -1; // Return -1 if no successor is found
    }
    
    @Override
    public int predecessor(int key) {
        Node current = root;
        Node predecessor = null;
        while (current != null) {
            if (current.getValue() < key) {
                predecessor = current;
                current = current.getRight();
            } else {
                current = current.getLeft();
            }
        }
        return (predecessor != null) ? predecessor.getValue() : -1; // Return -1 if no predecessor is found
    }
    

    public static void main(String[] args) {
        BST tree = new BST();
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);
        tree.insert(6);
        tree.insert(7);
        tree.insert(8);
        tree.insert(9);
        tree.insert(10);
        System.out.println(tree.search(1));
        System.out.println(tree.search(2));
        System.out.println(tree.search(3));
        System.out.println(tree.successor(1));
        System.out.println(tree.successor(2));
        System.out.println(tree.successor(3));
        System.out.println(tree.predecessor(7));
        System.out.println(tree.predecessor(8));
        System.out.println(tree.predecessor(9));
        tree.delete(2);
        tree.delete(3);
        tree.delete(4);
        System.out.println(tree.search(2));
        System.out.println(tree.search(3));
        System.out.println(tree.search(4));
    }
}
