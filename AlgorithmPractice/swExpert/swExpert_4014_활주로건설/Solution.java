package swExpert_4014_Ȱ�ַΰǼ�;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * SW Expert 4014�� ����SW �����׽�Ʈ �ùķ��̼� , ���Ʈ����, ����Ž��
 * 
 * @author Baek
 *
 */
public class Solution {
	static int cnt;
	static int X;
	static int N;
	static int map[];
	static int slope[];
	static int slopeCpy[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int tc = Integer.parseInt(br.readLine());
		int t = 1;
		while (tc-- > 0) {
			cnt = 0;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			map = new int[N * N];
			slope = new int[N];
			slopeCpy = new int[N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i * N + j] = Integer.parseInt(st.nextToken());
				}
			}

			check(1); // ����
			check(0); // ����
			System.out.println("#" + t + " " + cnt);
			t++;
		} // while�� ����
	}// main ����

	public static void check(int rc) {
		int tmp = 0;
		int m = 0;
		int y = 0;
		int x = 0;
		for (int i = 0; i < N; i++) {
			m = rc == 1 ? i * N : i;
			tmp = map[m]; // ���� ù��° ��
			slope = Arrays.copyOf(slopeCpy, N);
			for (int j = 1; j < N; j++) {
				m = rc == 1 ? i * N + j : j * N + i; // ���μ��ο� ���� i,j ��ġ ����
				if (Math.abs(tmp - map[m]) > 1) { // ���̰� 1�̻��� ���� Ȱ�ַθ� ������
					break;
				}

				if (Math.abs(tmp - map[m]) == 1) {
					y = rc == 1 ? i : j;
					x = rc == 1 ? j : i;
					if (slope(tmp, map[m], y, x, rc)) { // ���θ� ���� �� �ִٸ� tmp ����
						tmp = map[m];
					} else { // ���� ���� �� ������ Ȱ�ַ� ������
						break;
					}
				}
				if (j == N - 1) {
					cnt++;
				}
			}
		}
	}

	public static boolean slope(int cur, int next, int row, int col, int rc) {
		int block = 0;
		int m = 0;
		int m2 = 0;

		if (next > cur) { // ���� ���� ��簡 �� ������� ���ʿ� ���θ� ���� �� �ִ��� �˻�
			m = rc == 1 ? col - 1 : row - 1; // ���θ� �� ����, ���θ� �న��

			for (int k = m; k >= 0; k--) {
				m2 = rc == 1 ? row * N + k : k * N + col;
				if (map[m2] == cur && slope[k] == 0) { // slope ��ħ ����
					block++;
					slope[k] = 1;
					if (block == X) {
						break;
					}
					continue;
				} else { // slope ��ġ�� ����
					break;
				}
			}
			return block == X ? true : false;
		} else { // �������� ��簡 �� �������
			m = rc == 1 ? col : row; // ���θ� �� ����, ���θ� ������
			for (int k = m; k < N; k++) {
				m2 = rc == 1 ? row * N + k : k * N + col;
				if (map[m2] == next) {
					block++;
					slope[k] = 1; // �������� ��簡 ������� slope ��ĥ �� ����
					if (block == X) {
						break;
					}
					continue;
				} else {
					break;
				}
			}
			return block == X ? true : false;
		}
	}
}
