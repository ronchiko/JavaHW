package hw.unit4.core;

public class Stack<T> {
    private static class Node<T>
    {
        Node<T> next;
        T value;

        public Node(T value, Node<T> next){
            this.value = value;
            this.next = next;
        }
    }

    private Node<T> head;

    public Stack(){
        head = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void push(T value){
        head = new Node<>(value, head);
    }

    public T pop(){
        Node<T> temp = head;
        head = head.next;
        return temp.value;
    }

    public T top(){
        return head.value;
    }

    @Override
    public String toString(){
        StringBuilder output = new StringBuilder("[");

        Node<T> node = head;
        while (node != null){
            output.append(String.format("%s,", node.value));
            node = node.next;
        }

        if(isEmpty()) output.append(',');

        output = new StringBuilder(output.substring(0, output.length() - 1) + "]");
        return output.toString();
    }
}
