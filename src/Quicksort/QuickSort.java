package Quicksort;

import ElementarySorts.InsertionSort;
import edu.princeton.cs.algs4.StdRandom;

public class QuickSort {
	private static final int CUTOFF = 10;

	public static void sort(Comparable[] a) {
		StdRandom.shuffle(a);
		sort(a, 0, a.length - 1);
	}

	public static void sort3way(Comparable[] a) {
		StdRandom.shuffle(a);
		sort3way(a, 0, a.length - 1);
	}

	private static void sort(Comparable[] a, int lo, int hi) {
		if (hi <= lo + CUTOFF - 1) {
			InsertionSort.sort(a, lo, hi);
			return;
		}
		int j = partition(a, lo, hi);
		sort(a, lo, j-1);
		sort(a, j+1, hi);
	}

	private static void sort3way(Comparable[] a, int lo, int hi) {
		if (hi <= lo + CUTOFF - 1) {
			InsertionSort.sort(a, lo, hi);
			return;
		}
		int lt = lo;
		int gt = hi;
		Comparable p = a[lo];
		int i = lo + 1;
		while (i <= gt) {
			int r = p.compareTo(a[i]);
			if (r > 0) swap(a, i++, lt++);
			else if (r < 0) swap(a, i, gt--);
			else i++;
		}
		sort3way(a, lo, lt - 1);
		sort3way(a, gt + 1, hi);
	}

	private static int partition(Comparable[] a, int lo, int hi) {
		int i = lo;
		int j = hi + 1;

		while (true) {
			while (less(a[++i], a[lo]))
				if (i == hi) break;
			while (less(a[lo], a[--j]))
				continue;
			if (i >= j) break;
			swap(a, i, j);
		}
		swap(a, lo, j);
		return j;
	}

	private static void swap(Comparable[] a, int i, int j) {
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}
}
