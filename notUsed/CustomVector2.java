import java.util.Arrays;

public class CustomVector2 {
    private Word[] arr;
    private int size;

    public CustomVector2(String[] input, int size) {
        this.size = size;
        arr = new Word[size];
        for (int i = 0; i < size; i++) {
            arr[i] = new Word(input[i], i);
        }
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.print(arr[i].toString() + "\n");
        }
    }
    // public int length() {
    // return size - 1;
    // }

    // public void append(E e) {
    // if (size == items.length) {
    // doubleCap();
    // }
    // items[size] = e;
    // size++;
    // }

    // private void doubleCap() {
    // int newSize = items.length * 2;
    // items = Arrays.copyOf(items, newSize);
    // }

    // public void print() {
    // for (int i = 0; i < size; i++) {
    // System.out.print(items[i].toString() + "\n");
    // }
    // }

    // public void set(int idx, Object o) {
    // this.items[idx] = o;
    // }

    // @SuppressWarnings("unchecked")
    // public E get(int i) {
    // if (i >= this.size || i < 0) {
    // throw new IndexOutOfBoundsException("Index: " + i + ", Size " + i);
    // }
    // return (E) items[i];
    // }
    // }
}}

}

// public int length() {
// return size - 1;
// }

// public void append(E e) {
// if (size == items.length) {
// doubleCap();
// }
// items[size] = e;
// size++;
// }

// private void doubleCap() {
// int newSize = items.length * 2;
// items = Arrays.copyOf(items, newSize);
// }

// public void set(int idx, Object o) {
// this.items[idx] = o;
// }

// @SuppressWarnings("unchecked")
// public E get(int i) {
// if (i >= this.size || i < 0) {
// throw new IndexOutOfBoundsException("Index: " + i + ", Size " + i);
// }
// return (E) items[i];
// }
// }