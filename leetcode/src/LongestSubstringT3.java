import java.util.LinkedList;
import java.util.Queue;

public class LongestSubstringT3 {
	public int lengthOfLongestSubstring(String s) {
		Queue<Character> q = new LinkedList<Character>();
		int max = 0;
		for (int i = 0; i < s.length(); i++) {
			if (q.contains(s.charAt(i))) {
				while (q.remove() != s.charAt(i)) continue;
				q.add(s.charAt(i));
			}
			else {
				q.add(s.charAt(i));
				if (q.size() > max) max = q.size();
			}
		}

		return max;
	}
}
