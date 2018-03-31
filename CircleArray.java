package com.hzflk.opcommon.utils;

/**
 * @author liuwei liuwei@flksec.com
 * @version 1.0
 * @date 2018/2/27 0027
 */
public class CircleArray<T> {

    private Node[] ns;
    private Node current, head, tail;

    public CircleArray(T[] arrs){
        ns = new Node[arrs.length];
        int index = 0;
        for (T arr : arrs) {
            Node node = new Node<T>(index, arr);
            ns[index] = node;
            if (index > 0){
                ns[index - 1].setNext(node);
            }
            if (index == arrs.length - 1){
                node.setNext(ns[0]);
            }
            index++;
        }
        this.head = ns[0];
        this.current = ns[0];
        this.tail = ns[arrs.length - 1];
    }


    public Node next(){
        current = this.current.getNext();
        return current;
    }

    public T nextValue(){
        current = this.next();
        return (T)current.getData();
    }


    public T value(){
        return (T)current.getData();
    }


    private class Node<T> {

        int index;

        Node next;

        T data;

        public Node(int index){
            this(index, null);
        }

        public Node(int index, T data){
            this.index = index;
            this.data = data;
        }

        public void setNext(Node next){
            this.next = next;
        }

        public Node getNext(){
            return this.next;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }
    }


}
