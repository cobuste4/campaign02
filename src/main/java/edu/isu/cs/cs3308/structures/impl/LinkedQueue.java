package edu.isu.cs.cs3308.structures.impl;

import edu.isu.cs.cs3308.structures.Queue;

/**
 * @author Steve Coburn
 * DueDate: 3 February 2019
 * ISUCourse: CS3308
 * Instructor: Isaac Griffith
 * Project: campaign02
 * Description: This class impliments a queue using a doubly linked list
 */

public class LinkedQueue<E> implements Queue<E> {
    private DoublyLinkedList<E> DLL;

    // Constructor
    public LinkedQueue() {
        DLL = new DoublyLinkedList<>();
    }

    @Override
    public int size() {
        return DLL.size();
    }

    @Override
    public boolean isEmpty() {
        return DLL.isEmpty();
    }

    @Override
    // Similar to a stack push, only at the end of a list
    public void offer(E element) {
        if (element != null) {
            DLL.addLast(element);
        }
    }

    @Override
    public E peek() {
        return DLL.first();
    }

    @Override
    // Same as stack pop
    public E poll() {
        return DLL.removeFirst();
    }

    @Override
    public void printQueue() {
        DLL.printList();
    }

    @SuppressWarnings("Duplicates")
    public void transfer(Queue<E> into) {
        if (into != null) {
            LinkedStack<E> stack = new LinkedStack<>();
            int sz = this.size();
            for (int i = 0; i < sz; i++) {
                stack.push(this.poll());
            }
            sz = stack.size();
            for (int i = 0; i < sz; i++) {
                into.offer(stack.pop());
            }
        }
    }

    @SuppressWarnings("Duplicates")
    public void reverse() {
        LinkedStack<E> stack = new LinkedStack<>();
        int sz = this.size();
        for (int i = 0; i < sz; i++) {
            stack.push(this.poll());
        }
        sz = stack.size();
        for (int i = 0; i < sz; i++) {
            this.offer(stack.pop());
        }
    }

    public void merge(Queue<E> from) {
        if (from != null) {
            int sz = from.size();
            for (int i = 0; i < sz; i++) {
                E temp = from.poll();
                this.offer(temp);
                from.offer(temp);
            }
        }
    }
}
