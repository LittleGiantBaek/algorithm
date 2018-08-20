package baekJoone_2146_�ٸ������;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BaekJoon 2146 �� �ٸ������
 * dfs ,bfs ȥ��
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

	static int island = 2; // �� �ѹ���

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

		// �� �ѹ���
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

					if (map[ny * N + nx] == map[node.y * N + node.x]) { // ���� ���̸� continue
						continue;
					}

					if (map[ny * N + nx] == 0) { // �ٴ��ϋ�
						map[ny * N + nx] = map[node.y * N + node.x];
						checked[ny * N + nx] = depth; // check �迭�� depth�� ����
						q.offer(new Node(ny, nx));
					} else { // �ٸ����϶�
						if (checked[ny * N + nx] != depth) { // depth�� �ٸ���� �ٸ��� ����Ǵ� ���� ª�� ���� �߰�
							min *= 2;
							return;
						} else { // depth�� ������� while�� ���� ���� �߻��� �ٸ� => depth�� �ٸ���츦 ��ٷ�����
							check = true;
						}
					}
				}
			}
			if (check) { // ���� ���� : �߰��� depth�� �ٸ� �ٸ��� ������� depth�� ���� �ٸ��� �����°�캸�� �ٸ� ���̰� ª��
				min = min * 2 + 1;
				return;
			}
			min++;
			depth++;
		}
	}
}
