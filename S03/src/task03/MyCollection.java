package task03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

// Описать собственную коллекцию – список на основе массива.
// Коллекция должна иметь возможность хранить любые типы данных, иметь
// методы добавления и удаления элементов.
public class MyCollection<T> implements Iterable<T> {
    private Object[] array;
    private int size;
    private final int INIT_SIZE = 10;

    public MyCollection() {
        this.array = new Object[INIT_SIZE];
    }

    public void add(T element) {
        if (size >= array.length) {
            Object[] temp = array;
            array = new Object[array.length * 2];
            System.arraycopy(temp, 0, array, 0, temp.length);
        }
        array[size++] = element;
    }

    public void remove(int index) {
        if (index >= size || index < 0) return;

        if (size > 0) {
            System.arraycopy(array, index + 1, array, index, size - index);
            size--;
        } else {
            System.out.println("\nArray is empty!");
        }
    }

    @Override
    public String toString() {
        if (size <= 0) return "";
        Object[] temp = new Object[size];
        System.arraycopy(array, 0, temp, 0, size);
        return Arrays.toString(temp);
    }

    @Override
    public Iterator<T> iterator() {
        List<T> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add((T) array[i]);
        }
        return new MyIterator<>(list);
    }

    public static void main(String[] args) {
        MyCollection<Integer> arr = new MyCollection<>();

        arr.add(5);
        arr.add(2);
        arr.add(6);
        arr.add(55);
        arr.add(15);

        // MyIterator in action!
        for (Integer e: arr) {
            System.out.println(e);
        }

        arr.remove(3);

        System.out.println(arr.toString());
    }

}
