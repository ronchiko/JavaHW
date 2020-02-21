package expiramental;

public class LinkedList {
    private static class Node<T> {
        Node<T> next, prev;
        T value;

        public Node(T value){
            this.value = value;
            next = null;
            prev = null;
        }
    }

    private Node<Integer> head, tail;

    public LinkedList (){
        head = null;
        tail = null;
    }

    public void push(Integer value){
        Node node = new Node<>(value);
        if(head == null)
        {
            head = node;
            tail = node;
        }else{
            head.prev = node;
            node.next = head;
            head = node;
        }
    }
    public void append(Integer value){
        Node node = new Node<>(value);
        if(tail == null){
            head = node;
            tail = node;
        }else{
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
    }

    public void sort(){
        sort(head, tail);
    }

    private void sort(Node head, Node tail){
        if(head == tail) return;

        Node tailNext = tail, headNext = head;
        boolean x = true;
        while (tailNext != headNext){
            if(x) tailNext = tail.prev;
            else headNext = head.next;
            x = !x;
        }

        sort(head, tailNext);
        sort(tailNext.next, tail);

        headNext = tailNext;
        tailNext = tailNext.next;


    }

    public void t(){
        swap(head, tail);
    }

    private void swap(Node<Integer> a, Node<Integer> b){
        boolean aHead = false, bHead = false, aTail = false, bTail = false;
        Node<Integer> temp = new Node<>(a.value);
        temp.next = a.next;
        temp.prev = a.prev;

        if(a == head) aHead = true;
        if(a == tail) aTail = true;
        if(b == head) bHead = true;
        if(b == tail) bTail = true;

        a.value = b.value;
        a.next = b.next;
        a.prev = b.prev;

        if(bHead) head = a;
        if(bTail) tail = a;

        b.value = temp.value;
        b.prev = temp.prev;
        b.next = temp.next;

        if(aHead) head = b;
        if(aTail) tail = b;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        Node node = head;
        while (node != null){
            sb.append(node.value.toString() + ",");
            node = node.next;
        }
        if(sb.length() != 1) sb.delete(sb.length() - 1, sb.length());
        sb.append(']');
        return sb.toString();
    }
}
