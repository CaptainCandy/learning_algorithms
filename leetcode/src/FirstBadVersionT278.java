public class FirstBadVersionT278 {
    public int firstBadVersion(int n) {
        if (n == 1) return 1;
        int left = 1, right = n;
        int mid = 1;
//        while (left < right) {
//            mid = left + (right - left) / 2;
//            if (isBadVersion(mid)) {
//                right = mid;
//            } else {
//                left = mid + 1;
//            }
//        }
        return left;
    }
}
