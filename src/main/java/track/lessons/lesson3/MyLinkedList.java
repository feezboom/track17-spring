package track.lessons.lesson3;

import java.util.NoSuchElementException;

/**
 * Должен наследовать List
 * Односвязный список
 */
public class MyLinkedList extends List implements Stack, Queue {

    /**
     * private - используется для сокрытия этого класса от других.
     * Класс доступен только изнутри того, где он объявлен
     * <p>
     * static - позволяет использовать Node без создания экземпляра внешнего класса
     */

    private Node headNode;

    private static class Node {
        Node next;
        int val;

        Node(Node next, int val) {
            this.next = next;
            this.val = val;
        }
    }

    public MyLinkedList() { }

    @Override
    public void add(int item) {
        Node lastNode = getIdxNode(currentSize - 1);
        if (lastNode == null) {
            headNode = new Node(null, item);
        } else {
            lastNode.next = new Node(null, item);
        }
        currentSize++;
    }



    public int remove(int idx) throws NoSuchElementException {
        throwExceptionIfNotExists(idx);
        currentSize--;
        if (idx == 0) {
            int oldValue = headNode.val;
            headNode = null;
            return oldValue;
        } else {
            Node prev = getIdxNode(idx - 1);
            int oldValue = prev.next.val;
            prev.next = prev.next.next;
            return oldValue;
        }
    }

    @Override
    public int get(int idx) throws NoSuchElementException {
        throwExceptionIfNotExists(idx);
        return getIdxNode(idx).val;
    }

    private Node getIdxNode(int idx) {
        int currentIndex = 0;
        Node currentNode = headNode;
        while (currentIndex++ < idx) {
            currentNode = currentNode.next;
        }
        return currentNode;
    }

        /* Stack methods */

    @Override
    public void push(int value) {
        add(value);
    }

    @Override
    public int pop() {
        return remove(currentSize - 1);
    }

    /* Queue methods */

    @Override
    public void enqueue(int value) {
        add(value);
    }

    @Override
    public int dequeue() {
        return remove(0);
    }

}
