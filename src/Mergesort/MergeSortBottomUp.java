package Mergesort;

public class MergeSortBottomUp {
	private static Comparable[] aux;

	private static void sort(Comparable[] a) {
		int N = a.length;
		aux = new Comparable[N];
		for (int sz = 1; sz < N; sz = sz+sz) {
			for (int i = 0; i < N - sz; i += sz + sz) {
				merge(a, aux, i, i + sz - 1, Math.min(i + sz + sz -1, N - 1));
			}
		}
	}

	private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
		assert isSorted(a, lo, mid);
		assert isSorted(a, mid+1, hi);

		for (int i = lo; i <= hi; i++) {
			aux[i] = a[i];
		}

		int j = lo;
		int k = mid+1;
		for (int i = lo; i <= hi; i++) {
			if (j > mid) a[i] = aux[k++];
			else if (k > hi) a[i] = aux[j++];
			// 等于的时候用左边的元素先放，这样才是stable的排序，否则会变成instable的
			else if (less(aux[k], aux[j])) a[i] = aux[k++];
			else a[i] = aux[j++];
		}

		assert isSorted(a, lo, hi);
	}

	private static boolean isSorted(Comparable[] a, int lo, int hi) {
		for (int i = 1; i < a.length; i++)
			if (less(a[i], a[i-1])) return false;
		return true;
	}

	private static boolean less(Comparable a, Comparable b) {
		return a.compareTo(b) < 0;
	}
}
