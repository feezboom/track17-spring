package track.lessons.lesson3;

/**
 *
 * Created by avk on 14.03.17.
 *
 */

// Стек - структура данных, удовлетворяющая правилу Last IN First OUT
interface Stack {

    void push(int value); // положить значение наверх стека

    int pop(); // вытащить верхнее значение со стека
}