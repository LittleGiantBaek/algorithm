package kakaoblind_2019_ex1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class Solution {
	public static String[] solution(String[] record) {
		String[] answer = {};
		StringTokenizer st;
		Map<String, String> uidNick = new HashMap<>();
		ArrayList<String> inOutlist = new ArrayList<>();

		String uid = "UUUU";
		String msg = "MMMM";
		String nick = "NNNN";

		for (int i = 0; i < record.length; i++) {
			st = new StringTokenizer(record[i]);
			msg = st.nextToken();
			uid = st.nextToken();
			if (st.hasMoreTokens()) {
				nick = st.nextToken();
			}

			if (!uidNick.containsKey(uid)) {
				uidNick.put(uid, nick);
			}

			if (msg.equals("Enter") || msg.equals("Change")) {
				uidNick.replace(uid, nick);
			}

			if (msg.equals("Enter") || msg.equals("Leave")) {
				inOutlist.add(uid + "," + msg);
			}
		}

		answer = new String[inOutlist.size()];

		String ans;

		for (int i = 0; i < inOutlist.size(); i++) {
			st = new StringTokenizer(inOutlist.get(i), ",");
			uid = st.nextToken();
			msg = st.nextToken();
			if (msg.equals("Enter")) {
				ans = uidNick.get(uid) + "´ÔÀÌ µé¾î¿Ô½À´Ï´Ù.";
			} else {
				ans = uidNick.get(uid) + "´ÔÀÌ ³ª°¬½À´Ï´Ù.";
			}
			answer[i] = ans;
		}

		return answer;
	}
}