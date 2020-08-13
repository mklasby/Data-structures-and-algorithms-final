
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
        }

        // am.words.print();
        // // am.words.insertionSort("word");
        // am.words.insertionSortLL("word");
        am.words.print();
        am.words.setHead(am.words.insertionSortLL("sortedChars"));
        am.words.print();
        int count = am.words.countAnagrams();
        System.out.print(count + "\n");
        // am.words.print();

        am.lists = am.words.collectAnagrams();
        for (int i = 0; i < am.lists.length; i++) {
            am.lists[i].print();
        }
        for (int i = 0; i < am.lists.length; i++) {
            am.lists[i].setHead(am.lists[i].insertionSortLL("word"));
            am.lists[i].print();
        }

    }

    public void readFile(String inFile) throws IOException {
        String fname = String.format("./%s.txt", inFile);
        BufferedReader br = new BufferedReader(new FileReader(fname));

        String text;
        int idx = 0;
        while ((text = br.readLine()) != null) {
            Node node = new Node(new Word(text, idx));
            words.append(node);
            idx += 1;
        }
        br.close();
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

}
