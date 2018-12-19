package baekJoon_1759_암호만들기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static int L, C;
	static char vo[] = { 'a', 'i', 'o', 'u', 'e' }; // 모음
	static ArrayList<Character> voList = new ArrayList<>();
	static ArrayList<Character> charList = new ArrayList<>();
	static boolean checked[];
	static int conCnt;
	static int voCnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		L = Integer.parseInt(st.nextToken()); // 암호길이
		C = Integer.parseInt(st.nextToken()); // 입력 숫자 갯수
		checked = new boolean[C];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			char c = st.nextToken().charAt(0);
			if (isVo(c)) {
				voList.add(c);
			}
			charList.add(c);
		}
		Collections.sort(voList);
		Collections.sort(charList);

		dfs("", 0);

	}

	public static void dfs(String str, int num) {
		if (str.length() == L) {
			voCnt = 0;
			conCnt = 0;
			if (isPassWord(str)) {
				System.out.println(str);
			}
			return;
		}
		for (int i = num; i < C; i++) {
			if (checked[i] == false) {
				checked[i] = true;
				str += charList.get(i);
				dfs(str, i);
				checked[i] = false;
				str = str.substring(0, str.length() - 1);
			}
		}
	}

	public static boolean isVo(char s) {
		for (int i = 0; i < 5; i++) {
			if (s == vo[i]) {
				return true;
			}
		}
		return false;
	}

	public static boolean isPassWord(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (voList.contains(str.charAt(i))) {
				voCnt++;
			} else {
				conCnt++;
			}
		}
		if (voCnt >= 1 && conCnt >= 2) {
			return true;
		}
		return false;
	}
}
