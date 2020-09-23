public class HammingDistanceT461 {
    public int hammingDistance(int x, int y) {
        int n = x ^ y;
        return hammingWeight(n);
    }

    public int hammingWeight(int n) {
        int res = 0;
        int mask = 1;
        for (int i = 0; i < 32; i++) {
            if ((n & mask) != 0) res++;
            mask <<= 1;
        }
        return res;
    }
}
