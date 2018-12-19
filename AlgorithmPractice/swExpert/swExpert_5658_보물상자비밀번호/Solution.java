package swExpert_5658_보물상자비밀번호;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Solution {

	static int N, K;
	static ArrayList<String> numList = new ArrayList<>();
	static String s1, s2, s3, s4;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int tc = Integer.parseInt(br.readLine());
		int t = 1;
		while (tc-- > 0) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			String s = br.readLine();
			int answer;
			numList.clear();
			s1 = s.substring(0, N / 4);
			s2 = s.substring(N / 4, (N * 2 / 4));
			s3 = s.substring((N / 4) * 2, (N / 4) * 3);
			s4 = s.substring((N / 4) * 3, N);

			inList(s1);
			inList(s2);
			inList(s3);
			inList(s4);

			answer = solve();
			System.out.println("#" + t + " " + answer);
			t++;
		}
	}

	public static int solve() {
		String s;
		for (int i = 0; i < N - 1; i++) {
			s = s1.charAt(s1.length() - 1) + "";
			s1 = s1.substring(0, s1.length() - 1);
			s2 = s.concat(s2);

			s = s2.charAt(s2.length() - 1) + "";
			s2 = s2.substring(0, s2.length() - 1);
			s3 = s.concat(s3);

			s = s3.charAt(s3.length() - 1) + "";
			s3 = s3.substring(0, s3.length() - 1);
			s4 = s.concat(s4);

			s = s4.charAt(s4.length() - 1) + "";
			s4 = s4.substring(0, s4.length() - 1);
			s1 = s.concat(s1);

			inList(s1);
			inList(s2);
			inList(s3);
			inList(s4);
		}

		ArrayList<Integer> Answer = StringToNum();
		Collections.sort(Answer, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if (o1 > o2) {
					return -1;
				} else {
					return 1;
				}
			}
		});
		return Answer.get(K - 1);
	}

	public static void inList(String s) {
		if (!numList.contains(s)) {
			numList.add(s);
		}
	}

	public static ArrayList<Integer> StringToNum() {
		ArrayList<Integer> re = new ArrayList<>();
		for (int i = 0; i < numList.size(); i++) {
			re.add(Integer.parseInt(numList.get(i), 16));
		}
		return re;
	}

}
