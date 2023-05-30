// Hunter Scheppat | CS2 Homework V

import java.util.*;

/* Do not modify BinaryTree class */
abstract class BinaryTree {

    protected class Node implements TreePrinter.PrintableNode {
        protected int key;
        protected Node parent;
        protected Node left;
        protected Node right;
        public Node(int k, Node p) {
            this.key = k;
            this.parent = p;
            this.left = null;
            this.right = null;
        }
        public Node getLeft() {return left;}
        public Node getRight() {return right;}
        public String toString() {return "" + key;}
    }

    protected Node root;
    BinaryTree() {
        root = null;
    }
}

class BinarySearchTree extends BinaryTree {

    // call delete method with the right node
    public void delete(int k) {
        // find the node that needs to be removed
        Node remove = getDescendant(this.root, k);
        delete(remove);
    }

    private void delete(Node remove) {
        // case 1: if node has no children remove its parent pointer
        if (remove.left == null && remove.right == null) {
            // if we are removing the root, set it to null
            if (remove == this.root) {
                this.root = null;
            }
            // if it is a left leaf, set left pointer to null
            else if (remove.key < remove.parent.key) {
                remove.parent.left = null;
            }
            // if it is a right leaf, set right pointer to null
            else {
                remove.parent.right = null;
            }
        }

        // case 2: if node has right child
        else if (remove.left == null) {
            // if we are removing the root, set child as the new root
            if (remove == this.root) {
                this.root = remove.right;
                remove.right.parent = null;
            }
            // if it is a left child, set the parent left pointer to it
            else if (remove.key < remove.parent.key) {
                remove.parent.left = remove.right;
                remove.right.parent = remove.parent;
            }
            // if it is a right child, set the parent right pointer to it
            else {
                remove.parent.right = remove.right;
                remove.right.parent = remove.parent;
            }
        }

        // case 2.5: if node has right child
        else if (remove.right == null) {
            // if we are removing the root, set child as the new root
            if (remove == this.root) {
                this.root = remove.left;
                remove.left.parent = null;
            }
            // if it is a left child, set the parent left pointer to it
            else if (remove.key < remove.parent.key) {
                remove.parent.left = remove.left;
                remove.left.parent = remove.parent;
            }
            // if it is a right child, set the parent right pointer to it
            else {
                remove.parent.right = remove.left;
                remove.left.parent = remove.parent;
            }
        }

        // case 3: if node has two children
        else {
            Node predecessor = predecessorNode(remove);
            int hold = predecessor.key;
            delete(predecessor);
            remove.key = hold;

        }
    }

    // call successor node method
    public int successorKey(int k) {
        Node target = getDescendant(this.root, k);
        return successorNode(target).key;

    }

    // find the successor
    private Node successorNode(Node n) {
        // if there is a right node, it is min of right tree
        if (n.right != null) {
            return minDescendant(n.right);
        }
        // walk up the tree until we find a left child
        else {
            while (n.parent.key < n.key) {
                n = n.parent;
            }
            return n.parent;
        }
    }

    // call min descendant method
    public int minKey() {
        Node current = minDescendant(this.root);

        return current.key;
    }

    // traverse down the left tree for the min descendant
    public Node minDescendant(Node n) {
        Node current = n;

        while (current.left != null) {
            current = current.left;
        }

        return current;
    }


    // pop the stack until we get the median
    public int medianKey() {
        Stack<Integer> visited = new Stack<>();
        inOrderKeys(this.root, visited);
        Collections.sort(visited);
        int middle = visited.size() / 2;

        int i = 0;
        int hold = 0;
        while (i <= middle) {
            hold = visited.pop();
            i++;
        }

        return hold;

    }

    // push all keys into a stack in their inorder order
    private void inOrderKeys(Node n, Stack<Integer> s) {
        if (n != null) {
            inOrderKeys(n.left, s);
            s.push(n.key);
            inOrderKeys(n.right, s);

        }
    }

    // call count nodes method on the root
    public int nodesInLevel(int h) {

        return countNodes(this.root, h);
    }

    // count nodes recursively by traversing down the trees levels
    private int countNodes(Node n, int h) {
        if (n == null) {
            return 0;
        }
        if (h == 0) {
            return 1;
        }
        int left = countNodes(n.left, h-1);
        int right = countNodes(n.right, h-1);
        return left + right;
    }

    // check if a node is in the tree
    public boolean contains(int needle) {
        Node ret = getDescendant(root, needle);
        if (ret == null) {return false;}
        return true;
    }

    // search the tree for a node with certain key
    private Node getDescendant(Node n, int needle) {
        if (n == null) {return null;}
        if (needle == n.key) {return n;}
        else if (needle < n.key) {return getDescendant(n.left, needle);}
        else {return getDescendant(n.right, needle);}
    }

    // call the add descendant method
    public void add(int key) {
        if (root == null) {
            root = new Node(key, null);
        }
        else {
            addDescendant(key, root);
        }
    }

    // traverse down the tree to where the key fits
    private void addDescendant(int key, Node n) {
        // assumes n is not null
        if (key < n.key) {
            if (n.left == null) {
                n.left = new Node(key, n);
            }
            else {
                addDescendant(key, n.left);
            }
        }
        else {
            if (n.right == null) {
                n.right = new Node(key, n);
            }
            else {
                addDescendant(key, n.right);
            }
        }
    }

    // call max descendant node
    public int maxKey() {
        // assumes root is not null
        return maxDescendant(root).key;
    }

    // max descendant by going down the right side
    private Node maxDescendant(Node n) {
        // assumes n is not null
        Node current = n;
        while (current.right != null) {
            current = current.right;
        }
        return current;
    }

    // call predecessor node with node
    public int predecessorKey(int key) {
        // assumes key is not the minimum
        Node n = getDescendant(root, key);
        return predecessorNode(n).key;
    }

    // return a predecessor node
    private Node predecessorNode(Node x) {
        // if left, max of left tree
        if (x.left != null) {return maxDescendant(x.left);}
        Node ancestor = x.parent;
        // if right child, predecessor is its parent
        while (x != ancestor.right) {
            x = ancestor;
            ancestor = x.parent;
        }
        return ancestor;
    }

    // print the tree
    public void output() {
        TreePrinter.print(root);
    }

    // main method with input logic
    public static void main(String[] args) {
        java.util.Scanner myScanner = new java.util.Scanner(System.in);
        BinarySearchTree myTree = new BinarySearchTree();
        boolean done = false;
        while (!done) {
            String operation = myScanner.next();
            if (operation.equals("add")) {
                myTree.add(myScanner.nextInt());
            }
            else if (operation.equals("contains")) {
                System.out.println(myTree.contains(myScanner.nextInt()));
            }
            else if (operation.equals("maxKey")) {
                System.out.println(myTree.maxKey());
            }
            else if (operation.equals("output")) {
                myTree.output();
            }
            else if (operation.equals("predecessorKey")) {
                System.out.println(myTree.predecessorKey(myScanner.nextInt()));
            }
            else if (operation.equals("successorKey")) {
                System.out.println(myTree.successorKey(myScanner.nextInt()));
            }
            else if (operation.equals("minKey")) {
                System.out.println(myTree.minKey());
            }
            else if (operation.equals("medianKey")) {
                System.out.println(myTree.medianKey());
            }
            else if (operation.equals("delete")) {
                myTree.delete(myScanner.nextInt());
            }
            else if (operation.equals("nodesInLevel")) {
                System.out.println(myTree.nodesInLevel(myScanner.nextInt()));
            }
            else if (operation.equals("quit")) {
                done = true;
            }
        }
    }
}


/**
 * Binary tree printer (do not modify)
 *
 * @author MightyPork
 * @see <a href="https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram-in-java">source</a>
 */
class TreePrinter
{
    /** Node that can be printed */
    public interface PrintableNode {
        /** Get left child */
        PrintableNode getLeft();

        /** Get right child */
        PrintableNode getRight();
    }

    /**
     * Print a tree
     */
    public static void print(PrintableNode root) {
        List<List<String>> lines = new ArrayList<List<String>>();

        List<PrintableNode> level = new ArrayList<PrintableNode>();
        List<PrintableNode> next = new ArrayList<PrintableNode>();

        level.add(root);
        int nn = 1;

        int widest = 0;

        while (nn != 0) {
            List<String> line = new ArrayList<String>();

            nn = 0;

            for (PrintableNode n : level) {
                if (n == null) {
                    line.add(null);

                    next.add(null);
                    next.add(null);
                } else {
                    String aa = n.toString();
                    line.add(aa);
                    if (aa.length() > widest) widest = aa.length();

                    next.add(n.getLeft());
                    next.add(n.getRight());

                    if (n.getLeft() != null) nn++;
                    if (n.getRight() != null) nn++;
                }
            }

            if (widest % 2 == 1) widest++;

            lines.add(line);

            List<PrintableNode> tmp = level;
            level = next;
            next = tmp;
            next.clear();
        }

        int perpiece = lines.get(lines.size() - 1).size() * (widest + 4);
        for (int i = 0; i < lines.size(); i++) {
            List<String> line = lines.get(i);
            int hpw = (int) Math.floor(perpiece / 2f) - 1;

            if (i > 0) {
                for (int j = 0; j < line.size(); j++) {

                    // split node
                    char c = ' ';
                    if (j % 2 == 1) {
                        if (line.get(j - 1) != null) {
                            c = (line.get(j) != null) ? '┴' : '┘';
                        } else {
                            if (j < line.size() && line.get(j) != null) c = '└';
                        }
                    }
                    System.out.print(c);

                    // lines and spaces
                    if (line.get(j) == null) {
                        for (int k = 0; k < perpiece - 1; k++) {
                            System.out.print(" ");
                        }
                    } else {

                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? " " : "─");
                        }
                        System.out.print(j % 2 == 0 ? "┌" : "┐");
                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? "─" : " ");
                        }
                    }
                }
                System.out.println();
            }

            // print line of numbers
            for (int j = 0; j < line.size(); j++) {

                String f = line.get(j);
                if (f == null) f = "";
                int gap1 = (int) Math.ceil(perpiece / 2f - f.length() / 2f);
                int gap2 = (int) Math.floor(perpiece / 2f - f.length() / 2f);

                // a number
                for (int k = 0; k < gap1; k++) {
                    System.out.print(" ");
                }
                System.out.print(f);
                for (int k = 0; k < gap2; k++) {
                    System.out.print(" ");
                }
            }
            System.out.println();

            perpiece /= 2;
        }
    }
}
