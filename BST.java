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
    public void delete(int chave) {
        deleteNode(chave);
    }

    private boolean deleteNode(int key) {
        Node current = this.root;
        Node currentDad = null;
        while(current != null ){
            if (current.getValue() == key){
                break;
            } else if (key < current.getValue() ){
                currentDad = current;
                current= current.getLeft();

            } else {
                currentDad = current;
                current= current.getRight();
            }

        }  if ( current != null ){
            if(current.getRight() != null){
                Node replaceNode = current.getRight();
                Node replaceDad = current ;
                while ( replaceNode.getLeft()!= null){
                    replaceDad = replaceNode;
                    replaceNode = replaceNode.getLeft();
                } if (currentDad!= null){
                    if ( current.getValue()<currentDad.getValue()){
                        currentDad.setLeft(replaceNode);
                    }
                    else {
                        currentDad.setRight(replaceNode);
                    }
                }
                else {
                    this.root = replaceNode;
                } 
                

                if ( replaceNode.getValue()<replaceDad.getValue()){
                    replaceDad.setLeft(null);
                }
                else {
                    replaceDad.setRight(null);
                }

            } else if ( current.getLeft() != null){
                Node replaceNode = current.getLeft();
                Node replaceDad = current ;
                while ( replaceNode.getRight()!= null){
                    replaceDad = replaceNode;
                    replaceNode = replaceNode.getRight();
                
                } 
                if (currentDad != null ){
                    if ( current.getValue()<currentDad.getValue()){
                        currentDad.setLeft(replaceNode);
                    }
                    else {
                        currentDad.setRight(replaceNode);
                    }

                }
                else {
                    this.root = replaceNode;
                }
                
                if ( replaceNode.getValue()<replaceDad.getValue()){
                    replaceDad.setLeft(null);
                }
                else {
                    replaceDad.setRight(null);
                }

            }else {
                if (currentDad!= null){
                    if ( current.getValue()<currentDad.getValue()){
                        currentDad.setLeft(null);
                    }
                    else {
                        currentDad.setRight(null);
                    }

                } else {
                    this.root = null;
                }

            }
            return true ;
        }
        else {
            return false ;
        }
    }

    @Override
    public boolean search(int key) {
        if (root == null){
            return false;
        }
        return searchNode(root, key);
    }

    private boolean searchNode(Node n, int key){
        if (n.getValue() == key){
            return true;
        } else if (key > n.getValue()){
            if (n.getRight() == null){
                return false;
            } else {
                return searchNode(n.getRight(),key);
            }
        } else {
            if (n.getLeft() == null){
                return false;
            } else {
                return searchNode(n.getLeft(),key);
            }
        }
    }

    @Override
    public int minimum() {
        if (root == null) {
            return -1;
        } else {
            return minimumNode(root, root.getValue());
        }
    }

    private int minimumNode(Node no, int minimum) {
        int min = minimum;

        if (no.getLeft() != null) {
            if (no.getLeft().getValue() < minimum) {
                min = no.getLeft().getValue();
            }
            return minimumNode(no.getLeft(), min);
        } else {
            return min;
        }
    }

    @Override
    public int maximum() {
        if (root == null) {
            return -1;
        } else {
            return maximumNode(root, root.getValue());
        }
    }

    private int maximumNode(Node no, int maximum) {
        int max = maximum;

        if (no.getRight() != null) {
            if (no.getRight().getValue() > maximum) {
                max = no.getRight().getValue();
            }
            return maximumNode(no.getRight(), max);
        } else {
            return max;
        }
    }

    @Override
    public int sucessor(int chave) {
        int[] sucessor = new int[1];
        int max = maximumNode(root, root.getValue());
        sucessor[0] = max;

        if (chave == max) {
            return -1;
        } else {
            sucessorNode(root, chave, sucessor);
            return sucessor[0];
        }
    }

    private void sucessorNode(Node no, int key, int[] sucessor) {
        if (no != null) {
            sucessorNode(no.getLeft(), key, sucessor);
            if (no.getValue() > key && no.getValue() < sucessor[0]) {
                sucessor[0] = no.getValue();
            }
            sucessorNode(no.getRight(), key, sucessor);
        }
    }

    @Override
    public int prodessor(int chave) {
        int [] prodessor = new int [1];
        int min = minimumNode(root, root.getValue());
        prodessor[0] = min;

        if (chave == min) {
            return -1;
        } else {
            prodessorNode(root, chave, prodessor);
            return prodessor[0];
        }
    }

    private void prodessorNode(Node no, int key, int[] prodessor) {
        if (no != null) {
            prodessorNode(no.getRight(), key, prodessor);
            if (no.getValue() < key && no.getValue() > prodessor[0]) {
                prodessor[0] = no.getValue();
            }
            prodessorNode(no.getLeft(), key, prodessor);
        }
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
        System.out.println(tree.sucessor(1));
        System.out.println(tree.sucessor(2));
        System.out.println(tree.sucessor(3));
        System.out.println(tree.prodessor(7));
        System.out.println(tree.prodessor(8));
        System.out.println(tree.prodessor(9));
        tree.delete(1);
        tree.delete(2);
        tree.delete(3);
        System.out.println(tree.search(1));
        System.out.println(tree.search(2));
        System.out.println(tree.search(3));
    }
}
