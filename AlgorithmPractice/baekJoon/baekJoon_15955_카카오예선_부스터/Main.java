package baekJoon_15955_īī������_�ν���;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<Node> nodeList = new ArrayList<>();
	static ArrayList<Node> adjList = new ArrayList<>();

	static int N;
	static int checked[];
	static double hp;
	static int start, dest;

	public static class Node {
		int y, x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		checked = new int[N + 1];

		int Q = Integer.parseInt(st.nextToken());
		int y, x;

		nodeList.add(new Node(-1, -1)); // 0���� ä���

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			y = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			nodeList.add(new Node(y, x));
		}

		// ----------------------------------------------------------//

		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			dest = Integer.parseInt(st.nextToken());
			hp = Integer.parseInt(st.nextToken());
			checked[start] = 1;
			if (boost(start)) {
				System.out.println("YES");
				continue;
			}
			System.out.println("NO");
			Arrays.fill(checked, 0);
		}
	}

	public static boolean boost(int s) {
		if (s == dest) {
			return true;
		}

		for (int i = 1; i <= N; i++) {
			if (checked[i] == 1) {
				continue;
			}

			if (oneTime(s, i) == 2) { // �ѹ��� ����
				continue;
			}
			if (oneTime(s, i) == 1) { // �ѹ���
				checked[i] = 1;
				if (boost(i)) {
					return true;
				}
			}
		}
		return false;

	}

	public static int oneTime(int s, int d) {
		Node sNode = nodeList.get(s); // ������
		Node dNode = nodeList.get(d); // ������

		if (dNode.y == sNode.y || dNode.x == sNode.x) {
			return 1; // �ν��ͷ� �ѹ���
		}

		if (Math.abs(dNode.y - sNode.y)
				* (Math.abs(dNode.y - sNode.y) + Math.abs(dNode.x - sNode.x) * Math.abs(dNode.x - sNode.x)) <= hp
						* hp) {
			return 1; // �ν��� �Ⱦ��� hp�θ� �ѹ���
		}

		if (Math.abs(dNode.y - sNode.y) <= hp) {
			return 1; // hp�� ���θ� �̵��ϰ� �ν��� ����
		}
		if (Math.abs(dNode.x - sNode.x) <= hp) {
			return 1; // hp�� ���θ� �̵��ϰ� �ν��� ����
		}

		return 2; // �ѹ��� ����
	}
}
