package baekJoon_12100_2048_EASY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int map[];
	static int copy[];
	static int cnt;
	static int max;
	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = { 0, 0, -1, 1 }; // »óÇÏ ÁÂ¿ì

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N * N];
		copy = new int[N * N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int k = Integer.parseInt(st.nextToken());
				map[i * N + j] = k;
				copy[i * N + j] = k;
			}
		}

		printMap();
	}

	public static void dfs(int nmap[]) {
		if (cnt == 5) {
			findMax();
		}
		for (int i = 0; i < 4; i++) {
			moveMap(dy[i],dx[i]);
			dfs(map);
			map = Arrays.copyOf(copy, copy.length);
		}
	}
	public static void moveMap(int ddy,int ddx) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				max = max > map[i * N + j] ? max : map[i * N + j];
			}
		}
	}
	public static void findMax() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				max = max > map[i * N + j] ? max : map[i * N + j];
			}
		}
	}

	public static void printMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i * N + j]);
			}
			System.out.println();
		}
	}
}
