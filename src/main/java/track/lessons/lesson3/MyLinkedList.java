package track.lessons.lesson3;

import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Stack;

/**
 * Должен наследовать List
 * Односвязный список
 */
public class MyLinkedList extends List {

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

    public MyLinkedList() {
        this.headNode = null;
        this.currentSize = 0;
    }

    @Override
    void add(int item) {
        Node newHead = new Node(this.headNode, item);
        this.headNode = newHead;
        currentSize++;
    }

    @Override
    int remove(int idx) throws NoSuchElementException {
        throwExceptionIfNotExists(idx);
        currentSize--;
        if (idx == 0) {
            int oldValue = this.headNode.val;
            this.headNode = null;
            return oldValue;
        } else {
            Node prev = getIdxNode(idx - 1);
            int oldValue = prev.next.val;
            prev.next = prev.next.next;
            return oldValue;
        }
    }

    @Override
    int get(int idx) throws NoSuchElementException {
        throwExceptionIfNotExists(idx);
        return getIdxNode(idx).val;
    }

    private Node getIdxNode(int idx) {
        int currentIndex = 0;
        Node currentNode = this.headNode;
        while (currentIndex++ < idx) {
            assert currentNode.next != null;
            currentNode = currentNode.next;
        }
        return currentNode;
    }

    @Override
    int size() {
        return currentSize;
    }
}
