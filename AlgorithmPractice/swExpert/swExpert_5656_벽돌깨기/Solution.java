package swExpert_5656_벽돌깨기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
	static int N, W, H, Answer;

	static int[][] data;
	static int[][] map;

	public static void block_init() {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				map[i][j] = data[i][j];
			}
		}
	}

	public static boolean bomb_1() {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (map[i][j] > 10) {
					int d = map[i][j] - 10;
					map[i][j] = 0;
					int sx = j - d + 1; // 왼쪽 경계
					int ex = j + d - 1; // 오른쪽 경계
					if (sx < 0)
						sx = 0;
					if (ex >= W)
						ex = W - 1;
					boolean flag = false;
					for (int x = sx; x <= ex; x++) {
						// 제거할 벽들의 숫자가 1이면 해당 벽들을 지워줌
						if (map[i][x] == 1) {
							map[i][x] = 0;
						} else if (map[i][x] > 1 && map[i][x] < 10) {
							map[i][x] += 10;
							flag = true;
						}
					}

					int sy = i - d + 1;
					int ey = i + d - 1;
					if (sy < 0) {
						sy = 0;
					}
					if (ey >= 0) {
						ey = H - 1;
					}
					for (int y = sy; y <= ey; y++) {
						if (map[y][j] == 1) {
							map[y][j] = 0;
						} else if (map[y][j] > 1 && map[y][j] < 10) {
							map[y][j] += 10;
							flag = true;
						}
					}
					if (flag)
						return true;
				}
			}
		}
		return false;
	}

	public static void bomb(int x) {
		for (int y = 0; y < H; y++) {
			if (map[y][x] == 1) {
				map[y][x] = 0;
				return;
			} else if (map[y][x] > 1) {
				map[y][x] += 10;
				break;
			}
		}
		boolean flag = true;
		while (flag) {
			flag = bomb_1();
		}

	}

	public static void down() {
		for (int k = 0; k < W; k++) {
			for (int i = H - 1; i >= 0; i--) {
				if (map[i][k] == 0) {
					for (int y = i - 1; y >= 0; y--) {
						// 폭발되지 않은 벽돌이 있으면
						if (map[y][k] != 0) {
							map[i][k] = map[y][k];
							map[y][k] = 0;
							break;
						}
					}
				}
			}
		}
	}

	public static void block_check() {
		printMap();
		int cnt = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (map[i][j] > 0)
					cnt++;
			}
		}
		if (Answer > cnt) {
			Answer = cnt;
		}
	}

	public static void solve() {
		int[] B = new int[4];
		for (B[0] = 0; B[0] < W; B[0]++) {
			for (B[1] = 0; B[1] < W; B[1]++) {
				for (B[2] = 0; B[2] < W; B[2]++) {
					for (B[3] = 0; B[3] < W; B[3]++) {
						block_init();
						for (int i = 0; i < N; i++) {
							bomb(B[i]);
							down();
						}
						block_check();
						if (Answer == 0)
							return;
						if (N < 4)
							break;
					}
					if (N < 3)
						break;
				}
				if (N < 2)
					break;
			}
		}
	}

	static void printMap() {
		System.out.println("-------------------------------");
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		System.out.println("-------------------------------");
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		data = new int[15][12];
		map = new int[15][12];
		int tc = 0;
		tc = Integer.parseInt(br.readLine());
		int t = 1;

		while (tc > 0) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 구슬 쏘는 횟수
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					data[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			Answer = 9999999;
			solve();

			System.out.println("#" + t + " " + Answer);
			tc--;
			t++;
		}
	}

}