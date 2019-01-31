package edu.isu.cs.cs3308.structures.impl;

import edu.isu.cs.cs3308.Node;

/**
 * @author Steve Coburn
 * DueDate: 27 January 2019
 * ISUCourse: CS3308
 * Instructor: Isaac Griffith
 * Project: campaign01
 * Description: This class extends a SinglyLinkedList to ensure the end points to the beginning
 */

public class CircularlyLinkedList<E> extends SinglyLinkedList<E> {

    // CONSTURCTOR
    public CircularlyLinkedList() {
    }

    // METHODS
    @Override
    public void addLast(E element) {
        if (element != null) {
            Node<E> newest = new Node<>(element);
            newest.setNext(head);
            if (isEmpty()) {
                head = newest;
                newest.setNext(head);
            } else {
                tail.setNext(newest);
            }
            tail = newest;
            size++;
        }
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }
        tail.setNext(head.getNext());
        E temp = head.getData();
        head = head.getNext();
        size--;
        if (size == 0) {
            tail = null;
        }
        return temp;
    }

    @Override
    @SuppressWarnings("Duplicates")
    public E removeLast() {
        if (isEmpty()) {
            return null;
        }
        E toRemove = tail.getData();
        tail = head;
        for (int i = 1; i < size - 1; i++) {
            tail = tail.getNext();
        }
        tail.setNext(head);
        size--;
        return toRemove;
    }
}
