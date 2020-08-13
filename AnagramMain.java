
/**
 * Main function for running question 2
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
import java.io.Reader;
import java.util.Comparator;
import java.util.Random;
import java.io.File;
import java.util.Arrays;

public class AnagramMain {
    private Word[] inputText;
    private LinkedList[] lists;
    private LinkedList words = new LinkedList(); // We will use this list to count our words

    public static void main(String[] args) {
        AnagramMain am = new AnagramMain();

        String ioError = "Please enter input in the form of < java AnagramMain inputFileName outputFileName>\n"
                + "Where:\n" + "inputFileName: String name of text file to read from (.txt)\n"
                + "outputFileName: String name of text file to write output to (.txt)\n"
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
        long preTime = System.currentTimeMillis();
        // am.words.setHead(n);.words.insertionSortLL("sortedChars");
        am.words.setHead(am.words.insertionSortList(am.words.getHead(), "sortedChar"));
        am.words.print();
        am.lists = am.words.collectAnagrams();

        for (int i = 0; i < am.lists.length - 1; i++) {
            am.lists[i].print();
        }
        for (int i = 0; i < am.lists.length - 1; i++) {
            am.lists[i].setHead(am.lists[i].insertionSortLL("word"));
        }

        am.quickSort(am.lists, 0, am.lists.length - 1);
        long postTime = System.currentTimeMillis();

        try {
            am.writeOutFile(am.lists, outFile, postTime - preTime);

        } catch (IOException e) {
            System.out.print(
                    "ERROR: Output file invalid, please ensure that the output file name is only alpha numeric characters.");
            System.out.print(ioError);
            System.exit(0);
        }

    }

    public void readFile(String inFile) throws IOException {
        String fname = String.format("./%s.txt", inFile);
        BufferedReader br = new BufferedReader(new FileReader(fname));

        String text;
        int idx = 0;
        while ((text = br.readLine()) != null) {
            if (text.equals(" ") || text.equals("\n") || text.equals("\t")) {
                continue;
            }
            Node node = new Node(new Word(text, idx));
            words.append(node);
            idx += 1;
        }
        br.close();
    }

    public void writeOutFile(LinkedList[] arr, String outFile, long time) throws IOException {
        String fname = String.format("./%s.txt", outFile);

        File fout = new File(fname);
        if (!fout.exists()) {
            fout.createNewFile();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(fout));

        for (int i = 0; i < arr.length; i++) {
            writer.write(arr[i].printToFile());
        }
        writer.close();
    }

    public void sortByChar(CustomVector<Word> arr) {
        Word temp;
        for (int i = 1; 1 < arr.length(); i++) {
            int j = i;
            while (j > 0 && arr.get(j - 1).comparebyChar(arr.get(j)) > 0) {
                temp = arr.get(j); // temp will be a Word obj
                arr.set(j, arr.get(j - 1));
                arr.set(j - 1, temp);
            }
        }
    }

    public static void insertionSort(char[] arr) {
        char temp;
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
     * Two way randomized pivot quick-sort algorithm
     * 
     * @param arr array to be sorted
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
