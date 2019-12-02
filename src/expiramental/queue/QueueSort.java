package expiramental.queue;

import hw.unit4.core.Queue;

public class QueueSort {
    public static void sort(Queue<Integer> q){
        // Exit condition
        if(q.length() == 1) {
            return;
        }
        // Find the center of the queue
        int middlePoint = q.length() / 2;
        Queue<Integer> q1 = new Queue<>();
        Queue<Integer> q2 = new Queue<>();

        // Split the queue into 2 sub queues
        for (int i = 0; i < middlePoint; i++) {
            q1.insert(q.remove());
        }
        while (!q.isEmpty()) q2.insert(q.remove());

        // Sort the 2 sub queues
        sort(q1);
        sort(q2);

        // Merge the 2 sub queues
        while (!q1.isEmpty() || !q2.isEmpty()){
            if(q1.isEmpty()) q.insert(q2.remove());
            else if(q2.isEmpty()) q.insert(q1.remove());
            else if(q1.head() > q2.head()) q.insert(q1.remove());
            else q.insert(q2.remove());
        }
    }
}
