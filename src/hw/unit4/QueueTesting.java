package hw.unit4;

import hw.unit4.core.BinNode;
import hw.unit4.core.Queue;

public class QueueTesting {
    public static void main(String[] args) {
        Queue<Integer> q = new Queue<>();
        BinNode<Integer> b1 = new BinNode<>(10, null, null);

        q.insert(10);
        q.insert(20);
        q.insert(30);

        System.out.println(b1);
        System.out.println(q.remove());
        System.out.println(q.head());
        System.out.println(q);
    }
}
