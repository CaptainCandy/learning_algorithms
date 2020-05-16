package ElementarySorts;

public class ShellSort {

    public static void sort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        // use the sequence of 1, 4, 13, 40, 121, 364
        while (h < N / 3) h = 3 * h + 1;
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h; j -= h) {
                    if (less(a[j], a[j-h])) swap(a, j, j-h);
                }
                h /= 3;
            }
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void swap(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
