package baekJoon_16236_아기상어_삼성2018하반기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int map[][];
	static int checked[][];
	static int gy, gx;
	static int ans;
	static int geSize = 2;
	static int cnt;
	static int eatCnt;

	static int dy[] = { -1, 0, 1, 0 };
	static int dx[] = { 0, 1, 0, -1 };

	static Queue<Point> q = new LinkedList<>();
	static ArrayList<Point> fish = new ArrayList<>();

	static class Point {
		int y, x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		checked = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					gy = i;
					gx = j;
					q.add(new Point(gy, gx));
					checked[gy][gx] = 1;
					map[i][j] = 0;
				}
			}

		}
		bfs();
		System.out.println(ans);
	}

	static void bfs() {
		int size;
		boolean find = false;

		int ny, nx;

		while (!q.isEmpty()) {
			size = q.size();
			find = false;
			for (int i = 0; i < size; i++) {
				Point p = q.poll();
				for (int d = 0; d < 4; d++) {
					ny = p.y + dy[d];
					nx = p.x + dx[d];
					if (ny >= N || nx >= N || ny < 0 || nx < 0) {
						continue;
					}

					if (checked[ny][nx] == 1) {
						continue;
					}

					if (map[ny][nx] > geSize) {
						continue;
					}

					if (map[ny][nx] < geSize && map[ny][nx] != 0) {
						fish.add(new Point(ny, nx));
						find = true;
					}
					q.add(new Point(ny, nx));
					checked[ny][nx] = 1;
				}
			}
			if (find) {
				Collections.sort(fish, new Comparator<Point>() {
					@Override
					public int compare(Point o1, Point o2) {
						if (o1.y > o2.y)
							return 1;
						else if (o1.y < o2.y)
							return -1;
						else {
							if (o1.x > o2.x)
								return 1;
							else if(o1.x < o2.x) {
								return -1;
							}
							else
								return 0;
						}
					}
				});
				Point fp = fish.get(0);
				map[fp.y][fp.x] = 0;
				gy = fp.y;
				gx = fp.x;
				q.clear();
				fish.clear();
				for (int k = 0; k < N; k++) {
					Arrays.fill(checked[k], 0);
				}
				checked[gy][gx] = 1;
				q.add(new Point(gy, gx));

				eatCnt++;
				if (eatCnt == geSize) {
					eatCnt = 0;
					geSize++;
				}
				ans = cnt + 1;
			}
			cnt++;
		}
	}

	static void printMap() {
		System.out.println("==============================");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		System.out.println("==============================");
	}
}
