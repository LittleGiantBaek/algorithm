package baekJoon_7562;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int T;
	static int I[];
	static int curY[];
	static int curX[];
	static int movY[];
	static int movX[];
	static int map[][][];
	static boolean checked[][][];
	static Queue<Node> queue[];

	static class Node {
		int y;
		int x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	public static void bfs(int tn) {
		int cnt = 0;
		int[] dy = { 1, 2, -1, -2, 1, -2, -1, 2, 0 };
		int[] dx = { 2, 1, -2, -1, -2, 1, 2, -1, 0 };

		while (!queue[tn].isEmpty()) {
			int size = queue[tn].size();
			for (int s = 0; s < size; s++) {
				Node node = queue[tn].poll();

				for (int d = 0; d < 9; d++) {
					int py = node.y + dy[d];
					int px = node.x + dx[d];
					if ((py >= 0 && py < I[tn]) && (px >= 0 && px < I[tn])) {
						if (map[tn][py][px] == 2) {
							queue[tn].clear();
							s = size;
							if (dy[d] == 0) {
								cnt = -1;
							}
							break;
						}
						if (map[tn][py][px] == 0 && checked[tn][py][px] == false) {
							queue[tn].offer(new Node(py, px));
							checked[tn][py][px] = true;
						}
					}
				} // for문 종류
			}
			cnt++;
		} // while문 종료
		System.out.println(cnt);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		queue = new LinkedList[T];
		I = new int[T];
		curY = new int[T];
		curX = new int[T];
		map = new int[T][][];
		checked = new boolean[T][][];
		movY = new int[T];
		movX = new int[T];

		for (int i = 0; i < T; i++) {
			queue[i] = new LinkedList<>();
			I[i] = sc.nextInt();
			map[i] = new int[I[i]][I[i]];
			checked[i] = new boolean[I[i]][I[i]];
			curY[i] = sc.nextInt();
			curX[i] = sc.nextInt();
			movY[i] = sc.nextInt();
			movX[i] = sc.nextInt();

			map[i][curY[i]][curX[i]] = 1;
			map[i][movY[i]][movX[i]] = 2;
			queue[i].offer(new Node(curY[i], curX[i]));
			checked[i][curY[i]][curX[i]] = true;
		}

		for (int i = 0; i < T; i++) {
			bfs(i);
		}

	}
}
