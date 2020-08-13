import java.util.Arrays;

public class CustomVector<E> {
    private int size = 0;
    private int defaultCap = 100;
    private Object items[];

    public CustomVector() {
        items = new Object[defaultCap];
    }

    public int length() {
        return size - 1;
    }

    public void append(E e) {
        if (size == items.length) {
            doubleCap();
        }
        items[size] = e;
        size++;
    }

    private void doubleCap() {
        int newSize = items.length * 2;
        items = Arrays.copyOf(items, newSize);
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.print(items[i].toString() + "\n");
        }
    }

    public void set(int idx, Object o) {
        this.items[idx] = o;
    }

    @SuppressWarnings("unchecked")
    public E get(int i) {
        if (i >= this.size || i < 0) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Size " + i);
        }
        return (E) items[i];
    }
}