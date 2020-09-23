public class ReverseBitsT190 {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
//        int res = 0, power = 31;
//        for (int i = 0; i < 32; i++) {
//            res += (n & 1) << power;
//            power--;
//            n >>= 1;
//        }
//        return res;

        int res = 0;
        for (int i = 0; i < 32; i++) {
            res = (res << 1) + (n & 1);
            n >>= 1;
        }
        return res;
    }
}
