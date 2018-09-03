package kakao_2018_blind_모의5;

public class Solution {
	class DP {
		int index;
		int answer;

		public DP(int index, int answer) {
			this.index = index;
			this.answer = answer;
		}
	}

	public int solution(int[][] land) {
		int answer = 0;
		int top[] = new int[3];
		DP dp[][] = new DP[land.length][4];
		top = pickTop(land[0]);

		// dp[A][B] = A번쨰줄 값들 중 B index의 값을 선택할 때의 최대값
		dp[0][0] = new DP(0, land[0][0]);
		dp[0][1] = new DP(1, land[0][1]);
		dp[0][2] = new DP(2, land[0][2]);
		dp[0][3] = new DP(3, land[0][3]);

		DP val[][] = new DP[land.length][2]; // N번째 줄에서의 최대값 ,2번쨰값과 그 index
		val[0][0] = new DP(top[0], land[0][top[0]]);
		val[0][1] = new DP(top[1], land[0][top[1]]);

		for (int i = 1; i < land.length; i++) {
			for (int j = 0; j < 4; j++) {
				if (val[i - 1][0].index == j) {
					dp[i][j] = new DP(j, val[i - 1][1].answer + land[i][j]);
				} else {
					dp[i][j] = new DP(j, val[i - 1][0].answer + land[i][j]);
				}
			}

			val[i][0] = findMax(dp[i][0], dp[i][1], dp[i][2], dp[i][3]);
			switch (val[i][0].index) {
			case 0:
				val[i][1] = findMax(new DP(0, -1), dp[i][1], dp[i][2], dp[i][3]);
				break;
			case 1:
				val[i][1] = findMax(dp[i][0], new DP(1, -1), dp[i][2], dp[i][3]);
				break;
			case 2:
				val[i][1] = findMax(dp[i][0], dp[i][1], new DP(2, -1), dp[i][3]);
				break;
			case 3:
				val[i][1] = findMax(dp[i][0], dp[i][1], dp[i][2], new DP(3, -1));
				break;
			}
		}

		answer = val[land.length - 1][0].answer;

		return answer;
	}

	public DP findMax(DP a, DP b, DP c, DP d) {
		DP ret;

		int max = 0;
		int index = 0;

		max = a.answer > b.answer ? a.answer : b.answer;
		index = a.answer > b.answer ? a.index : b.index;

		max = max > c.answer ? max : c.answer;
		index = max > c.answer ? index : c.index;

		max = max > d.answer ? max : d.answer;
		index = max > d.answer ? index : d.index;

		ret = new DP(index, max);

		return ret;
	}

	public int[] pickTop(int[] arr) {
		int[] a = { -1, -1, -1 };
		int min = 999;
		int mini = 0;

		for (int i = 0; i < 4; i++) {
			if (arr[i] < min) {
				min = arr[i];
				mini = i;
			}
		}
		int max = -999;
		int maxi = 0;

		for (int i = 0; i < 4; i++) {
			if (i == mini) {
				continue;
			}
			if (max < arr[i]) {
				max = arr[i];
				maxi = i;
			}
		}

		int mid = -999;
		int midi = 0;
		for (int i = 0; i < 4; i++) {
			if (i == mini || i == maxi) {
				continue;
			}
			if (mid < arr[i]) {
				mid = arr[i];
				midi = i;
			}
		}
		a[0] = maxi;
		a[1] = midi;

		for (int i = 0; i < 4; i++) {
			if (i == mini || i == maxi || i == midi) {
				continue;
			}
			a[2] = i;
		}

		return a;
	}

}
