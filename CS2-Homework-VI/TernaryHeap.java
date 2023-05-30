/*
    @class: TernaryHeap
    @description: Heap where each node has 3 children
    @author: Hunter Scheppat & Prof. Levear @Boston College
 */
public class TernaryHeap {
    private int[] storage;
    private int size;
    // initialize the heap
    public TernaryHeap() {
        storage = new int[5];
        size = 0;
    }
    // print the heap
    public void output() {
        for (int i = 0; i < size; i++) {
            System.out.print(storage[i] + " ");
        }
        System.out.println();
    }

    // homework methods
    private int left(int i) {
        return 3*i + 1;
    }
    private int right(int i) {
        return 3*i + 3;
    }
    private int middle(int i) {
        return 3*i + 2;
    }
    private int parent(int i) {
        return (i-1) / 3;
    }
    // add a value to the heap
    public void add(int k) {
        // make space if necessary
        if (size == storage.length) {
            makeSpace();
        }
        // put value at end and heapify up
        storage[size] = k;
        heapUp(size);
        size++;
    }

    // validate heap property by working up
    private void heapUp(int index) {
        if (index == 0) {
            return;
        }
        // if val breaks heap property swap it with parent
        if (storage[index] > storage[parent(index)]) {
            swap(index, parent(index));
            heapUp(parent(index));
        }
    }

    // validate heap property by working down
    private void heapDown(int index) {
        // find if the heap has a max child
        int maxChildIndex = getMaxChild(index);
        if (maxChildIndex == -1) {
            return;
        }
        // if the max is greater swap it with the current value
        if (storage[index] < storage[maxChildIndex]) {
            swap(maxChildIndex, parent(maxChildIndex));
            // keep working down through the swapped child
            heapDown(maxChildIndex);
        }
    }
    // find the max child by comparing all 3
    private int getMaxChild(int index) {
        int leftIndex = left(index);
        int middleIndex = middle(index);
        int rightIndex = right(index);

        // if no children return -1
        if (leftIndex >= size) {
            return -1;
        }

        // work through as if left is biggest
        int maxIndex = leftIndex;

        // as other elements are bigger replace the index
        if (middleIndex < size && storage[middleIndex] > storage[maxIndex]) {
            maxIndex = middleIndex;
        }

        if (rightIndex < size && storage[rightIndex] > storage[maxIndex]) {
            maxIndex = rightIndex;
        }

        // return the largest index
        return maxIndex;
    }

    // swap two elements
    private void swap(int i, int j) {
        int temp = storage[i];
        storage[i] = storage[j];
        storage[j] = temp;
    }

    // return the max
    public int peekMax() {
        return storage[0];
    }

    // return the max and shorten the heap
    public int popMax() {
        int max = storage[0];
        // put the last element at the top
        storage[0] = storage[size - 1];
        size--;

        // heap down
        heapDown(0);

        return max;
    }

    // build a heap from an array
    public void buildHeap(int[] Y) {
        storage = Y;
        size = Y.length;

        // heap down on the nodes
        for (int i = size / 3; i >= 0; i--) {
            heapDown(i);
        }
    }

    // count the nodes in a level
    public int nodesInLevel(int h) {
        // find start and end of that level
        int start = (int)((Math.pow(3, h) - 1) / 2);
        int end = (int)(Math.min(size, start + Math.pow(3, h)));
        int solution = 0;

        // increment while less than size
        for (int i = start; i <  end; i++) {
            if (i >= size) {
                return solution;
            }
            solution++;
        }
        // return number
        return solution;
    }
    // make space by doubling array
    private void makeSpace() {
        int[] doubleSize = new int[size * 2];
        for (int i = 0; i < size; i++) {
            doubleSize[i] = storage[i];
        }
        storage = doubleSize;
    }

    // Boilerplate code

    public static void main(String[] args) {
        java.util.Scanner myScanner = new java.util.Scanner(System.in);
        TernaryHeap myHeap = new TernaryHeap();
        boolean done = false;
        System.out.println("Type heap commands:");
        while (!done) {
            String[] tokens = myScanner.nextLine().split(" ");
            String operation = "";
            int[] operands = null;
            if (tokens.length > 0) {
                operation = tokens[0];
                operands = new int[tokens.length - 1];
                for (int i = 0; i < operands.length; i++) {
                    operands[i] = Integer.parseInt(tokens[1 + i]);
                }
            }

            if (operation.equals("add")) {
                myHeap.add(operands[0]);
            } else if (operation.equals("peekMax")) {
                System.out.println(myHeap.peekMax());
            } else if (operation.equals("popMax")) {
                System.out.println(myHeap.popMax());
            } else if (operation.equals("output")) {
                myHeap.output();
            } else if (operation.equals("buildHeap")) {
                myHeap.buildHeap(operands);
            } else if (operation.equals("nodesInLevel")) {
                System.out.println(myHeap.nodesInLevel(operands[0]));
            } else if (operation.equals("quit")) {
                done = true;
            }
        }
    }
}
 