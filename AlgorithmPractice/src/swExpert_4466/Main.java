package swExpert_4466;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// testcase ��
		int T = sc.nextInt();
		int N[] = new int[T];
		int K[] = new int[T];
		int max[] = new int[T];
		List<int[]> score = new ArrayList<>();
		// int score[][] = null;

		for (int j = 0; j < T; j++) {
			// ����ģ ������ ��
			N[j] = sc.nextInt();
			// ������ ������ ��
			K[j] = sc.nextInt();

			score.add(new int[N[j]]);

			// N���� ���� 0~100 �� �Է�
			for (int i = 0; i < N[j]; i++) {
				score.get(j)[i] = sc.nextInt();
			}

		}
		for (int i = 0; i < T; i++) {
			Arrays.sort(score.get(i));
		}
		for (int i = 0; i < T; i++) {
			for (int j = N[i] - 1; j > N[i] - K[i] - 1; j--) {
				max[i] += score.get(i)[j];
			}
		}
		for (int i = 0; i < T; i++) {
			System.out.println("#" + (i + 1) + " " + max[i]);
		}
	}
}
