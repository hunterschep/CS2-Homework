/*
@name: Hunter Scheppat
@date: February 27th, 2023
@description: TripSort.java checks if TripSort can be used in an array by splitting
an array into two different arrays of every other element. By comparing the sorted versions
of these arrays, it can be determined whether TripSort will work
 */

import java.util.Scanner;

public class TripSort {
    public static void main(String[] args) {
        // get the array size & create the array
        Scanner myScan = new Scanner(System.in);
        int length = myScan.nextInt();
        int[] arr = new int[length];

        // add the input to our main array
        for (int i = 0; i < arr.length; i++) {
            arr[i] = myScan.nextInt();
        }

        if (canTripSort(arr)) {
            System.out.println("TripSort will work");
        } else {
            System.out.println("TripSort will not work");
        }
    }

    // checks if tripSort will work and returns a true or false bool
    public static boolean canTripSort(int[] arr) {
        int mid = arr.length / 2;
        // create two arrays to hold the split of our main array
        int[] evenElements = new int[arr.length - mid];
        int[] oddElements = new int[mid];

        // fill both arrays
        int j = 0;
        int k = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 0) {
                evenElements[j] = arr[i];
                j++;
            } else {
                oddElements[k] = arr[i];
                k++;
            }
        }

        // mergesort both arrays
        mergeSort(evenElements, 0, evenElements.length - 1);
        mergeSort(oddElements, 0, oddElements.length - 1);

        // even elements must be less than odd elements at each index
        for (int h = 0; h < oddElements.length; h++) {
            if (evenElements[h] > oddElements[h]) {
                return false;
            }
        }
        return true;
    }

    // merge an array given two indices and a middle
    public static void merge(int[] arr, int start, int mid, int end) {

        // if the direct merge is already sorted
        int second = mid + 1;
        if (arr[mid] <= arr[second]) {
            return;
        }

        // keep sorting and incrementing
        while (start <= mid && second <= end) {

            // increment if already sorted
            if (arr[start] <= arr[second]) {
                start++;
            }
            else {
                int value = arr[second];
                int idx = second;

                // decrement
                while (idx != start) {
                    arr[idx] = arr[idx - 1];
                    idx--;
                }
                arr[start] = value;

                // increment pointers
                start++;
                mid++;
                second++;
            }
        }
    }

    // mergeSort an array
    public static void mergeSort(int[] arr, int left, int right) {
        // check if sorting is not complete
        if (left >= right) {
            return;
        }
        else {
            // for large l and r
            int middle = (left + right) / 2;

            // Sort first and second halves
            mergeSort(arr, left, middle);
            mergeSort(arr, middle + 1, right);

            // merge the left and right
            merge(arr, left, middle, right);
        }
    }
}

