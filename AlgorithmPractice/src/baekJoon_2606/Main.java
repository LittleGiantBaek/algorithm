package baekJoon_2606;

import java.util.Scanner;

public class Main {
	static int com;
	static int map[][];
	static int cnt;

	public static void dfs(int y) {
		if(y==1) {
			for (int j = 0; j < com + 1; j++) {
				map[j][1] = 0;
			}
		}
		for (int i = 0; i < com + 1; i++) {
			if (map[y][i] == 1) {
				cnt++;
				for (int j = 0; j < com + 1; j++) {
					map[j][i] = 0;
				}
				dfs(i);
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		com = sc.nextInt();
		map = new int[com + 1][com + 1];

		int v = sc.nextInt();

		for (int i = 0; i < v; i++) {
			int node1 = sc.nextInt();
			int node2 = sc.nextInt();

			map[node1][node2] = 1;
			map[node2][node1] = 1;
		}
		dfs(1);
		System.out.println(cnt);
	}
}
