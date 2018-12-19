package swExpert_2806;

import java.util.Scanner;

public class Solution {
	static int N[];
	static int tn;
	static boolean checked[][][];
	static int cnt[];

	private static void queen(int tNum, int y, int x) {
		if (y == N[tNum] - 1) {
			cnt[tNum]++;
			checked[tNum][y][x] = false;
			return;
		}

		for (int i = 0; i < N[tNum]; i++) {
			if (isPossible(tNum, y + 1, i)) {
				checked[tNum][y + 1][i] = true;
				queen(tNum, y + 1, i);
			}
			if (i == N[tNum] - 1) {
				checked[tNum][y][x] = false;
				return;
			}
		}
	}

	public static boolean isPossible(int t, int y, int x) {
		int tmpY;
		int tmpX;

		if (y == N[t]) {
			return false;
		}
		tmpY = y;
		while (tmpY >= 0) {
			if (checked[t][tmpY][x] == true) {
				return false;
			}
			tmpY--;
		}
		tmpY = y;
		tmpX = x;
		while (tmpY >= 0 && tmpX >= 0) {
			if (checked[t][tmpY][tmpX] == true) {
				return false;
			}
			tmpY--;
			tmpX--;
		}
		tmpY = y;
		tmpX = x;
		while (tmpY >= 0 && tmpX < N[t]) {
			if (checked[t][tmpY][tmpX] == true) {
				return false;
			}
			tmpY--;
			tmpX++;
		}

		return true;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		tn = sc.nextInt();
		checked = new boolean[tn][][];
		N = new int[tn];
		cnt = new int[tn];

		for (int i = 0; i < tn; i++) {
			N[i] = sc.nextInt();
			checked[i] = new boolean[N[i]][N[i]];
		}

		for (int i = 0; i < tn; i++) {
			for (int j = 0; j < N[i]; j++) {
				checked[i][0][j] = true;
				queen(i, 0, j);
				checked[i][0][j] = false;
			}
			System.out.println("#"+(i+1)+" "+cnt[i]);
		}
	}
}
