package kakao_2018_blind_¸ğÀÇ3;

public class Solution {
	public int[] solution(int[][] v) {
		int[] answer = new int[2];
		int x1;
		int x2;
		int x3;
		int x4;
		int y1;
		int y2;
		int y3;
		int y4;

		x1 = v[0][0];
		y1 = v[0][1];

		x2 = v[1][0];
		y2 = v[1][1];

		x3 = v[2][0];
		y3 = v[2][1];

		if (x1 == x2) {
			x4 = x3;
			if (y1 == y3) {
				y4 = y2;
			} else {
				y4 = y1;
			}
		} else {
			if (y1 == y2) {
				y4 = y3;
				if (x1 == x3) {
					x4 = x2;
				} else {
					x4 = x1;
				}
			} else {
				if (y1 == y3) {
					y4 = y2;
					x4 = x1;
				} else {
					y4 = y1;
					x4 = x2;
				}
			}
		}

		answer[0] = x4;
		answer[1] = y4;
		return answer;
	}
}
