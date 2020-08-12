
/**
 * This program implements bubble, insertion, merge, and quick sort. Inputs are
 * passed as CLI arguments in the form of < java sortingAlgos order size
 * algorithm outputfile >
 * 
 * @author: Mike Lasby
 * @since: August 12, 2020
 * @version: 1.0
 */

import java.util.Random;

public class SortingAlgos {

    public static void main(String[] args) {
        // expecting str[]: [order, size, algo, outFile]
        String order = null;
        int size = 0;
        String algo = null;
        String outFile = null;
        String errorString = "Please enter format in form of\n"
                + "< java sortingAlgos order size algorithm outputfile >\n" + "Where:\n"
                + "order: str [Options: ascending, descending, random]\n" + "size: int -> size of array\n"
                + "algorithm: str [Options: bubble, insertion, merge, quick]\n"
                + "outputfile: str -> user specified file name\n" + "Try again, GOODBYE!\n\n";
        try {
            order = args[0];
            size = Integer.parseInt(args[1]);
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
            while (i < size - 1) {
                arr[i] = rand.nextInt(2000) + arr[i - 1];
                i++;
            }
        } else if (order.equals("descending")) {
            arr[0] = Integer.MAX_VALUE;
            while (i < size - 1) {
                arr[i] = arr[i - 1] - rand.nextInt(2000);
                i++;
            }
        } else if (order.equals("random")) {
            while (i < size) {
                // no limit or seed value required when randoms are selected
                arr[i] = rand.nextInt();
            }
        } else {
            System.out.print(errorString);
            System.out.print("ERROR: Order string not found\n");
            System.exit(0);
        }

    }

    public void bubbleSort(int[] arr) {
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

    public void insertionSort(int[] arr) {

    }

    public void mergeSort(int[] arr) {

    }

    public void quickSort(int[] arr) {

    }

}