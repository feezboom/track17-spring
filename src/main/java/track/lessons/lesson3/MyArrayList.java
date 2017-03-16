package track.lessons.lesson3;

import java.util.NoSuchElementException;

/*
  Должен наследовать List

  Должен иметь 2 конструктора
  - без аргументов - создает внутренний массив дефолтного размера на ваш выбор
  - с аргументом - начальный размер массива
 */

/**
 * MyArrayList - список на основе массива, массив должен динамически расширяться,
 * если в старом не хватает места для элемента.
 * Для копирования массива следует использовать метод System.arraycopy()
* */

public class MyArrayList extends List {

    private int capacity = 1;
    private int[] data;

    public MyArrayList() {
        data = new int[capacity];
    }

    public MyArrayList(int capacity) {
        this.capacity = capacity;
        data = new int[capacity];
    }

    @Override
    void add(int item) {
        if (capacity == 0) {
            capacity = 1;
            data = new int[capacity];
        }

        if (capacity <= currentSize) {
            capacity *= 2;
            int[] newData = new int[capacity];
            System.arraycopy(data, 0, newData, 0, currentSize);
            data = newData;
        }

        data[currentSize] = item;
        currentSize++;
    }

    @Override
    int remove(int idx) throws NoSuchElementException {
        throwExceptionIfNotExists(idx);

        @SuppressWarnings("CheckStyle") int oldValue = data[idx];

        System.arraycopy(data, idx + 1, data, idx, (currentSize--) - idx - 1);

        if (capacity > currentSize * 4) {
            int[] newData = new int[capacity / 2];
            capacity = capacity / 2;
            System.arraycopy(data, 0, newData, 0, currentSize);
            data = newData;
        }

        return oldValue;
    }

    @Override
    int get(int idx) throws NoSuchElementException {
        throwExceptionIfNotExists(idx);
        return data[idx];
    }
}



