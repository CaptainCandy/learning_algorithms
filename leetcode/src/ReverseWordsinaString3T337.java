import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ReverseWordsinaString3T337 {
	public String reverseWords(String s) {
		// 可以用s.trim(), 也可以之后用.isEmpty()
		String[] slist = s.split(" ");
		ArrayList<String> slist2 = new ArrayList<String>();
		for (int i = 0; i < slist.length; i++) {
			if (!slist[i].isEmpty()) {
				String reverse = new StringBuffer(slist[i]).reverse().toString();
				slist2.add(reverse);
			}
		}
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < slist2.size(); i++) {
			res.append(slist2.get(i));
			if (i != slist2.size() - 1) {
				res.append(" ");
			}
		}
		String ans = res.toString();
		return ans;
	}
}
