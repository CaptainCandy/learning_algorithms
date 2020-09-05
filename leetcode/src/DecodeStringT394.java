import java.util.*;

public class DecodeStringT394 {
    public String decodeString(String s) {
        Stack<String> str = new Stack<String>();
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (cur - '0' >= 0 && cur - '0' < 10) {
                Integer digit = cur - '0';
                while (s.charAt(i + 1) - '0' >= 0 && s.charAt(i + 1) - '0' < 10) {
                    digit = digit * 10 + (s.charAt(i + 1) - '0');
                    i++;
                }
                System.out.println(digit);
                str.push(digit.toString());
            } else if (cur == ']') {
                String c = str.pop();
                List<String> q = new ArrayList<String>();
                while (!c.equals("[")) {
                    q.add(c);
                    c = str.pop();
                }
                int n = Integer.parseInt(str.pop());
                Collections.reverse(q);
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < q.size(); k++) {
                        str.push(q.get(k));
                    }
                }
            } else {
                System.out.println(cur);
                str.push(Character.toString(cur));
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.size(); i++) {
            sb.append(str.get(i));
        }
        return sb.toString();
    }
}
