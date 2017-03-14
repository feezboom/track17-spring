package track.lessons.lesson3;

/**
 *
 */
public class ListMain {

    public static void printList(List list) {
        for (int i = 0; i < list.size(); ++i) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MyArrayList myArrayList = new MyArrayList();

        myArrayList.add(5);
        myArrayList.add(6);
        myArrayList.add(7);
        myArrayList.add(8);
        myArrayList.add(9);
        myArrayList.add(10);

        printList(myArrayList);

        myArrayList.remove(0);
        printList(myArrayList);

        myArrayList.remove(0);
        printList(myArrayList);

        myArrayList.remove(0);
        printList(myArrayList);

        myArrayList.remove(0);
        printList(myArrayList);

        myArrayList.remove(1);
        printList(myArrayList);

        myArrayList.remove(0);
        printList(myArrayList);

        System.out.println(myArrayList.size());
    }
}
