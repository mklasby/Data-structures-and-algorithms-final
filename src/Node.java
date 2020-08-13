/**
 * Node class for use in building linked lists
 * 
 * @author: Mike Lasby
 * @since: August 12, 2020
 * @version: 1.0
 */

public class Node {
    public Node next = null;
    public Word element = null;

    public Node(Word element) {
        this.element = element;
    }

    public Node getNext() {
        return this.next;
    }

    public void setNext(Node n) {
        this.next = n;
    }

    public Word getElement() {
        return this.element;
    }

    public void setElement(Word n) {
        this.element = n;
    }

    @Override
    public String toString() {
        return this.element.toString();
    }

}