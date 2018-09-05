package baekJoon_14501_퇴사;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * BaekJoon 14501 퇴사
 * DP 문제
 * @author Baek
 *
 */
public class Main {
	static int t[]; //상담 소요 기간
	static int p[]; //상담 비용
	static int dp[][]; //0번지 : 해당 날짜의 상담을 수락할경우, 1번지 : 수락하지 않을경우
	static int d[]; //N일쨰 최대 상담비용

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		t = new int[N + 1];
		p = new int[N + 1];

		dp = new int[N + 2][2];
		d = new int[N + 2];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			t[i] = Integer.parseInt(st.nextToken());
			p[i] = Integer.parseInt(st.nextToken());
		}

		if (N + t[N] > N + 1) {
			dp[N][0] = 0; // 해당일의 상담을 고를 경우
		} else {
			dp[N][0] = p[N]; // 해당일의 상담을 고를 경우
		}
		
		dp[N][1] = 0;

		d[N] = dp[N][0];
		d[N + 1] = 0;

		for (int i = N - 1; i >= 1; i--) { //날짜 계산을 위해 뒤에서부터 계산
			if (t[i] == 1) {
				dp[i][0] = d[i + 1] + p[i];
				d[i] = dp[i][0];
			} else {
				int index = i + t[i];

				if (index > N + 1) {
					dp[i][0] = d[i + 1];
				} else {
					dp[i][0] = d[index] + p[i];
				}
				
				dp[i][1] = d[i + 1];

				d[i] = dp[i][0] > dp[i][1] ? dp[i][0] : dp[i][1];
			}
		}

		System.out.println(d[1]);
	}
}
