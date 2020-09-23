public class CountPrimesT204 {
    public int countPrimes(int n) {
        int res = 0;
//        for (int i = 2; i < n; i++) {
//            if (isPrime(i)) res++;
//        }

        // 厄拉多塞筛法
        boolean[] b = new boolean[n];
        for (int i = 2; i < n; i++) {
            if (!b[i]) {
                res++;
                for (int j = i + i; j < n; j+=i) {
                    b[j] = true;
                }
            }
        }
        return res;
    }

    public boolean isPrime(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
