// Hunter Scheppat
interface List<E> {
    void add(int index, E item);
    E get(int index);
    int indexOf(E item);
    E removeAt(int index);
    void output();
}
public class BCArrayList<E> implements List<E> {
    private E[] arr;

    // given code
    public static void main(String[] args) {
        java.util.Scanner myScanner = new java.util.Scanner(System.in);
        List<Integer> myList = new BCArrayList<>();
        boolean done = false;
        while (!done) {
            String operation = myScanner.next();
            if (operation.equals("add")) {
                myList.add(myScanner.nextInt(), myScanner.nextInt());
            }
            else if (operation.equals("get")) {
                System.out.println(myList.get(myScanner.nextInt()));
            }
            else if (operation.equals("removeAt")) {
                System.out.println(myList.removeAt(myScanner.nextInt()));
            }
            else if (operation.equals("indexOf")) {
                System.out.println(myList.indexOf(myScanner.nextInt()));
            }
            else if (operation.equals("output")) {
                myList.output();
            }
            else {
                done = true;
            }
        }
    }
    // constructor
    @SuppressWarnings("unchecked")
    public BCArrayList() {
        arr = (E[]) new Object[10];
    }

    // add an item
    public void add(int index, E item) {
        if (arr[arr.length-1] != null) {
            this.makeSpace();
        }
        for (int i = arr.length-2; i >= index; i--) {
            arr[i+1] = arr[i];
        }
        arr[index] = item;
    }
    

    // get an item from an index
    public E get(int index) {
        return arr[index];
    }

    // find an item
    public int indexOf(E item) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                E current = arr[i];
                if (current.equals(item)) {
                    return i;
                }
            }
        }
        return -1;
    }

    // remove an item
    public E removeAt(int index) {
        E removed = arr[index];
        for(int i = index; i < arr.length - 1; i++) {
            arr[i] = arr[i + 1];
        }
        arr[arr.length - 1] = null;
        return removed;
    }

    // print all used spaces
    public void output() {
        for (E item: arr) {
            if (item != null) {
                System.out.print(item + " ");
            }
        }
        System.out.println();
    }

    // make more space
    @SuppressWarnings("unchecked")
    public void makeSpace() {
        E[] replacement = (E[]) new Object[arr.length * 2];
        for (int i = 0; i < replacement.length; i++) {
            if (i < arr.length) {
                replacement[i] = arr[i];
            }
            else {
                replacement[i] = null;
            }
        }
        arr = replacement;
    }
}
