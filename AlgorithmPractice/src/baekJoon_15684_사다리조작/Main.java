package baekJoon_15684_��ٸ�����;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;
/**
 * BaekJoon 15684�� ��ٸ� ����
 * �Ｚ ���⹮��
 * ���� Ž��
 * DP
 * @author Baek
 *
 */
public class Main {
	static int row, col;
	static int lineN; // ó�� ���μ� ����
	static int ladder[]; // ��ٸ���
	static int min = 999; // �߰� ���μ� �ּҰ�
	static ArrayList<Node> zeroList = new ArrayList<>();

	static class Node {
		int y, x;

		public Node(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		col = Integer.parseInt(st.nextToken());
		lineN = Integer.parseInt(st.nextToken());
		row = Integer.parseInt(st.nextToken());
		ladder = new int[(row + 1) * (col + 1)];
		int a = 0;
		int b = 0;

		for (int i = 0; i < lineN; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			ladder[a * col + b] = 1;
			ladder[a * col + b + 1] = 2;
		} // ���μ� �����

		for (int i = 1; i <= row; i++) {
			for (int j = 1; j < col; j++) {
				if (isAvailable(j, i)) {
					zeroList.add(new Node(i, j));
				}
			}
		}
		if (ladderComplete()) {
			System.out.println(0);
			return;
		}

		dfs(0, 0);

		if (min == 999) {
			System.out.println(-1);
		} else {
			System.out.println(min);
		}
	}

	public static void dfs(int line, int depth) {
		if (line == 3) {
			if (ladderComplete()) {
				min = min > line ? line : min;
				return;
			} else {
				return;
			}
		}
		if (ladderComplete()) {
			min = min > line ? line : min;
			return;
		}
		for (int i = depth; i < zeroList.size(); i++) {

			Node node = zeroList.get(i);

			if (isAvailable(node.x, node.y)) {
				ladder[node.y * col + node.x] = 1;
				ladder[node.y * col + node.x + 1] = 2;
				dfs(line + 1, i + 1);
				ladder[node.y * col + node.x] = 0;
				ladder[node.y * col + node.x + 1] = 0;
			}
		}
	}

	public static boolean isAvailable(int ladNum, int ladHor) { // ladNum��° ��ٸ��� ladHor���� ���μ��� �׾����� ¦�� �´��� �˻�
		if (ladder[ladHor * col + ladNum] != 0) {
			return false;
		}

		if (ladder[ladHor * col + ladNum + 1] != 0) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean ladderComplete() {
		int tmp;
		for (int i = 1; i <= col; i++) {
			tmp = i;
			for (int j = 1; j <= row; j++) {
				if (ladder[j * col + tmp] == 0) {
					continue;
				} else if (ladder[j * col + tmp] == 1) {
					tmp++;
				} else {
					tmp--;
				}
			}
			if (tmp != i) {
				return false;
			}
		}
		return true;
	}

	public static void printMap() {
		for (int i = 1; i <= row; i++) {
			for (int j = 1; j <= col; j++) {
				System.out.print(ladder[i * col + j] + " ");
			}
			System.out.println();
		}
	}
}
