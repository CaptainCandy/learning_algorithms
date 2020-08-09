import java.util.ArrayList;

public class AtoiT8 {
    public int myAtoi(String str) {
        str = str.trim();
        ArrayList<Integer> res = new ArrayList<Integer>();
        int i = 0;
        boolean isNeg = false;
        if (str.equals("")) {
            return 0;
        }
        if (str.charAt(0) == '-' ) {
            if (str.length() == 1) {
                return 0;
            }
            else if (str.charAt(1) - '0' >=0&&str.charAt(1) - '0'<=9) {
                i++;
                isNeg = true;
            }
            else {
                return 0;
            }
        }
        else if (str.charAt(0) == '+') {
            if (str.length() == 1) {
                return 0;
            }
            else if (str.charAt(1) - '0' >=0&&str.charAt(1) - '0'<=9) {
                i++;
            }
            else {
                return 0;
            }
        } else if (!(str.charAt(0) - '0' >= 0 && str.charAt(0) - '0' <= 9)) {
            return 0;
        }
        while (i < str.length()) {
            if (str.charAt(i) - '0' >= 0 && str.charAt(i) - '0' <= 9) {
                res.add(str.charAt(i) - '0');
            } else {
                break;
            }
            i++;
        }
        int ans = 0;
        for (int j = 0; j < res.size(); j++) {
            if (isNeg) {
                int pop = -res.get(j);
                if (ans < Integer.MIN_VALUE/10 || (ans == Integer.MIN_VALUE / 10 && pop < -8)) return Integer.MIN_VALUE;
                ans = ans * 10 + pop;
            }
            else {
                int pop = res.get(j);
                if (ans > Integer.MAX_VALUE/10 || (ans == Integer.MAX_VALUE / 10 && pop > 7)) return Integer.MAX_VALUE;
                ans = ans * 10 + pop;
            }
        }

        return ans;
    }
}
