package expiramental;

import expiramental.inegral.IntegralUnit;

public class IntegrationTesting {

    private static final int ARRAY_SIZE = 1000000;

    public static void main(String[] args) {

        int[] array = new int[ARRAY_SIZE];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int)(Math.random() * (ARRAY_SIZE * 2)) - ARRAY_SIZE;
        }
        System.out.println("Sort started");
        Sorting.sort(array);
        System.out.println("Sorting ended");

        System.out.println(Sorting.isSorted(array));

        // IntegralUnit unit = new IntegralUnit("x^2 + (x + 9)^3");
        // unit.integrate(0, 2);
    }
}
