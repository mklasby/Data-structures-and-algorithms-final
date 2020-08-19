
/**
 * Main function for running question 2. Required Node, Word, and LinkedList classes
 * 
 * @author: Mike Lasby
 * @since: August 12, 2020
 * @version: 1.0
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.io.File;

public class Exercise2 {
    private LinkedList[] lists;

    // We will use this list to count our words and initilize the more complex data
    // structure
    private LinkedList words = new LinkedList();

    public static void main(String[] args) {
        Exercise2 am = new Exercise2();

        String ioError = "Please enter input in the form of < java Exercise2 inputFileName outputFileName>\n"
                + "Where:\n" + "inputFileName: String name of text file to read from, excluding file format\n"
                + "outputFileName: String name of text file to write output to, excluding file format\n"
                + "Please try again! Goodbye\n";

        String inFile = null;
        String outFile = null;

        try {
            inFile = args[0];
            outFile = args[1];
        } catch (Exception e) {
            System.out.print(ioError);
        }

        try {
            am.readFile(inFile);
        } catch (IOException e) {
            System.out.print("ERROR: No such input file found!\n");
            System.out.print(ioError);
            System.exit(0);
        }
        long preTime = System.currentTimeMillis(); // algo time starts here

        // sort our list of words by sortecChar to speed up subsequent functions, we
        // rely on a sorted list for the functions which count and collect the anagrams
        am.words.setHead(am.words.insertionSortList(am.words.getHead(), "sortedChar"));
        am.lists = am.words.collectAnagrams(); // populate LinkedList[] array
        for (int i = 0; i < am.lists.length - 1; i++) {
            am.lists[i].setHead(am.lists[i].insertionSortList(am.lists[i].getHead(), "word")); // sort each linked list
        }
        am.quickSort(am.lists, 0, am.lists.length - 1); // quick sort used to sort the array of linkedlist
        long postTime = System.currentTimeMillis(); // algo time ends here
        System.out.printf("File processed in %d ms\n", postTime - preTime);

        try {
            am.writeOutFile(am.lists, outFile, postTime - preTime);

        } catch (IOException e) {
            System.out.print(
                    "ERROR: Output file invalid, please ensure that the output file name only contains alpha numeric characters.");
            System.out.print(ioError);
            System.exit(0);
        }

    }

    /**
     * Reads from file
     * 
     * @param inFile input file name, no extension.
     * @throws IOException
     */
    public void readFile(String inFile) throws IOException {
        String fname;
        if (inFile.endsWith(".txt")) {
            fname = String.format("./%s", inFile);
        } else {
            fname = String.format("./%s.txt", inFile);
        }

        BufferedReader br = new BufferedReader(new FileReader(fname));

        String text;
        int idx = 0;
        while ((text = br.readLine()) != null) {
            if (text.equals(" ") || text.equals("\n") || text.equals("\t")) {
                continue;
            }
            Node node = new Node(new Word(text, idx)); // create a new node which contains a word object
            words.append(node);
            idx += 1;
        }
        br.close();
    }

    /**
     * Utility function to print lists content to standard out
     * 
     * @param arr array of LinkedList instances
     */
    public void printOutLists(LinkedList[] arr) {
        for (int i = 0; i < arr.length; i++) {
            LinkedList thisList = arr[i];
            Node cursor = thisList.getHead();
            System.out.print("\n");
            while (cursor != null) {
                System.out.print(cursor.element.word + " ");
                cursor = cursor.getNext();
            }
        }
    }

    public void writeOutFile(LinkedList[] arr, String outFile, long time) throws IOException {
        String fname = String.format("./%s.txt", outFile);

        File fout = new File(fname);
        if (!fout.exists()) {
            fout.createNewFile();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(fout));

        for (int i = 0; i < arr.length; i++) {
            // arr[i].print();
            writer.write(arr[i].printToFile());
        }
        writer.close();
    }

    /**
     * Two way randomized pivot quick-sort algorithm. Algorithm will have an average
     * runtime of O(nlogn) for a random list due to recursive calls and for loop
     * used to pivot the partiions
     * 
     * @param arr array of LinkedLists to be sorted
     * @param l   left bound (0 to sort entire array at initial call)
     * @param r   right bound(r = arr.length-1 to sort entire array at initial call)
     */
    public void quickSort(LinkedList[] arr, int l, int r) {
        if (l >= r) {
            return;
        }

        // get random element from list to minimze potential for a unbalanced paritions
        // and swap with element in idx = 0
        Random rand = new Random();
        int randIdx = l + rand.nextInt(r - l + 1);
        swap(arr, randIdx, r);

        // partitioning
        LinkedList pivot = arr[l];
        int pIdx = l;
        for (int i = l + 1; i <= r; i++) {
            if (arr[i].getHead().element.word.compareTo(pivot.getHead().element.word) <= 0) {
                pIdx++;
                swap(arr, i, pIdx);
            }
        }
        swap(arr, l, pIdx);
        quickSort(arr, l, pIdx - 1);
        quickSort(arr, pIdx + 1, r);
    }

    public static void swap(LinkedList[] arr, int i, int j) {
        LinkedList temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
