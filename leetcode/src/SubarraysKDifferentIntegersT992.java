import java.util.HashMap;
import java.util.Map;

public class SubarraysKDifferentIntegersT992 {
    public int subarraysWithKDistinct(int[] A, int K) {
        Window w1 = new Window();
        Window w2 = new Window();
        int p = 0, q = 0;
        int count = 0;

        for (int i = 0; i < A.length; i++) {
            w1.add(A[i]);
            w2.add(A[i]);
            while (w1.diff > K) {
                w1.remove(A[p]);
                p++;
            }
            while (w2.diff > K - 1) {
                w2.remove(A[q]);
                q++;
            }
            count += q - p;
        }
        return count;
    }
}

class Window {
    private Map<Integer, Integer> map;
    public int diff;

    public Window() {
        map = new HashMap<>();
        diff = 0;
    }

    public void add(int x) {
        map.put(x, map.getOrDefault(x, 0) + 1);
        if (map.get(x) == 1) diff++;
    }

    public void remove(int x) {
        map.put(x, map.get(x) - 1);
        if (map.get(x) == 0) diff--;
    }
}