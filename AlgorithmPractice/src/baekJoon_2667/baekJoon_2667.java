package baekJoon_2667;

import java.util.Arrays;
import java.util.Scanner;

public class baekJoon_2667 {

	public static int[][] danzi = new int[26][26]; // �����͸� ������ �迭

	public static int dfs(int y, int x, int cnt) {
		cnt++;
		danzi[y][x] = 0;
		if (danzi[y][x + 1] == 1) {
			cnt = dfs(y, x + 1, cnt);
		}
		if (danzi[y + 1][x] == 1) {
			cnt = dfs(y + 1, x, cnt);
		}
		if (x > 0) {
			if (danzi[y][x - 1] == 1) {
				cnt = dfs(y, x - 1, cnt);
			}
		}
		if (y > 0) {
			if (danzi[y - 1][x] == 1) {
				cnt = dfs(y - 1, x, cnt);
			}
		}

		return cnt;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int danSize = sc.nextInt(); // �Է� ������ ũ��

		String[] dan = new String[danSize]; // �Է� �����͸� ���� string ����

		int cnt; // ������ ����Ʈ ��
		int danCnt = 0; // ������
		int[] danTot = new int[(danSize * danSize / 2) + 1]; // ������ �� ����Ʈ ��
		
		// string data -> int ��ȯ
		for (int i = 0; i < danSize; i++) {
			dan[i] = sc.next();
			for (int j = 0; j < danSize; j++) {
				danzi[i][j] = Integer.parseInt(dan[i].substring(j, j + 1));
			}
		}

		for (int i = 0; i < danSize; i++) {
			for (int j = 0; j < danSize; j++) {
				if (danzi[i][j] == 1) {
					cnt = dfs(i, j, 0);
					danTot[danCnt++] = cnt;
				}
			}
		}
		
		Arrays.sort(danTot);
		System.out.println(danCnt);
		for(int re : danTot) {
			if(re!=0) {
				System.out.println(re);
			}
		}
	}
}
