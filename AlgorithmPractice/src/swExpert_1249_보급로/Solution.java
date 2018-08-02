package swExpert_1249_º¸±Þ·Î;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	static int map[][] = new int[100][100];
	static int val[][] = new int[100][100];
	static Queue<Node> q = new LinkedList<>();
	static int dy[] = { -1, 0, 1, 0 };
	static int dx[] = { 0, -1, 0, 1 };
	static int len;

	static class Node {
		int y, x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int tc = Integer.parseInt(br.readLine());
		for (int t = 1; t <= tc; t++) {
			len = Integer.parseInt(br.readLine());
			for (int i = 0; i < len; i++) {
				String s = br.readLine();
				for (int j = 0; j < len; j++) {
					map[i][j] = s.charAt(j) - '0';
					val[i][j] = 9999;
				}
			}
			val[0][0] = 0;
			q.offer(new Node(0, 0));
			bfs();
			System.out.println("#"+t+" "+val[len - 1][len - 1]);
		}

	}

	public static void bfs() {
		while (!q.isEmpty()) {
			Node n = q.poll();
			int y = n.y;
			int x = n.x;
			for (int i = 0; i < 4; i++) {
				int ny = y + dy[i];
				int nx = x + dx[i];
				if (ny < 0 || nx < 0 || ny >= len || nx >= len) {
					continue;
				}
				if (val[ny][nx] > map[ny][nx] + val[y][x]) {
					val[ny][nx] = map[ny][nx] + val[y][x];
					q.offer(new Node(ny, nx));
				}
			}
		}
	}

	public static void printMap() {
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				System.out.print(val[i][j]);
			}
			System.out.println();
		}
		System.out.println("*************************");
	}
}
