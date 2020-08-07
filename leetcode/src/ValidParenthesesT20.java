import java.util.Stack;

public class ValidParenthesesT20 {
	public boolean isValid(String s) {
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < s.length(); i++) {
			char x = s.charAt(i);
			if (x == '(' || x == '[' || x == '{') {
				stack.push(x);
			}
			else {
				if (stack.empty()) return false;
				char y = stack.pop();
				switch (y){
					case '(':
						if (x != ')') return false;
						break;
					case '[':
						if (x != ']') return false;
						break;
					case '{':
						if (x != '}') return false;
						break;
				}
			}
		}
		if (stack.empty()) return true;
		else return false;
	}
}
