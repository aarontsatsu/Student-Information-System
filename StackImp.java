/**
 * @author Aaron Tsatsu Tamakloe
 * @author Mercy Chimezie
 * @author Princess Asante
 */

import java.util.LinkedList;

/***
 * uses the java utility LinkedList to implement a stack
 * @param <E> generic type class implementation
 */
public class StackImp<E> {
    private LinkedList<E> list = new LinkedList();
    private int t = -1;

    public StackImp() {
    }

    /***
     *
     * @return number of elements in the stack
     */
    public int size() {
        return list.size();
    }

    /***
     *
     * @return True or false if the stack is empty or not
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /***
     * Adds an element at the top of the stack
     * @param data element to be added
     */
    public void push(E data) {
        list.add(data);
    }

    /***
     *
     * @return the first element on top of the stack
     */
    public E top() {
        if (list.size() == 0) {
            return null;
        } else if (list.size() == 1) {
            E checker = list.getFirst();
            return checker;
        }
        E getter = list.getLast();
        return getter;
    }

    /***
     *
     * @return the element that was removed from the top of the stack
     */
    public E pop() {
        if (list.size() == 0) {
            return null;
        } else if (list.size() == 1) {
            E remover = list.removeFirst();
            return remover;
        }
        E getter = list.getLast();
        list.remove(getter);
        return getter;
    }

    /***
     * Converts the stack to a string to see elements when printed
     * @return elements in the stack
     */
    public String toString() {
        return list.toString();
    }
}