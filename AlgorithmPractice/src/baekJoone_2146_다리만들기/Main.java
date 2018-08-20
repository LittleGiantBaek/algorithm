package baekJoone_2146_다리만들기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BaekJoon 2146 번 다리만들기
 * dfs ,bfs 혼합
 * 
 * @author Baek
 *
 */
public class Main {
	static int N;
	static int map[];
	static int checked[];

	static Queue<Node> q = new LinkedList<>();

	static int dy[] = { -1, 0, 1, 0 };
	static int dx[] = { 0, 1, 0, -1 };

	static int island = 2; // 섬 넘버링

	static int ny, nx;
	static int size;
	static int min;
	static int depth = 2;

	static class Node {
		int y, x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N * N];
		checked = new int[N * N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i * N + j] = Integer.parseInt(st.nextToken());
				if (map[i * N + j] == 1) {
					q.offer(new Node(i, j));
				}
			}
		}

		// 선 넘버링
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i * N + j] == 1) {
					map[i * N + j] = island;
					dfs(i, j);
					island++;
				}
			}
		}

		bfs();
		System.out.println(min);

	}

	public static void dfs(int y, int x) {
		for (int k = 0; k < 4; k++) {
			ny = y + dy[k];
			nx = x + dx[k];
			if (ny >= N || nx >= N || ny < 0 || nx < 0) {
				continue;
			}
			if (map[ny * N + nx] == 1) {
				map[ny * N + nx] = island;
				dfs(ny, nx);
			}
		}
	}

	public static void bfs() {
		boolean check = false;
		while (!q.isEmpty()) {
			size = q.size();
			for (int i = 0; i < size; i++) {
				Node node = q.poll();
				for (int k = 0; k < 4; k++) {
					ny = node.y + dy[k];
					nx = node.x + dx[k];
					if (ny >= N || nx >= N || ny < 0 || nx < 0) {
						continue;
					}

					if (map[ny * N + nx] == map[node.y * N + node.x]) { // 같은 섬이면 continue
						continue;
					}

					if (map[ny * N + nx] == 0) { // 바다일떄
						map[ny * N + nx] = map[node.y * N + node.x];
						checked[ny * N + nx] = depth; // check 배열로 depth를 저장
						q.offer(new Node(ny, nx));
					} else { // 다른섬일때
						if (checked[ny * N + nx] != depth) { // depth가 다를경우 다리가 연결되는 가장 짧은 길이 발견
							min *= 2;
							return;
						} else { // depth가 같은경우 while문 도는 도중 발생한 다리 => depth가 다른경우를 기다려야함
							check = true;
						}
					}
				}
			}
			if (check) { // 뺴는 이유 : 중간에 depth가 다른 다리를 만날경우 depth가 같은 다리를 만나는경우보다 다리 길이가 짧음
				min = min * 2 + 1;
				return;
			}
			min++;
			depth++;
		}
	}
}
