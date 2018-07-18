package swExpert_1219;

import java.util.Scanner;

public class Solution {
	static int tNum[] = new int[11];
	static int map[][][] = new int[10][100][100];
	static boolean checked[][][] = new boolean[10][100][100];
	static int cnt[] = new int[10];

	public static int dfs(int y, int tn) {
		for (int i = 0; i < 100; i++) {
			if (map[tn][y][i] == 2) {
				cnt[tn] = 1;
			}
			if (map[tn][y][i] == 1 && checked[tn][y][i] == false) {
				checked[tn][y][i] = true;
				cnt[tn] = dfs(i, tn);
			}
		}
		return cnt[tn];
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		for (int i = 0; i < 10; i++) {
			int tn = sc.nextInt();
			tNum[tn] = sc.nextInt();
			for (int j = 0; j < tNum[tn]; j++) {
				int y = sc.nextInt();
				int x = sc.nextInt();
				map[i][y][x] = 1;
				if (x == 99) {
					map[i][y][x] = 2;
				}
			}
		}

		for (int i = 0; i < 10; i++) {
			dfs(0, i);
			System.out.println("#" + (i + 1) + " " + cnt[i]);
		}
	}
}
