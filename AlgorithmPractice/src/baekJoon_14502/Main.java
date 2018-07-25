package baekJoon_14502;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static class Point {
		int y, x;

		public Point(int y, int x) {
			this.x = x;
			this.y = y;
		}
	}

	static int row, col;
	static ArrayList<ArrayList<Integer>> map = new ArrayList<>();
	static ArrayList<ArrayList<Integer>> copy = new ArrayList<>();
	static ArrayList<Point> zeroPoint = new ArrayList<>();
	static ArrayList<Point> virusPoint = new ArrayList<>();

	static int cnt;
	static int max;

	public static void safeTrack() {
		if (cnt == 3) {
			copymap();
			for (int i = 0; i < virusPoint.size(); i++) {
				int y = virusPoint.get(i).y;
				int x = virusPoint.get(i).x;
				dfs(y, x);
			}
			int re = safezone();
			max = max >= re ? max : re;
			reset();
			return;
		}

		for (int i = 0; i < zeroPoint.size(); i++) {
			int y = zeroPoint.get(i).y;
			int x = zeroPoint.get(i).x;
			if (map.get(y).get(x) == 0) {
				map.get(y).set(x, 1);
				cnt++;
				safeTrack();
				cnt--;
				map.get(y).set(x, 0);
			}
		}
		return;
	}

	public static void dfs(int y, int x) {
		int dy[] = { 0, 0, 1, -1 };
		int dx[] = { 1, -1, 0, 0 };

		for (int k = 0; k < 4; k++) {
			int ny = y + dy[k];
			int nx = x + dx[k];
			if (ny >= row || nx >= col || ny < 0 || nx < 0) {
				continue;
			}
			if (map.get(ny).get(nx) == 0) {
				map.get(ny).set(nx, 2);
				dfs(ny, nx);
			}
		}
	}

	/**
	 * 안전영역 찾기
	 */
	public static int safezone() {
		int safe = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (map.get(i).get(j) == 0) {
					safe += 1;
				}
			}
		}
		return safe;
	}

	/**
	 * map -> copy 복사
	 */
	public static void copymap() {
		for (int i = 0; i < row; i++) {
			copy.get(i).clear();
			for (int j = 0; j < col; j++) {
				int tmp = map.get(i).get(j);
				copy.get(i).add(tmp);
			}
		}
	}

	/**
	 * dfs 이후 변경된 map (copy -> map) reset
	 */
	public static void reset() {
		for (int i = 0; i < row; i++) {
			map.get(i).clear();
			for (int j = 0; j < col; j++) {
				int tmp = copy.get(i).get(j);
				map.get(i).add(tmp);
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		row = sc.nextInt(); // 행
		col = sc.nextInt(); // 열

		for (int i = 0; i < row; i++) {
			map.add(new ArrayList<>());
			copy.add(new ArrayList<>());
		}

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				int k = sc.nextInt();
				if (k == 0) {
					zeroPoint.add(new Point(i, j));
				}
				if (k == 2) {
					virusPoint.add(new Point(i, j));
				}
				map.get(i).add(k);
			}
		}
		safeTrack();

		System.out.println(max);
	}

}
