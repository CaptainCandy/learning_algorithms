import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class LetterCombinationsPhoneNumberT17 {
	private HashMap<String, String> hash = new HashMap<String, String>() {
		{
			put("2", "abc");
			put("3", "def");
			put("4", "ghi");
			put("5", "jkl");
			put("6", "mno");
			put("7", "pqrs");
			put("8", "tuv");
			put("9", "wxyz");
		}
	};

	private List<String> res = new ArrayList<>();

	private void findCombination(String digits, int idx, String s) {
		if (idx == digits.length()) {
			res.add(s);
			return;
		}

		String key = digits.substring(idx, idx + 1);
		String cs = hash.get(key);
		for (int i = 0; i < cs.length(); i++) {
			findCombination(digits, idx+1, s + cs.charAt(i));
		}
	}

	public List<String> letterCombinations(String digits) {
		if (digits.equals(""))
			return res;

		findCombination(digits, 0, "");
		return res;
	}
}
