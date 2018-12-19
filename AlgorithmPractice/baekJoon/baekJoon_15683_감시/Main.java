package baekJoon_15683_����;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BaekJoon 15683�� ����
 * BruteForce,����Ž��
 * 
 * @author Baek
 *
 */
public class Main {
	static int row, col;
	static int map[];

	static int dy[] = { -1, 0, 1, 0 };
	static int dx[] = { 0, 1, 0, -1 };

	static int ny, nx;
	static int cnt;
	static int min = 99999; // �簢���� �ּҰ�

	static ArrayList<Node> list = new ArrayList<>(); // ������ CCTV ����Ʈ
	static ArrayList<Node> list5 = new ArrayList<>(); // 5�� ����Ʈ

	static class Node {
		int y, x;
		int num;

		public Node(int y, int x, int num) {
			this.y = y;
			this.x = x;
			this.num = num;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		map = new int[row * col];

		for (int i = 0; i < row; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < col; j++) {
				map[i * col + j] = Integer.parseInt(st.nextToken());
				if (map[i * col + j] == 5) {
					list5.add(new Node(i, j, 5));
				} else if (map[i * col + j] != 0 && map[i * col + j] != 6) {
					list.add(new Node(i, j, map[i * col + j]));
				}
			}
		}
		// 5�� Map �̸� ����
		for (int i = 0; i < list5.size(); i++) {
			makeMap(list5.get(i).y, list5.get(i).x, 0, 5);
		}

		dfs(0); // CCTV ����Ʈ ���� Ž��

		System.out.println(min);
	}

	public static void dfs(int index) {
		if (index == list.size()) {
			checkCount(); // �簢���� �˻� + �ּ� �簢���� ���
			return;
		}

		int tmpMap[] = Arrays.copyOf(map, map.length); // �ش� depth���� ����ϴ� �ӽ� map ����

		Node n = list.get(index);
		if (n.num == 2) { // 2�ϰ�� �¿�, ���� �ۿ� ����
			for (int dir = 0; dir < 2; dir++) {
				makeMap(n.y, n.x, dir, 2);
				dfs(index + 1);
				map = Arrays.copyOf(tmpMap, map.length);
			}
		} else {
			for (int dir = 0; dir < 4; dir++) {
				makeMap(n.y, n.x, dir, n.num);
				dfs(index + 1);
				map = Arrays.copyOf(tmpMap, map.length);
			}
		}
	}

	public static void checkCount() {
		cnt = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (map[i * col + j] == 0) {
					cnt++;
				}
			}
		}
		min = cnt > min ? min : cnt;
	}

	public static void makeMap(int y, int x, int dir, int num) {
		switch (num) {
		case 1: {
			ny = y;
			nx = x;
			make(dir);
			break;
		}
		case 2: {
			for (int i = 0; i < 2; i++) { // �¿�
				ny = y;
				nx = x;
				make(dir);
				dir = (dir + 2) % 4;
			}
			break;
		}
		case 3: {
			for (int i = 0; i < 2; i++) { // �ι���
				ny = y;
				nx = x;
				make(dir);
				dir = (dir + 1) % 4;
			}
			break;
		}
		case 4: {
			for (int i = 0; i < 3; i++) { // ������
				ny = y;
				nx = x;
				make(dir);
				dir = (dir + 1) % 4;
			}
			break;
		}
		case 5: {
			for (int i = 0; i < 4; i++) { // �׹���
				ny = y;
				nx = x;
				make(i);
			}
			break;
		}
		}
	}

	public static void make(int dir) {
		while (true) {
			ny = ny + dy[dir];
			nx = nx + dx[dir];
			if (ny >= row || nx >= col || ny < 0 || nx < 0) {
				break;
			}
			if (map[ny * col + nx] == 6) {
				break;
			}
			if (map[ny * col + nx] == 1 || map[ny * col + nx] == 2 || map[ny * col + nx] == 3 || map[ny * col + nx] == 4
					|| map[ny * col + nx] == 5) {
				continue;
			}
			map[ny * col + nx] = 7;
		}
	}

	public static void printMap() {
		System.out.println("--------------------");
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.print(map[i * col + j]);
			}
			System.out.println();
		}
		System.out.println("--------------------");
	}
}
