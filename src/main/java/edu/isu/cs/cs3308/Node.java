package edu.isu.cs.cs3308;

/**
 * @author Steve Coburn
 * ISUCourse: CS3308
 * Instructor: Isaac Griffith
 * Description: This class is the basis for all list structures
 */

public class Node<E> {
    private E contents;
    private Node<E> next;
    private Node<E> prev;

    public Node(E data) {
        contents = data;
        next = null;
        prev = null;
    }

    // Getters and setters

    public Node<E> getNext() {
        return next;
    }

    public void setNext(Node<E> n) {
        next = n;
    }

    public Node<E> getPrev() { return prev; }

    public void setPrev(Node<E> p) { prev = p; }

    public E getData() {
        return contents;
    }

    public void setData(E element) {
        contents = element;
    }
}
