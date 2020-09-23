import java.util.Stack;

public class ValidParenthesisStringT678 {
    public boolean checkValidString(String s) {
        // 双栈法
        Stack<Integer> left = new Stack<Integer>(), star = new Stack<Integer>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') left.push(i);
            else if (s.charAt(i) == '*') star.push(i);
            else {
                if (!left.empty()) left.pop();
                else if (!star.empty()) star.pop();
                else return false;
            }
        }
        while (!left.empty() && !star.empty()) {
            if (left.pop() > star.pop()) return false;
        }
        return left.empty();
    }

    private boolean checkDFS(String s, int start, int count) {
        if (count < 0) return false;
        for (int i = start; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') count++;
            else if (c == ')') {
                if (count == 0) return false;
                count--;
            }
            else return checkDFS(s, i + 1, count + 1) ||
                    checkDFS(s, i + 1, count) ||
                    checkDFS(s, i + 1, count - 1);
        }
        return count == 0;
    }

    private boolean checkMinMax(String s) {
        int min = 0, max = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                min++;
                max++;
            } else if (c == ')') {
                if (min > 0) min--;
                if (max == 0) return false;
                if (max > 0) max--;
            } else {
                if (min > 0) min--; // *当右括号
                max++; // *当左括号
            }
        }
        return min == 0;
    }

    private boolean checkDoubleTraverse(String s) {
        int l = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != ')') l++;
            else {
                if (l == 0) return false;
                l--;
            }
        }
        if (l == 0) return true;

        int r = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c != '(') {
                r++;
            }
            else {
                if (r == 0) return false;
                r--;
            }
        }
        return true;
    }
}
