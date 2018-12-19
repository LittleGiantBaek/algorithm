package swExpert_5653_줄기세포배양;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	static int N, M, K;

	static int data[] = new int[1000 * 1000];
	static int map[] = new int[1000 * 1000];
	static int depth[] = new int[1000 * 1000];

	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = { 0, 0, 1, -1 };

	static int ans;

	static Queue<Point> q = new LinkedList<>();

	static class Point {
		int y, x;

		public Point(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());

		for (int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			ans = 0;
			Arrays.fill(map, 0);
			Arrays.fill(data, 0);
			Arrays.fill(depth, 0);
			q.clear();
			
			for (int i = 500; i < 500 + N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 500; j < 500 + M; j++) {
					map[i * 1000 + j] = Integer.parseInt(st.nextToken());
					data[i * 1000 + j] = map[i * 1000 + j];
					if (map[i * 1000 + j] != 0) {
						depth[i * 1000 + j] = 1;
						q.add(new Point(i, j));
					}
				}
			}
			bfs();
			printMap();
			System.out.println("#" + t + " " + ans);
		}
	}

	static void bfs() {
		int size = 0;
		int ny, nx;
		int dep = 1;

		while (K > 0) {
			size = q.size();

			for (int i = 0; i < size; i++) {
				Point p = q.poll();
				if (map[p.y * 1000 + p.x] == 0) { //
					map[p.y * 1000 + p.x] = -1;
					for (int d = 0; d < 4; d++) {
						ny = p.y + dy[d];
						nx = p.x + dx[d];

						if (data[ny * 1000 + nx] == 0) {
							map[ny * 1000 + nx] = data[p.y * 1000 + p.x];
							data[ny * 1000 + nx] = data[p.y * 1000 + p.x];
							depth[ny * 1000 + nx] = dep;
							q.add(new Point(ny, nx));

						} else if(depth[ny * 1000 + nx] == dep){
							if (map[ny * 1000 + nx] < data[p.y * 1000 + p.x]) {
								map[ny * 1000 + nx] = data[p.y * 1000 + p.x];
								data[ny * 1000 + nx] = data[p.y * 1000 + p.x];
							}
						}
					}
				} else {
					map[p.y * 1000 + p.x]--;
					q.add(new Point(p.y, p.x));
				}
			}
			K--;
			dep++;
		}

		count();
	}

	public static void count() {
		for (int i = 0; i < 1000; i++) {
			for (int j = 0; j < 1000; j++) {
				if (data[i * 1000 + j] != 0 && map[i * 1000 + j] != -1) {
					ans++;
				}
			}
		}
	}

	public static void printMap() {
		System.out.println("----------------------");
		for (int i = 0; i < 1000; i++) {
			for (int j = 0; j < 1000; j++) {
				System.out.print(map[i * 1000 + j]);
			}
			System.out.println();
		}
		System.out.println("----------------------");
	}
}
