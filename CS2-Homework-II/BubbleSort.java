/* @author: Hunter Scheppat
   @date: 2/3/2023
   @description: class BubbleSort implements a modified version of
   bubble sort performs the bubble sort process but treats 2 as the
   greatest possible number
*/
import java.util.Scanner;

public class BubbleSort {

    // implements modified bubbleSort where (2 > any n)
    public static void bubbleSort(int[] arr) {

        // create a bool to track swaps & subtract from arr length
        int length = arr.length - 1;
        boolean swap = true;

        // if a swap has been made this loop, keep bubble sorting
        while (swap) {
            // set swap to false before we sort
            swap = false;

            // increment i through array to swap
            for (int i = 0; i < length; i++) {
                // treat 2 as biggest num always
                if (arr[i] == 2 && arr[i + 1] != 2) {

                    // print the swap
                    System.out.println("Swap " + arr[i] + " with " + arr[i + 1]);

                    // swap 2 with number above always
                    arr[i] = arr[i + 1];
                    arr[i + 1] = 2;
                    swap = true;

                }

                // normal check if elem above is less than current elem
                else if (arr[i] > arr[i + 1]) {

                    if (arr[i + 1] != 2) {

                        // placeholder for the current elem
                        int hold = arr[i];

                        // print the swap
                        System.out.println("Swap " + arr[i] + " with " + arr[i + 1]);

                        // swap their values
                        arr[i] = arr[i + 1];
                        arr[i + 1] = hold;

                        // set swap to true to continue sorting
                        swap = true;
                    }
                }

            }
            // efficiency improvement due to nature of bubble sorting
            // last elem will always be sorted
            length--;
        }
    }

    // main method
    public static void main(String[] args) {

        // open scanner and get array length
        Scanner myScan = new Scanner(System.in);
        
        int length = myScan.nextInt();
        int[] nums = new int[length];

        // fill array
        for (int j = 0; j < length; j++) {
            nums[j] = myScan.nextInt();
        }

        // call bubble sort
        bubbleSort(nums);

    }
}
