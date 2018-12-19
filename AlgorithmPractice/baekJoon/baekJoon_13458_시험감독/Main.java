package baekJoon_13458_시험감독;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st1, st2;

		int N = Integer.parseInt(br.readLine());
		long room[] = new long[N];
		long re = N;
		st1 = new StringTokenizer(br.readLine());
		st2 = new StringTokenizer(br.readLine());
		long cap = Integer.parseInt(st2.nextToken());
		long cap2 = Integer.parseInt(st2.nextToken());

		for (int i = 0; i < N; i++) {
			room[i] = Integer.parseInt(st1.nextToken());
			room[i] = room[i] - cap;
			if (room[i] < 0) {
				room[i] = 0;
			}
			if (room[i] % cap2 == 0) {
				re = re + (room[i] / cap2);
			} else {
				re = re + 1 + (room[i] / cap2);
			}
		}

		System.out.println(re);
	}
}
