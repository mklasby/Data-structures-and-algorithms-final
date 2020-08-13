import java.util.Comparator;

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

    public final Comparator<Word> bySortedChar = new Comparator<Word>() {
        public int compare(Word a, Word b) {
            return a.getSortedChars().compareTo(b.getSortedChars());
        }

        public boolean equals(Object o) {
            return o == this;
        }
    };

    public int compareTo(Word b) {
        return word.compareTo(b.getWord());
    }

    public int comparebyChar(Word b) {
        return sortedChars.compareTo(b.sortedChars);
    }

    private void populateSortedChars() {
        char[] charArr = this.word.toCharArray();
        charInsertionSort(charArr);
        this.sortedChars = new String(charArr);
    }

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
