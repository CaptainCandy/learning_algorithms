package Mergesort;

import ElementarySorts.InsertionSort;

public class MergeSort {
	// 当数组元素个数足够小的时候，用简单的算法能提高效率
	private static final int CUTOFF = 7;
	private static Comparable[] aux;

	public static void sort(Comparable[] a) {
		aux = new Comparable[a.length];
		sort(a, aux, 0, a.length - 1);
	}

	private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
		if (lo >= hi) return;
		if (hi <= lo + CUTOFF - 1) {
			InsertionSort.sort(a, lo, hi);
			return;
		}
		int mid = lo + (hi - lo) / 2;
		sort(a, aux, lo, mid);
		sort(a, aux, mid+1, hi);
		// 如果已经排好序了就可以提前结束
		if (less(a[mid], a[mid+1])) return;
		merge(a, aux, lo, mid, hi);
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
			else if (less(aux[j], aux[k])) a[i] = aux[j++];
			else a[i] = aux[k++];
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
