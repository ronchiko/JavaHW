package expiramental;

import hw.unit4.core.Queue;

public class Sorting {

    /**
     * Merge sort wrapper method
     * @param a int array to sort
     */
    public static void sort(int[] a){
        sort(a, 0, a.length);
    }

    /**
     * Checks if the array is sorted
     * @param a the array
     * @return true if sorted, false otherwise
     */
    public static boolean isSorted(int[] a){
        for (int i = 0; i < a.length - 1; i++) {
            if(a[i] > a[i + 1]) return false;
        }
        return true;
    }

    /**
     * merge sort implementation
     * @param a array to sort
     * @param s start of the array
     * @param e end of the array
     * @return a sort queue with the items
     */
    private static Queue<Integer> sort(int[] a, int s, int e){
        if(s + 1 == e) return new Queue<Integer>(){
            {
                insert(a[s]);
            }
        };
        // Find the middle point
        int m = (s + e) / 2;
        // Sort the 2 sub arrays
        Queue<Integer> a1 = sort(a, s, m), a2 = sort(a, m, e), out = new Queue<>();
        int i = s;
        // Merge the queues into the array
        while (!a1.isEmpty() || !a2.isEmpty()){
            int item;
            if(a1.isEmpty()) item = a2.remove();
            else if(a2.isEmpty()) item = a1.remove();
            else if(a2.head() > a1.head()) item = a1.remove();
            else item = a2.remove();
            out.insert(item);
            a[i] = item;
            i++;
        }

        return out;
    }
}
