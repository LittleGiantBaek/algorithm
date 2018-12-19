package swExpert_5650_핀볼게임;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int map[][];

	static int dy[] = { -1, 0, 1, 0 }; // 상우하좌
	static int dx[] = { 0, 1, 0, -1 };

	static class Hole {
		int y, x;

		public Hole(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	static int go(int y, int x, int sy, int sx, int dir, int cnt) {
		int ny, nx;

		ny = y + dy[dir];
		nx = x + dx[dir];

		if (ny == sy && nx == sx) {
			return cnt;
		}

		if (ny >= N || nx >= N || ny < 0 || nx < 0 || map[ny][nx] == 5) {
			cnt = go(ny, nx, sy, sx, (dir + 2) % 4, cnt + 1);
			return cnt;
		}

		if (map[ny][nx] == -1) {
			return cnt;
		}

		if (map[ny][nx] >= 6) { // 웜홀
			if (ny == holes[map[ny][nx]][0].y && nx == holes[map[ny][nx]][0].x) {
				y = holes[map[ny][nx]][1].y;
				x = holes[map[ny][nx]][1].x;
			} else {
				y = holes[map[ny][nx]][0].y;
				x = holes[map[ny][nx]][0].x;
			}
			cnt = go(y, x, sy, sx, dir, cnt);
			return cnt;
		} else if (map[ny][nx] <= 4 && map[ny][nx] >= 1) { // 상자
			// 반대방향으로 이동
			if ((map[ny][nx] == 1 || map[ny][nx] == 4) && dir == 0) {
				cnt = go(ny, nx, sy, sx, (dir + 2) % 4, cnt + 1);
			} else if ((map[ny][nx] == 1 || map[ny][nx] == 2) && dir == 1) {
				cnt = go(ny, nx, sy, sx, (dir + 2) % 4, cnt + 1);
			} else if ((map[ny][nx] == 2 || map[ny][nx] == 3) && dir == 2) {
				cnt = go(ny, nx, sy, sx, (dir + 2) % 4, cnt + 1);
			} else if ((map[ny][nx] == 3 || map[ny][nx] == 4) && dir == 3) {
				cnt = go(ny, nx, sy, sx, (dir + 2) % 4, cnt + 1);
			}

			// 시계방향 90도 회전
			else if ((map[ny][nx] == 1) && dir == 3) {
				cnt = go(ny, nx, sy, sx, (dir + 1) % 4, cnt + 1);
			} else if ((map[ny][nx] == 2) && dir == 0) {
				cnt = go(ny, nx, sy, sx, (dir + 1) % 4, cnt + 1);
			} else if ((map[ny][nx] == 3) && dir == 1) {
				cnt = go(ny, nx, sy, sx, (dir + 1) % 4, cnt + 1);
			} else if ((map[ny][nx] == 4) && dir == 2) {
				cnt = go(ny, nx, sy, sx, (dir + 1) % 4, cnt + 1);
			}

			// 반시계방향 90도 회전
			else if ((map[ny][nx] == 1) && dir == 2) {
				cnt = go(ny, nx, sy, sx, (dir + 3) % 4, cnt + 1);
			} else if ((map[ny][nx] == 2) && dir == 3) {
				cnt = go(ny, nx, sy, sx, (dir + 3) % 4, cnt + 1);
			} else if ((map[ny][nx] == 3) && dir == 0) {
				cnt = go(ny, nx, sy, sx, (dir + 3) % 4, cnt + 1);
			} else if ((map[ny][nx] == 4) && dir == 1) {
				cnt = go(ny, nx, sy, sx, (dir + 3) % 4, cnt + 1);
			}
			return cnt;

		} else {
			cnt = go(ny, nx, sy, sx, dir, cnt);
			return cnt;
		}
	}

	static Hole holes[][];

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int tc = Integer.parseInt(br.readLine());
		int t = 1;
		while (tc-- > 0) {
			N = Integer.parseInt(br.readLine());
			map = new int[N + 1][N + 1];
			holes = new Hole[20][2];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] >= 6) {
						if (holes[map[i][j]][0] == null) {
							holes[map[i][j]][0] = new Hole(i, j);
						} else {
							holes[map[i][j]][1] = new Hole(i, j);
						}
					}
				}
			}

			int ret = -1;
			int tmp = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == 0) {
						for (int d = 0; d < 4; d++) {
							tmp = go(i, j, i, j, d, 0);
							ret = ret > tmp ? ret : tmp;
						}
					}
				}
			}

			System.out.println("#" + t++ + " " + ret);
		}
	}
}
