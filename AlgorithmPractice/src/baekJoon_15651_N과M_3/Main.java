package baekJoon_15651_N°úM_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;

	public static void dfs(String str) {
		if (str.length() == M) {
			System.out.println(str);
			return;
		}
		for (int i = 1; i <= N; i++) {
			str += i + " ";
			dfs(str);
			str = str.substring(0, str.length() - 2);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		M = M * 2;

		dfs("");
	}
}
