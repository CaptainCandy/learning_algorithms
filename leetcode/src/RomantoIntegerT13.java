import java.util.HashMap;

public class RomantoIntegerT13 {
    public int romanToInt(String s) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("I", 1);
        map.put("V", 5);
        map.put("X", 10);
        map.put("L", 50);
        map.put("C", 100);
        map.put("D", 500);
        map.put("M", 1000);

        int pre = map.get(s.substring(0, 1));
        int sum = 0;
        for (int i = 1; i < s.length(); i++) {
            int cur = map.get(s.substring(i, i + 1));
            if (pre < cur) {
                sum -= pre;
            } else {
                sum += pre;
            }
            pre = cur;
        }
        sum += pre;
        return sum;
    }
}
