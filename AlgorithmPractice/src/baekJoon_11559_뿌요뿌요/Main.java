package baekJoon_11559_�ѿ�ѿ�;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * baekJoon 11559�� �ѿ�ѿ�
 * dfs
 * @author Baek
 *
 */
public class Main {
	static ArrayList<ArrayList<Character>> map = new ArrayList<>(); // �� ��
	static ArrayList<Node> nodeList = new ArrayList<>(); // ���� �� ���� ��ǥ ���� ����Ʈ

	static boolean checked[][] = new boolean[12][6];

	static int dy[] = { -1, 0, 1, 0 };
	static int dx[] = { 0, -1, 0, 1 };
	static int ny, nx;

	static int cnt = 1; // ���ӵ� ���� ����
	static boolean status = true; // ���� ���Ű� �Ǵ��� ���� ���º���
	static int explosion; // ���� ȸ��

	public static class Node {
		int y, x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input;

		for (int j = 0; j < 6; j++) {
			map.add(new ArrayList<>());
		}
		for (int i = 0; i < 12; i++) {
			input = br.readLine();
			for (int j = 0; j < 6; j++) {
				map.get(j).add(input.charAt(j));
			}
		} // map ����

		while (status) {
			status = false;
			// ���� 1���� for�� �ѹ�
			for (int i = 11; i >= 0; i--) {
				for (int j = 0; j < 6; j++) {
					if (map.get(j).get(i) == 'C' || map.get(j).get(i) == '.') { // �ѹ��� ���� ������ ��� ó�� ���� �ӽ÷� C ���ڷ� ����
						continue;
					}
					cnt = 1;
					for (int k = 0; k < 12; k++) {
						Arrays.fill(checked[k], false); // check �迭 �ʱ�ȭ
					}
					checked[i][j] = true;
					dfs(i, j);
					if (cnt >= 4) { // ����� ���� ����
						status = true; // ���Ⱑ �ѹ��̶� ������ ���°� true
						map.get(j).set(i, 'C');
						for (int n = 0; n < nodeList.size(); n++) {
							ny = nodeList.get(n).y;
							nx = nodeList.get(n).x;
							map.get(nx).set(ny, 'C');
						}
					}
					nodeList.clear();
				}
			}
			if (status) { // �������� ����
				nextMap();
				explosion++;
			}
		}

		System.out.println(explosion);
	}

	public static void dfs(int y, int x) {
		for (int d = 0; d < 4; d++) {
			ny = y + dy[d];
			nx = x + dx[d];
			if (ny < 0 || nx < 0 || ny > 11 || nx > 5 || checked[ny][nx] == true) {
				continue;
			}
			if (map.get(nx).get(ny) == map.get(x).get(y)) {
				cnt++;
				checked[ny][nx] = true;
				nodeList.add(new Node(ny, nx));
				dfs(ny, nx);
			}
		}
	}

	public static void nextMap() {
		for (int j = 0; j < 6; j++) { // �������� �Ʒ� ����� �˻�
			for (int i = 11; i >= 0; i--) {
				if (map.get(j).get(i) == '.') { // .�ϰ�� ������ ������ �� ����
					break;
				}
				if (map.get(j).get(i) == 'C') {
					map.get(j).remove(i);
					map.get(j).add(0, '.');
					nextMap(); // ���� �̵��ϹǷ� ��ͷ� ó������ �ٽ� �˻�
				}
			}
		}
	}

	public static void printMap() {
		System.out.println("-------------------");
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 6; j++) {
				System.out.print(map.get(j).get(i) + " ");
			}
			System.out.println();
		}
		System.out.println("-------------------");
		System.out.println();
	}
}
