package expiramental;

import expiramental.integral.IntegralUnit;
import expiramental.integral.IntegrationException;
import expiramental.queue.QueueSort;
import hw.unit4.core.Queue;

public class IntegrationTesting {

    private static final int ARRAY_SIZE = 1000000;

    public static void main(String[] args) throws IntegrationException {
        LinkedList list = new LinkedList();

        list.push(10);
        list.append(50);
        list.push(70);

        list.t();

        System.out.println(list);
    }
}
