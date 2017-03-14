package track.lessons.lesson3;

import java.util.NoSuchElementException;

/**
 * Должен наследовать List
 *
 * Должен иметь 2 конструктора
 * - без аргументов - создает внутренний массив дефолтного размера на ваш выбор
 * - с аргументом - начальный размер массива
 */

/**
 * MyArrayList - список на основе массива, массив должен динамически расширяться,
 * если в старом не хватает места для элемента.
 * Для копирования массива следует использовать метод System.arraycopy()
* */

public class MyArrayList extends List {

    private int capacity = 1;
    private int currentSize = 0;
    private int[] data;

    public MyArrayList() {
        this.data = new int[this.capacity];
    }

    public MyArrayList(int capacity) {
        this.capacity = capacity;
        this.currentSize = 0;
        this.data = new int[this.capacity];
    }

    @Override
    void add(int item) {
        if (this.capacity > this.currentSize) {
            data[currentSize] = item;
        } else {
            capacity *= 2;
            int[] newData = new int[capacity];
            System.arraycopy(data, 0, newData, 0, this.currentSize++);
            this.data = newData;
        }
    }

    @Override
    int remove(int idx) throws NoSuchElementException {
        throwExceptionIfNotExists(idx);

        int oldValue = this.data[idx];

        System.arraycopy(data, idx + 1, data, idx, (this.currentSize--) - idx - 1);
        if (this.capacity > currentSize * 4) {
            int[] newData = new int[currentSize * 2];
            this.capacity = capacity / 2;
            System.arraycopy(this.data, 0, newData, 0, this.currentSize);
            this.data = newData;
        }
        return oldValue;
    }

    @Override
    int get(int idx) throws NoSuchElementException {
        throwExceptionIfNotExists(idx);
        return this.data[idx];
    }

    @Override
    int size() {
        return currentSize;
    }

    private void throwExceptionIfNotExists(int idx) throws NoSuchElementException {
        if (idx >= this.currentSize || idx < 0) {
            throw new NoSuchElementException();
        }
    }
}



