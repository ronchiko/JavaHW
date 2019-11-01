package hw.unit4.core;

public class Queue<T> {
    private static class Node<T> {
        Node<T> next;
        T value;

        public Node(T value, Node<T> next){
            this.value = value;
            this.next = next;
        }
    }

    private Node<T> head, tail;

    public Queue(){
        head = null;
        tail = null;
    }

    public boolean isEmpty(){
        return head == null;
    }

    public void insert(T value){
        if(head == null){
            head = new Node<T>(value, null);
            tail = head;
        }else {
            Node<T> temp = new Node<>(value, null);
            tail.next = temp;
            tail = temp;
        }
    }

    public T head(){
        return head.value;
    }

    public T remove(){
        Node<T> temp = head;
        head = head.next;
        return temp.value;
    }

    @Override
    public String toString(){
        StringBuilder output = new StringBuilder("[");

        Node<T> node = head;
        while (node != null){
            output.append(node.value).append(",");
            node = node.next;
        }

        if(isEmpty()) output.append(" ");

        output = new StringBuilder(output.substring(0, output.length() - 1) + "]");

        return output.toString();
    }
}
