
/**
 * This program implements bubble, insertion, merge, and quick sort. Inputs are
 * passed as CLI arguments in the form of < java sortingAlgos order size
 * algorithm outputfile >
 * 
 * @author: Mike Lasby
 * @since: August 12, 2020
 * @version: 1.0
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class SortingAlgos {

    public static void main(String[] args) {
        // expecting args[]: [order, size, algo, outFile]
        String order = null;
        int size = 0;
        String algo = null;
        String outFile = null;
        String errorString = "Please enter format in form of\n"
                + "< java sortingAlgos order size algorithm outputfile >\n" + "Where:\n"
                + "order: str [Options: ascending, descending, random]\n" + "size: int -> size of array, positive int\n"
                + "algorithm: str [Options: bubble, insertion, merge, quick]\n"
                + "outputfile: str -> user specified file name\n" + "Try again, GOODBYE!\n\n";
        try {
            order = args[0];
            size = Integer.parseInt(args[1]);
            if (size < 0) {
                System.out.print("ERROR: Size must be positive\n");
                System.out.print(errorString);
                System.exit(0);

            }
            algo = args[2];
            outFile = args[3];
            System.out.printf(order + Integer.toString(size) + algo + outFile + "\n");

        } catch (Exception e) {
            System.out.print(errorString);
            System.exit(0);
        }

        Random rand = new Random();
        int[] arr = new int[size];
        int i = 1;

        // we use 2000 as upper bound to restrict value of array of length 1 million to
        // 2 billion, slightly less than max int value ( (2^32-1) // 2) )
        // nextInt will return an int b/w 0-2000
        if (order.equals("ascending")) {
            arr[0] = rand.nextInt(2000);
            while (i < size) {
                arr[i] = rand.nextInt(2000) + arr[i - 1];
                i++;
            }
        } else if (order.equals("descending")) {
            arr[0] = Integer.MAX_VALUE;
            while (i < size) {
                arr[i] = arr[i - 1] - rand.nextInt(2000);
                i++;
            }
        } else if (order.equals("random")) {
            i = 0; // no limit or initial value required when randoms are selected
            while (i < size) {
                arr[i] = rand.nextInt();
                i++;
            }
        } else {
            System.out.print(errorString);
            System.out.print("ERROR: Order string not found\n");
            System.exit(0);
        }
        System.out.print(Integer.toString(arr.length) + "\n");

        long preTime = 0;
        long postTime = 0;
        if (algo.equals("quick")) {
            preTime = System.nanoTime();
            quickSort(arr, 0, arr.length - 1);
            postTime = System.nanoTime();
        } else if (algo.equals("bubble")) {
            preTime = System.nanoTime();
            bubbleSort(arr);
            postTime = System.nanoTime();
        } else if (algo.equals("insertion")) {
            preTime = System.nanoTime();
            insertionSort(arr);
            postTime = System.nanoTime();
        } else if (algo.equals("merge")) {
            preTime = System.nanoTime();
            mergeSort(arr, 0, arr.length - 1);
            postTime = System.nanoTime();
        } else {
            System.out.print("ERROR: Algo string not found\n");
            System.out.print(errorString);
            System.exit(0);
        }

        // sanity check to make sure array is sorted. Tough to tell with these big
        // negative numbers
        if (testSort(arr)) {
            System.out.printf("Sorted with %s alogrithm in %d ms\n", algo, postTime - preTime);
        }
        try {
            writeOutFile(arr, outFile, order, size, algo, postTime - preTime);
        } catch (IOException e) {
            System.out.print("ERROR: File Name Formatting Error\n");
            System.out.print(errorString);
            System.exit(0);
        }

    }

    /**
     * Write results out to file
     * 
     * @param arr     array to be printed
     * @param outFile output file name, no extension
     * @param order   CLI parameter
     * @param size    CLI parameter
     * @param algo    CLI parameter
     * @param time    time to sort
     * @throws IOException
     */
    public static void writeOutFile(int[] arr, String outFile, String order, int size, String algo, long time)
            throws IOException {
        String fname = String.format("./%s.csv", outFile);

        File fout = new File(fname);
        if (!fout.exists()) {
            fout.createNewFile();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(fout));

        // Header used to pull data from each test run. Unsure if this would interfere
        // with automated testing so I have removed these lines.
        // String header = String.format("Order: %s Size:%d Algo:%s Time (ms): %d",
        // order, size, algo, time);
        // writer.write(header + "\n");

        for (int i = 0; i < arr.length; i++) {
            writer.write(Integer.toString(arr[i]) + ", ");
        }
        writer.close();
    }

    /**
     * Confirms that array is sorted
     * 
     * @param arr
     * @return true/false
     */
    public static boolean testSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] >= arr[i - 1]) {
                i++;
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * Helper print function to print our array
     * 
     * @param arr
     */
    public static void printArray(int[] arr) {
        System.out.print("[");

        for (int i = 0; i < arr.length; i++) {
            System.out.print(Integer.toString(arr[i]) + ", ");
        }
        System.out.print("]\n");
    }

    /**
     * Bubble sort. Time complexity will be O(n^2) due to nested for loop.
     * 
     * @param arr array to sort
     */
    public static void bubbleSort(int[] arr) {
        int temp;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                }
            }
        }
    }

    /**
     * Insertion sort. Time complexity is O(n^2) due to nested for/while pair.
     * 
     * @param arr array to sort.
     */
    public static void insertionSort(int[] arr) {
        int temp;
        for (int i = 1; i < arr.length; i++) {
            int j = i;
            while (j > 0 && arr[j - 1] > arr[j]) {
                temp = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = temp;
                j--;
            }
        }

    }

    /**
     * Merge Sort. Complexity of O(nlogn) due to outer for loop and recusive
     * traversal of binary tree
     * 
     * @param arr array to sort
     * @param l   left bound, 0 to start
     * @param r   right bound, arr.length-1 to start
     */
    public static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int mid = (l + r) / 2;
            mergeSort(arr, l, mid);
            mergeSort(arr, mid + 1, r);
            merge(arr, l, mid, r);
        }
    }

    public static void merge(int[] arr, int l, int mid, int r) {
        int[] left = new int[mid - l + 2];
        int[] right = new int[r - mid + 1];

        for (int i = 0; i < left.length - 1; i++) {
            left[i] = arr[l + i];
        }
        for (int j = 0; j < right.length - 1; j++) {
            right[j] = arr[mid + j + 1];
        }
        int i = 0;
        int j = 0;
        right[right.length - 1] = Integer.MAX_VALUE;
        left[left.length - 1] = Integer.MAX_VALUE;
        for (int k = l; k <= r; k++) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
        }
    }

    /**
     * Two way randomized pivot quick-sort algorithm. Average complexity of O(nlogn)
     * due to for loop in paritioning step and recusions down and back up binary
     * tree.
     * 
     * @param arr array to be sorted
     * @param l   left bound (0 to sort entire array at initial call)
     * @param r   right bound(r = arr.length-1 to sort entire array at initial call)
     */
    public static void quickSort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }

        // get random element from list to minimze potential for a unbalanced paritions
        // and swap with element in idx = 0
        Random rand = new Random();
        int randIdx = l + rand.nextInt(r - l + 1);
        swap(arr, randIdx, r);

        // partitioning
        int pivot = arr[l];
        int pIdx = l;
        for (int i = l + 1; i <= r; i++) {
            if (arr[i] <= pivot) {
                pIdx++;
                swap(arr, i, pIdx);
            }
        }
        swap(arr, l, pIdx);
        quickSort(arr, l, pIdx - 1);
        quickSort(arr, pIdx + 1, r);
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}