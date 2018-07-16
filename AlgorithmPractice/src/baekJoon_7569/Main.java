package baekJoon_7569;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int map[][][];
	static boolean checked[][][];
	static Queue<Node> queue;
	static int col;
	static int row;
	static int height;

	static class Node {
		int y, x, h;

		public Node(int y, int x, int h) {
			this.y = y;
			this.x = x;
			this.h = h;
		}
	}

	private static void bfs() {
		int year = 0;
		int[] dy = { 0, 1, -1, 0, 0, 0 };
		int[] dx = { 1, 0, 0, -1, 0, 0 };
		int[] dh = { 0, 0, 0, 0, 1, -1 };
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int k = 0; k < size; k++) {
				Node node = queue.poll();
				for (int i = 0; i < 6; i++) {
					int py = node.y + dy[i];
					int px = node.x + dx[i];
					int ph = node.h + dh[i];
					if ((py >= 0 && py < row) && (px >= 0 && px < col) && (ph >= 0 && ph < height)) {
						if (map[py][px][ph] == 0 && checked[py][px][ph] == false) {
							queue.offer(new Node(py, px, ph));
							map[py][px][ph] = 1;
							checked[py][px][ph] = true;
						}
					}
				}
			}
			year++;
		}
		for (int h = 0; h < height; h++) {
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					if (map[i][j][h] == 0) {
						System.out.println("-1");
						h = height;
						i = row;
						j = col;
						break;
					}
					if (h == height - 1 && i == row - 1 && j == col - 1) {
						System.out.println(year - 1);
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		col = sc.nextInt();
		row = sc.nextInt();
		height = sc.nextInt();
		map = new int[row][col][height];
		checked = new boolean[row][col][height];
		queue = new LinkedList<>();

		for (int h = 0; h < height; h++) {
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					map[i][j][h] = sc.nextInt();
					if (map[i][j][h] == 1) {
						queue.offer(new Node(i, j, h));
						checked[i][j][h] = true;
					}
				}
			}
		}
		bfs();
	}

}
