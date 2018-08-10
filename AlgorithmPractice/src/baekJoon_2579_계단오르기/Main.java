package baekJoon_2579_계단오르기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int stair[] = new int[N];
		int dp[][] = new int[N][3];

		for (int i = 0; i < N; i++) {
			stair[i] = Integer.parseInt(br.readLine());
		}

		// dp[x][0] = x계단을 안건널경우
		// dp[x][1] = x계단을 건너고 x-1 계단을 안건넌 경우
		// dp[x][2] = x계단,x-1계단을 건너고 x-2계단을 안건넌경우

		dp[0][0] = 0;
		dp[0][1] = stair[0];

		for (int i = 1; i < N; i++) {
			dp[i][0] = Math.max(dp[i - 1][1], dp[i - 1][2]);
			dp[i][1] = dp[i - 1][0] + stair[i];
			dp[i][2] = dp[i - 1][1] + stair[i];
		}

		for (int i = 0; i < N; i++) {
			System.out.println(dp[i][0] + " " + dp[i][1] + " " + dp[i][2] + "  ssss:" + stair[i]);
		}
		System.out.println(Math.max(dp[N - 1][1], dp[N - 1][2]));
	}
}
