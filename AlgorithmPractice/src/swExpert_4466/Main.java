package swExpert_4466;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// testcase 수
		int T = sc.nextInt();
		int N[] = new int[T];
		int K[] = new int[T];
		int max[] = new int[T];
		List<int[]> score = new ArrayList<>();
		// int score[][] = null;

		for (int j = 0; j < T; j++) {
			// 시험친 과목의 수
			N[j] = sc.nextInt();
			// 선택할 과목의 수
			K[j] = sc.nextInt();

			score.add(new int[N[j]]);

			// N개의 정수 0~100 점 입력
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
