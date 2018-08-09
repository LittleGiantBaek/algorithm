package swExpert_1767_���μ��������ϱ�;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * SwExpert 1767�� ���μ��� �����ϱ�
 * 
 * @author Baek
 *
 */
public class Solution {
	static int N;
	static int map[];
	static int dy[] = { -1, 0, 1, 0 };
	static int dx[] = { 0, 1, 0, -1 };
	static ArrayList<Node> proList = new ArrayList<>();
	static int min = 999999; // line ������ ���� �ּ��ΰ��� ã��
	static int max = -999999; // line ������ ���� �ִ��� ���� ã��

	static int cnt; // ���μ����� ����

	static int lineLen; // ���α���
	static int lineNum; // ���ΰ���

	static boolean checked; // ������ ����������� ���� ��濡 ���θ��� ���μ��� üũ

	static int ny, nx;

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
		int tc = Integer.parseInt(br.readLine());
		int t = 1;

		while (tc-- > 0) {
			N = Integer.parseInt(br.readLine());
			map = new int[N * N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i * N + j] = Integer.parseInt(st.nextToken());
					if (map[i * N + j] == 1) {
						if (i != 0 && j != 0 && i != N - 1 && j != N - 1) {
							proList.add(new Node(i, j));
						}
					}
				}
			}
			cnt = proList.size();

			dfs(0);

			System.out.println("#" + t + " " + min);

			min = 99999;
			max = -999999;
			proList.clear();
			t++;
		} // while tc ����
	}

	public static void dfs(int index) {
		if (cnt == 0) { // ���μ��� ���� �˻��� ��
			if (lineNum > max) {
				min = 99999; // lineNum max �� �ٲ��� min �ʱ�ȭ
				max = lineNum;
			}
			if (lineNum == max) { // ������ ����� ���μ����� �ִ��϶��� �˻�
				cal();
				min = Math.min(lineLen, min); // ���� ���� �ּҰ� ����
			}
			return;
		}
		int tmpMap[] = Arrays.copyOf(map, map.length); // �� ���� �迭

		for (int i = index; i < proList.size(); i++) {
			checked = false;
			Node nNode = proList.get(i);
			for (int dir = 0; dir < 4; dir++) {
				if (isAvailavle(dir, nNode.y, nNode.x)) {
					checked = true;
					makeLine(dir, nNode.y, nNode.x); // �ʿ� ���� ����
					lineNum++;
					cnt--;
					dfs(i + 1);
					lineNum--;
					cnt++;
					map = Arrays.copyOf(tmpMap, map.length); // �ʺ���
				} else {
					continue;
				}
			}
			if (!checked) { // �����¿� �˻�� ���� ���� ���������
				cnt--;
				dfs(i + 1);
				cnt++;
			}
		}
	}

	private static void makeLine(int dir, int y, int x) {
		while (true) { // �̹� �˻縦 �Ϸ��� ��ǥ�� ���� ����� ������ �������� ������
			y = y + dy[dir];
			x = x + dx[dir];
			if (y < 0 || x < 0 || y >= N || x >= N) {
				break;
			}
			map[y * N + x] = 2;
		}
	}

	private static boolean isAvailavle(int dir, int y, int x) {
		while (true) {
			y = y + dy[dir];
			x = x + dx[dir];
			if (y < 0 || x < 0 || y >= N || x >= N) {
				return true;
			}
			if (map[y * N + x] == 2 || map[y * N + x] == 1) {
				return false;
			}
		}
	}

	public static void cal() {
		lineLen = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i * N + j] == 2) {
					lineLen++;
				}
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
