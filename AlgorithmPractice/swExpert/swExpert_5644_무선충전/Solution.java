package swExpert_5644_무선충전;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int M, A;
	static int map[][][];
	static int Amove[] = new int[101];
	static int Bmove[] = new int[101];
	static Queue<BC> q = new LinkedList<>();

	static int ans;
	static int dy[] = { 0, -1, 0, 1, 0 };
	static int dx[] = { 0, 0, 1, 0, -1 };
	static BC bcs[];

	static int ay = 0;
	static int ax = 0;
	static int by = 9;
	static int bx = 9;

	static class BC {
		int index;
		int y, x, c, p;

		public BC(int index, int x, int y, int c, int p) {
			this.index = index;
			this.y = y;
			this.x = x;
			this.c = c;
			this.p = p;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;

		int tc = Integer.parseInt(br.readLine());
		int t = 1;
		while (tc-- > 0) {
			st = new StringTokenizer(br.readLine());

			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			map = new int[10][10][A];
			bcs = new BC[A];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				Amove[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				Bmove[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				bcs[i] = new BC(i + 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1,
						Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				map[bcs[i].y][bcs[i].x][i] = i + 1;
				q.clear();
				q.add(bcs[i]);
				bfs(i, bcs[i].c);
			}

			if (map[ay][ax][0] != 0) {
				if (map[by][bx][0] != 0) {

				} else {

				}

			} else {

			}

			for (int i = 0; i < M; i++) {

			}

			System.out.println("#" + t++ + " ");
		}
	}

	static int maxBC(int y, int x) {
		int max = -1;
		int ta = 0;

		while (map[y][x][ta] != 0) {
			max = Math.max(map[y][x][ta], max);
			ta++;
		}

		return max;
	}

	static void bfs(int idx, int c) {
		int cnt = 0;
		int size = 0;
		int ny, nx;
		while (!q.isEmpty() && cnt < c) {

			size = q.size();
			for (int s = 0; s < size; s++) {
				BC b = q.poll();
				for (int i = 1; i < 5; i++) {
					ny = b.y + dy[i];
					nx = b.x + dx[i];

					if (ny >= 10 || nx >= 10 || ny < 0 || nx < 0) {
						continue;
					}
					if (map[ny][nx][idx] == idx + 1) {
						continue;
					}

					map[ny][nx][idx] = idx + 1;
					q.add(new BC(idx, nx, ny, c, 0));

				}
			}
			cnt++;
		}
	}

	static void printMap() {
		for (int k = 0; k < A; k++) {
			System.out.println("---------------------------------");
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					System.out.print(map[i][j][k]);
				}
				System.out.println();
			}
			System.out.println("---------------------------------");
		}
	}
}
