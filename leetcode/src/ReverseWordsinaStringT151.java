import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ReverseWordsinaStringT151 {
	public String reverseWords(String s) {
		// 可以用s.trim(), 也可以之后用.isEmpty()
		String[] slist = s.split(" ");
		ArrayList<String> slist2 = new ArrayList<String>();
		for (int i = 0; i < slist.length; i++) {
			if (!slist[i].isEmpty()) slist2.add(slist[i]);
		}
		StringBuilder res = new StringBuilder();
		Collections.reverse(slist2);
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
