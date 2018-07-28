package baekJoon_3055_탈출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BaekJoon_3055 탈출 BFS
 * 
 * @author Baek
 *
 */
public class Main {
	static int row, col; // 행 렬

	static char map[][]; // 지도
	static int checked[][];
	static int waterChecked[][];

	static Queue<Node> q = new LinkedList<>(); // BFS로 이동경로 저장할 큐
	static Queue<Node> waterQ = new LinkedList<>(); // 물의 이동경로 저장할 큐

	static int dy[] = { -1, 0, 1, 0 };
	static int dx[] = { 0, 1, 0, -1 }; // 상 우 하 좌 시계방향

	static int cnt; // 이동횟수

	static class Node {
		int y, x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());

		map = new char[row][col];
		checked = new int[row][col];
		waterChecked = new int[row][col];

		for (int i = 0; i < row; i++) {
			String s = br.readLine();
			for (int j = 0; j < col; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == 'S') {
					q.offer(new Node(i, j));
					checked[i][j] = 1;
				}
				if (map[i][j] == '*') {
					waterQ.offer(new Node(i, j));
					waterChecked[i][j] = 1;
				}
			}
		}

		if (bfs()) {
			System.out.println(cnt);
		} else {
			System.out.println("KAKTUS");
		}

	}

	//////////////////////////////////////////////////////////////////////////////////////////////
	public static boolean bfs() {
		boolean flag = false;
		int ny = 0;
		int nx = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Node node = q.poll();
				for (int j = 0; j < 4; j++) {
					ny = node.y + dy[j];
					nx = node.x + dx[j];
					if (ny >= row || nx >= col || ny < 0 || nx < 0) {
						continue;
					}
					if (map[ny][nx] == 'D') {
						flag = true;
						cnt++;
						return flag;
					} else if (isAvailable(ny, nx)) {
						q.offer(new Node(ny, nx));
						map[ny][nx] = 'S';
						checked[ny][nx] = 1;
					}
				}

			}
			cnt++;
			waterBFS();
		}

		return flag;
	}

	public static void waterBFS() {
		int size = waterQ.size();
		for (int s = 0; s < size; s++) {
			Node node = waterQ.poll();
			for (int i = 0; i < 4; i++) {
				int ny = node.y + dy[i];
				int nx = node.x + dx[i];
				if (ny >= row || nx >= col || ny < 0 || nx < 0) {
					continue;
				}
				if (waterChecked[ny][nx] == 1) {
					continue;
				}
				if (map[ny][nx] != 'X' && map[ny][nx] != 'D') {
					map[ny][nx] = '*';
					waterChecked[ny][nx] = 1;
					waterQ.offer(new Node(ny, nx));
				}
			}
		}
	}

	private static boolean isAvailable(int y, int x) {
		int ny = 0;
		int nx = 0;
		if (map[y][x] != '.') {
			return false;
		}
		for (int i = 0; i < 4; i++) {
			ny = y + dy[i];
			nx = x + dx[i];
			if (ny >= row || nx >= col || ny < 0 || nx < 0) {
				continue;
			} else if (checked[ny][nx] == 1) {
				continue;
			}
			if (map[ny][nx] == '*') {
				return false;
			}
		}
		return true;
	}
}
