/**
 * Implementation of a linked list from primative constructs. This class stores
 * Word objects at each Ndode.
 * 
 * @author: Mike Lasby
 * @version: 1.0
 * @since: August 12, 2020
 */

public class LinkedList {
    private Node head;

    public LinkedList() {
        this.head = null;
    }

    /**
     * At node to end of list
     */
    public void append(Node n) {
        if (this.head == null) {
            this.head = n;
            return;
        } else {
            Node cursor = this.getHead();
            while (cursor.getNext() != null) {
                cursor = cursor.getNext();
            }
            cursor.setNext(n);
        }
    }

    /**
     * Add node to start of list
     */
    public void prepend(Node n) {
        n.setNext(this.head);
        this.setHead(n);
    }

    /**
     * Add Node n at index idx
     * 
     * @param n   Node to be added
     * @param idx index at which to add node.
     */
    public void insert(Node n, int idx) {
        if (idx == 0) {
            setHead(n);
            return;
        }
        if (idx >= this.length()) {
            System.out.print("ERROR: No node at that index\n");
            return;
        }
        Node cursor = this.getHead();
        int idx_counter = 0;
        while (idx_counter < idx) {
            cursor = cursor.getNext();
            idx_counter++;
        }
        n.setNext(cursor.getNext());
        cursor.setNext(n);
    }

    /**
     * Sets head of linked list to node n
     * 
     * @param n node to set head to
     */
    public void setHead(Node n) {
        this.head = n;
    }

    /**
     * Returns head of this list
     * 
     * @return head node
     */
    public Node getHead() {
        return this.head;
    }

    /**
     * Deletes node at index idx
     * 
     * @param idx index at which to delete node
     */
    public void delete(int idx) {
        if (idx == 0) {
            head = head.getNext();
        } else {
            Node n = head;
            for (int i = 0; i < idx - 1; i++) {
                n = n.next;
            }
            Node del = n.next;
            if (del.next == null) {
                n.setNext(null);
            } else {
                Node n1 = del.next;
                n.setNext(n1);
            }
        }
    }

    /**
     * Removes Node from list
     * 
     * @param n Node to remove from list
     */
    public void pop(Node n) {
        Node prev = null;
        Node curr = getHead();
        while (curr != null) {
            if (curr == n) {
                if (prev != null) {
                    prev.next = curr.next;
                    return;
                } else {
                    head = curr.next;
                    return;
                }
            } else {
                prev = curr;
                curr = curr.next;
            }
        }
    }

    /**
     * Counts the number of anagrams within the list by subtracting the number of
     * anagrams from the size of the list.
     * 
     */
    public int countAnagrams2() {
        Node corr = getHead();
        Node corrNext = corr.getNext();
        int size = this.length(); // assume no anagrams

        while (corrNext != null) {
            if (corr.element.sortedChars.equals(corrNext.element.sortedChars)) {
                size--;
            }
            corr = corr.getNext();
            corrNext = corrNext.getNext();
        }
        return size;
    }

    /**
     * Populates an array of LinkedList objects by binning all elements which are
     * anagrams of eachother.
     * 
     * @return array of LinkedLists
     * 
     */
    public LinkedList[] collectAnagrams() {
        System.out.print(this.length() + "\n");
        int size = countAnagrams2();

        Node curr = getHead();
        LinkedList[] lists = new LinkedList[size];
        int lists_idx = 0;
        // we add each unique element to it's own linked list to start.
        while (curr != null) {
            // System.out.printf("Testing %s\n", curr.toString());
            Node newHead = new Node(new Word(curr.element.word, curr.element.idx));
            LinkedList thisList = new LinkedList();
            thisList.setHead(newHead);
            Node currNext = curr.next;
            if (currNext == null) {
                lists[lists_idx] = thisList;
                return lists;
            }
            // if the next node is an anagram of the current node, then we append it to the
            // same linked list
            while (currNext != null && curr.element.sortedChars.equals(currNext.element.sortedChars)) {
                // System.out.printf("Found anagram %s\n", currNext.toString());
                Node nextNewNode = new Node(new Word(currNext.element.word, currNext.element.idx));
                thisList.append(nextNewNode);
                currNext = currNext.next;
            }
            // append this list to our array of lists
            lists[lists_idx] = thisList;
            lists_idx++;
            curr = currNext;
            if (currNext == null || curr == null) {
                break;
            }
        }
        return lists;
    }

    /**
     * Return length of list, sim to other java length functions, returns the count
     * of the list, not the last element index.
     * 
     * @return length of list
     */
    public int length() {
        if (this.head == null) {
            return 0;
        } else {
            int counter = 1; // We wish to return number of elements, not final index
            Node cursor = this.head;
            while (cursor.getNext() != null) {
                counter++;
                cursor = cursor.getNext();
            }
            return counter;
        }
    }

    /**
     * Helper function for formating for printing to file
     * 
     * @return space seperated Word.word values for every node in list
     */
    public String printToFile() {
        Node head = getHead();
        String buffer = "";
        while (head != null) {
            buffer += head.element.word + " ";
            head = head.next;
        }
        buffer += "\n";
        return buffer;
    }

    /**
     * Helper function to print to sout
     */
    public void print() {
        Node cursor = this.getHead();
        System.out.print("[");

        while (cursor != null) {
            System.out.print(cursor + ",\n");
            cursor = cursor.getNext();
        }
        System.out.print("]\n");

    }

    /**
     * Improved insertion sorting algorithm. Complexity is O(n^2) due nested while
     * loops. We must walk the list in both of the below functions from 0 to n-1
     * 
     * @param head head of list
     * @param curr Node currently being sorted
     * @param by   Parameter to switch on for sorting either by sortedChar or
     *             Word.word
     */
    void insertionSortHelper(Node head, Node curr, String by) {
        if (by == "word") {
            while (head != curr) {
                if (head.element.word.compareTo(curr.element.word) > 0) {
                    Word temp = head.element;
                    head.element = curr.element;
                    curr.element = temp;
                }
                head = head.next;
            }
        } else { // sort by sortedChar
            while (head != curr) {
                if (head.element.sortedChars.compareTo(curr.element.sortedChars) > 0) {
                    Word temp = head.element;
                    head.element = curr.element;
                    curr.element = temp;
                }
                head = head.next;
            }
        }
    }

    /**
     * Entry point and outer loop for insertion sort alogrithm.
     * 
     * @param head Head node
     * @param by   Parameter to swtich on for sorting either by sortedChar or
     *             Word.word
     * @return new head node of sorted list
     */
    public Node insertionSortList(Node head, String by) {
        if (head == null || head.next == null)
            return head;
        Node curr = head.next;
        while (curr != null) {
            insertionSortHelper(head, curr, by);
            curr = curr.next;
        }
        return head;
    }
}

// Due to the trailing 'tail' in this sorting function, it has a compleixity of
// O(n^3) ! Left it in for general interest and my future reference

// public Node insertionSortLL(String by) {
// if (by == "word") {
// if (head == null || head.next == null) {
// return head;
// }
// Node min = new Node(new Word("", -1));
// Node tail = min;
// Node curr = head;
// while (curr != null) {
// Node ahead = curr.next;
// if (curr.element.word.compareTo(tail.element.word) >= 0) {
// tail.next = curr;
// curr.next = null;
// tail = tail.next;
// } else {
// Node temp = min;
// Node prev = min;
// while (temp != null && temp.element.word.compareTo(curr.element.word) <= 0) {
// prev = temp;
// temp = temp.next;
// }
// prev.next = curr;
// curr.next = temp;
// }
// curr = ahead;
// }
// return min.next;
// } else { // sort by chars
// if (head == null || head.next == null) {
// return head;
// }
// Node min = new Node(new Word("", -1));
// Node tail = min;
// Node curr = head;
// while (curr != null) {
// Node ahead = curr.next;
// if (curr.element.sortedChars.compareTo(tail.element.sortedChars) >= 0) {
// tail.next = curr;
// curr.next = null;
// tail = tail.next;
// } else {
// Node temp = min;
// Node prev = min;
// while (temp != null &&
// temp.element.sortedChars.compareTo(curr.element.sortedChars) <= 0) {
// prev = temp;
// temp = temp.next;
// }
// prev.next = curr;
// curr.next = temp;
// }
// curr = ahead;
// }
// return min.next;

// }

// }