package baekJoon_14503_로봇청소기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BaekJoon 14503번 로봇청소기 dfs,bfs
 * 
 * @author Baek
 *
 */
public class Main {
	static int row, col;
	static int map[];
	static int sy, sx; // 시작좌표
	static int sdir; // 시작 방향
	static int dy[] = { -1, 0, 1, 0 }; // 북 서 남 동
	static int dx[] = { 0, -1, 0, 1 };
	static int ny, nx;
	static int cnt = 1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		map = new int[row * col];
		st = new StringTokenizer(br.readLine());
		sy = Integer.parseInt(st.nextToken());
		sx = Integer.parseInt(st.nextToken());
		sdir = Integer.parseInt(st.nextToken());
		for (int i = 0; i < row; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < col; j++) {
				map[i * col + j] = Integer.parseInt(st.nextToken());
			}
		}
		if (sdir == 1) {
			sdir = 3;
		} else if (sdir == 3) {
			sdir = 1;
		}

		map[sy * col + sx] = 2;
		dfs(sy, sx, sdir);
		System.out.println(cnt);
	}

	public static void dfs(int y, int x, int dir) {
		for (int i = 1; i <= 4; i++) {
			ny = y + dy[(dir + i) % 4];
			nx = x + dx[(dir + i) % 4];
			if (ny > row - 1 || nx > col - 1 || ny < 0 || nx < 0) {
				continue;
			}
			if (map[ny * col + nx] == 1 || map[ny * col + nx] == 2) {
				continue;
			}
			map[ny * col + nx] = 2;
			cnt++;
			dfs(ny, nx, (dir + i) % 4);
			return;
		}

		ny = y + dy[(dir + 2) % 4];
		nx = x + dx[(dir + 2) % 4];
		if (ny > row - 1 || nx > col - 1 || ny < 0 || nx < 0) {
			return;
		}
		if (map[ny * col + nx] == 1) {
			return;
		}
		dfs(ny, nx, dir);
	}

	public static void printMap() {
		System.out.println("********************************");
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.print(map[i * col + j]);
			}
			System.out.println();
		}
		System.out.println("********************************");
	}
}
