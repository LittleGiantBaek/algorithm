package swExpert_5658_보물상자비밀번호2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Solution {

	static int N;
	static int K;
	static String line;
	static ArrayList<Integer> nums = new ArrayList<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int tc = Integer.parseInt(br.readLine());
		int t = 1;
		while (tc-- > 0) {
			nums.clear();
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			line = br.readLine();
			for (int i = 0; i < N / 4; i++) {
				for (int j = 0; j < 4; j++) {
					String hex = line.substring((j * (N / 4)), ((j + 1) * (N / 4)));
					int hexNum = Integer.parseInt(hex, 16);
					if (nums.contains(hexNum)) {
						continue;
					}
					nums.add(hexNum);
				}
				char s = line.charAt(line.length() - 1);
				line = s + line.substring(0, line.length() - 1);
			}
			nums.sort(new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					if (o1 > o2) {
						return -1;
					} else {
						return 1;
					}
				}
			});
			System.out.println("#" + t + " " + nums.get(K - 1));
			t++;
		}
	}

}
