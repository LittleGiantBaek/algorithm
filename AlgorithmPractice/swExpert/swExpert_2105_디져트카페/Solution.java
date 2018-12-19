package swExpert_2105_디져트카페;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
	static int N;

	static Integer map[];

	static int drdy[] = { 1, 1 }; // 우측하단 , 좌측 하단
	static int drdx[] = { 1, -1 };

	static int dudy[] = { 1, -1 }; // 좌측하단, 좌측 상단
	static int dudx[] = { -1, -1 };

	static int urdy[] = { -1, -1 }; // 좌측상단, 우측상단
	static int urdx[] = { -1, 1 };

	static int udy[] = { -1 }; // 우측상단
	static int udx[] = { 1 };

	static int sy, sx; // 시작점

	static int depth; // 카페 들른 횟수
	static int cnt; // 카페 들른 횟수 최대값

	static Integer desert[] = new Integer[101]; // 1~100까지 desert 객체

	static ArrayList<Integer> dList = new ArrayList<>(); // 방문한 desert 객체

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());
		int re = 1;

		for (int i = 0; i <= 100; i++) {
			desert[i] = i;
		}

		while (t-- > 0) {
			N = Integer.parseInt(br.readLine());
			map = new Integer[N * N];

			cnt = -999;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i * N + j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 1; j < N - 1; j++) {
					depth = 0;
					sy = i;
					sx = j;

					dList.add(desert[map[i * N + j]]);

					dfs(sy, sx, 1);

					dList.clear();

				}
			}

			if (cnt == -999) {
				System.out.println("#" + re + " -1");
			} else {
				System.out.println("#" + re + " " + cnt);
			}

			re++;
		}
	}

	public static void dfs(int y, int x, int dir) {
		if (y == sy && x == sx && depth != 0) {
			cnt = cnt > depth ? cnt : depth;
			return;
		}
		int ny, nx;

		for (int i = 0; i < 2; i++) {

			if (dir == 1) {
				ny = y + drdy[i];
				nx = x + drdx[i];
			} else if (dir == 2) {
				ny = y + dudy[i];
				nx = x + dudx[i];
			} else if (dir == 3) {
				ny = y + urdy[i];
				nx = x + urdx[i];
			} else {
				if (i == 1) {
					continue;
				}
				ny = y + udy[i];
				nx = x + udx[i];
			}

			if (ny >= N || nx >= N || ny < 0 || nx < 0) {
				continue;
			}

			if (dList.contains(desert[map[ny * N + nx]])) {
				if (ny == sy && nx == sx && depth != 1) {
					depth++;
					dfs(ny, nx, dir);
					depth--;
				}
				continue;
			}
			dList.add(desert[map[ny * N + nx]]);
			depth++;
			if (i == 1) {
				dfs(ny, nx, dir + 1);
			} else {
				dfs(ny, nx, dir);
			}
			depth--;
			dList.remove(desert[map[ny * N + nx]]);
		}
	}
}
