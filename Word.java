
/**
 * Class to store information about our words. Will reside in Node.element and
 * be accessed for sorting
 * 
 * @author: Mike Lasby
 * @since: August 13, 2020
 * @version 2.0
 */

public class Word implements Comparable<Word> {
    public String word;
    public String sortedChars;
    public int idx;

    public Word(String word, int idx) {
        this.word = word.toLowerCase();
        this.idx = idx;
        populateSortedChars();
    }

    public String getWord() {
        return word;
    }

    public String getSortedChars() {
        return sortedChars;
    }

    public int getIdx() {
        return idx;
    }

    @Override
    public String toString() {
        return String.format("Word: %s, SortedChars: %s, Index: %d", word, sortedChars, idx);
    }

    /**
     * Custom compareTo function to compare the Word.word attributes.
     */
    @Override
    public int compareTo(Word b) {
        return word.compareTo(b.getWord());
    }

    /**
     * Custom compareTo function to compare the Word.sortedChar attributes.
     */
    public int comparebyChar(Word b) {
        return sortedChars.compareTo(b.sortedChars);
    }

    /**
     * Sorts word chars by value and adds to this.sortedChar
     */
    private void populateSortedChars() {
        char[] charArr = this.word.toCharArray();
        charInsertionSort(charArr);
        this.sortedChars = new String(charArr);
    }

    /**
     * Helper function to sort chars of each word Due to nested for and while loop,
     * has a complexity of O(n^2)
     */
    public static void charInsertionSort(char[] arr) {
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

    public void set(int i) {
        this.idx = i;
    }

}
