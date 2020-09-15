public class MergeSortedArrayT88 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p = m - 1;
        int q = m + n - 1;
        int r = n - 1;
        while (true) {
            if (p < 0) {
                while (r >= 0) {
                    nums1[q] = nums2[r];
                    System.out.println(nums2[r]);
                    q--;
                    r--;
                }
                break;
            }
            if (r < 0) break;
            if (nums1[p] < nums2[r]) {
                nums1[q] = nums2[r];
                System.out.println(nums2[r]);
                r--;
                q--;
            } else {
                nums1[q] = nums1[p];
                System.out.println(nums1[p]);
                p--;
                q--;
            }
        }
    }
}
