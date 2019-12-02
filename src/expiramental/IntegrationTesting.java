package expiramental;

import expiramental.integral.IntegralUnit;
import expiramental.integral.IntegrationException;
import expiramental.queue.QueueSort;
import hw.unit4.core.Queue;

public class IntegrationTesting {

    private static final int ARRAY_SIZE = 1000000;

    public static void main(String[] args) throws IntegrationException {

        Queue<Integer> q = new Queue<>();

        q.insert(-900);
        q.insert(800);
        q.insert(89);
        q.insert(-83);
        q.insert(60);

        QueueSort.sort(q);

        System.out.println(q);

        IntegralUnit unit = new IntegralUnit("x^2 + 3 * x");
        // unit.integrate(0, 2);
    }
}
