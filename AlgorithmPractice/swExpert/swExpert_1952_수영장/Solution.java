package swExpert_1952_������;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int day, m1, m3, year;
	static int yearplan[] = new int[13];
	static int d[] = new int[13]; // k �ޱ��� ������ �ݾ��� �ּҰ�
	static int dp[][] = new int[13][5]; // k �޿��� �� �� �ִ� ���� ����, 1�ϱ�, 1�ޱ�, 3�ޱ�, 1���

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

			// 4�� 12���� 2�� 24���� 1600�� ������
			// 1�ϱ� ����ִ밪 3000 3000*30*12 = �� 100��

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
