package baekJoon_15649;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N, M;
	static Queue<Integer> queue = new LinkedList<>();
	static int nums[];

	public static void NM() {
		for (int i = 1; i <= N; i++) {
			if (queue.size() == M) {
				for (int j = 0; j < M; j++) {
					int k = queue.poll();
					System.out.print(k + " ");
					if (j != M - 1) {
						queue.offer(nums[k]);
					}
				}
				System.out.println();
				return;
			}
			if (queue.contains(nums[i])) {
				continue;
			}
			queue.offer(nums[i]);
			NM();
		}
		for (int j = 0; j < queue.size(); j++) {
			int k = queue.poll();
			if (j != queue.size()) {
				queue.offer(nums[k]);
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		nums = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			nums[i] = i;
		}
		for (int i = 1; i <= N; i++) {
			queue.offer(nums[i]);
			NM();
			queue.poll();
		}
	}
}
