package swExpert_1952_수영장;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int day, m1, m3, year;
	static int yearplan[] = new int[13];
	static int d[] = new int[13]; // k 달까지 결제한 금액의 최소값
	static int dp[][] = new int[13][5]; // k 달에서 할 수 있는 선택 무료, 1일권, 1달권, 3달권, 1년권

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int tc = Integer.parseInt(br.readLine());

		int t = 1;
		while (tc-- > 0) {

			st = new StringTokenizer(br.readLine());
			day = Integer.parseInt(st.nextToken());
			m1 = Integer.parseInt(st.nextToken());
			m3 = Integer.parseInt(st.nextToken());
			year = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());

			for (int i = 1; i <= 12; i++) {
				yearplan[i] = Integer.parseInt(st.nextToken());
			}

			// 4의 12제곱 2의 24제곱 1600만 가지수
			// 1일권 요금최대값 3000 3000*30*12 = 약 100만

			int re = f(1);

			System.out.println("#" + t++ + " " + re);
		}
	}

	static int f(int month) {
		if (month > 12) {
			return 0;
		} else if (month == 12) {
			dp[12][1] = yearplan[12] * day;
			dp[12][2] = m1;
			dp[12][3] = m3;
			dp[12][4] = year;

			return min(dp[12][1], dp[12][2], dp[12][3], dp[12][4]);
		}

		dp[month][1] = f(month + 1) + yearplan[month] * day;
		dp[month][2] = f(month + 1) + m1;
		dp[month][3] = f(month + 3) + m3;
		dp[month][4] = year;

		return min(dp[month][1], dp[month][2], dp[month][3], dp[month][4]);
	}

	static int min(int a, int b, int c, int d) {
		return Math.min(Math.min(Math.min(a, b), c), d);
	}
}
