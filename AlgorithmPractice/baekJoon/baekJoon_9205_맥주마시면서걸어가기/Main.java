package baekJoon_9205_맥주마시면서걸어가기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int ny, nx;
	static int sy, sx;
	static int fy, fx;

	static ArrayList<Point> shopList = new ArrayList<>();
	static int shopChecked[];

	static class Point {
		int y, x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int tc = Integer.parseInt(br.readLine());

		while (tc-- > 0) {
			N = Integer.parseInt(br.readLine());
			shopChecked = new int[N];
			st = new StringTokenizer(br.readLine());
			sy = Integer.parseInt(st.nextToken());
			sx = Integer.parseInt(st.nextToken());

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				ny = Integer.parseInt(st.nextToken());
				nx = Integer.parseInt(st.nextToken());
				shopList.add(new Point(ny, nx));
			}

			st = new StringTokenizer(br.readLine());
			fy = Integer.parseInt(st.nextToken());
			fx = Integer.parseInt(st.nextToken());

			if (dfs(sy, sx)) {
				System.out.println("happy");
			} else {
				System.out.println("sad");
			}
			shopList.clear();
		} // while문 종료
	}// main 종료

	public static boolean dfs(int y, int x) {
		if (Math.abs(fy - y) + Math.abs(fx - x) <= 1000) {
			return true;
		}

		for (int i = 0; i < shopList.size(); i++) {
			if (shopChecked[i] == 1) {
				continue;
			}
			Point p = shopList.get(i);
			if (Math.abs(p.y - y) + Math.abs(p.x - x) <= 1000) {
				shopChecked[i] = 1;
				if (dfs(p.y, p.x)) {
					return true;
				} else {
					continue;
				}
			}

		}
		return false;
	}
}
