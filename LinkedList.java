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
     * 
     */
    public Node getHead() {
        return this.head;
    }

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

    public int countAnagrams() {
        Node a = getHead();
        Node b = a.getNext();
        int counter = 0;

        while (b != null) {
            if (a.element.sortedChars.equals(b.element.sortedChars)) {
                System.out.print("anagram found\n");
                System.out.print(a.element.word + "\n");
                System.out.print(b.element.word + "\n");
                Node c = b.next;
                counter++; // found an anagram
                while (c != null) {
                    if (b.element.sortedChars.equals(c.element.sortedChars)) {
                        System.out.print(c.element.word + "\n");
                        c = c.next;
                        counter++; // found another
                    } else {
                        break;
                    }
                }
                if (c == null || a.next == null) {
                    break;
                }
                a = c.next;
                if (a == null) {
                    break;
                }
                b = a.next;
            } else {
                a = b;
                b = b.next;
            }
        }
        return counter;

    }

    public LinkedList[] collectAnagrams() {
        System.out.print(this.length() + "\n");

        // int size = this.length() - countAnagrams() - 1;
        int size = countAnagrams2();
        System.out.printf("\nSIZE! = %d\n", size);

        Node curr = getHead();
        LinkedList[] lists = new LinkedList[size];
        int lists_idx = 0;

        while (curr != null) {
            System.out.printf("Testing %s\n", curr.toString());
            Node newHead = new Node(new Word(curr.element.word, curr.element.idx));
            LinkedList thisList = new LinkedList();
            thisList.setHead(newHead);
            Node currNext = curr.next;
            if (currNext == null) {
                lists[lists_idx] = thisList;
                return lists;
            }
            while (currNext != null && curr.element.sortedChars.equals(currNext.element.sortedChars)) {
                System.out.printf("Found anagram %s\n", currNext.toString());
                Node nextNewNode = new Node(new Word(currNext.element.word, currNext.element.idx));
                thisList.append(nextNewNode);
                currNext = currNext.next;
            }
            lists[lists_idx] = thisList;
            lists_idx++;
            curr = currNext;
            if (currNext == null || curr == null) {
                break;
            }
        }
        return lists;
    }

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

    public Node insertionSortLL(String by) {
        if (by == "word") {
            if (head == null || head.next == null) {
                return head;
            }
            Node min = new Node(new Word("", -1));
            Node tail = min;
            Node curr = head;
            while (curr != null) {
                Node ahead = curr.next;
                if (curr.element.word.compareTo(tail.element.word) >= 0) {
                    tail.next = curr;
                    curr.next = null;
                    tail = tail.next;
                } else {
                    Node temp = min;
                    Node prev = min;
                    while (temp != null && temp.element.word.compareTo(curr.element.word) <= 0) {
                        prev = temp;
                        temp = temp.next;
                    }
                    prev.next = curr;
                    curr.next = temp;
                }
                curr = ahead;
            }
            return min.next;
        } else { // sort by chars
            if (head == null || head.next == null) {
                return head;
            }
            Node min = new Node(new Word("", -1));
            Node tail = min;
            Node curr = head;
            while (curr != null) {
                Node ahead = curr.next;
                if (curr.element.sortedChars.compareTo(tail.element.sortedChars) >= 0) {
                    tail.next = curr;
                    curr.next = null;
                    tail = tail.next;
                } else {
                    Node temp = min;
                    Node prev = min;
                    while (temp != null && temp.element.sortedChars.compareTo(curr.element.sortedChars) <= 0) {
                        prev = temp;
                        temp = temp.next;
                    }
                    prev.next = curr;
                    curr.next = temp;
                }
                curr = ahead;
            }
            return min.next;

        }

    }

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

    public void print() {
        Node cursor = this.getHead();
        System.out.print("[");

        while (cursor != null) {
            System.out.print(cursor + ",\n");
            cursor = cursor.getNext();
        }
        System.out.print("]\n");

    }

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
        } else {
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