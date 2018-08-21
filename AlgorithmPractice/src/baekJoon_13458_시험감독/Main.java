package baekJoon_13458_시험감독;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static long room[];
	static long cap;
	static long re;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		room = new long[N];
		re = N;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			room[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		cap = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			room[i] = room[i] - cap;
			if (room[i] < 0) {
				room[i] = 0;
			}
		}
		cap = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			if (room[i] % cap == 0) {
				re = re + (room[i] / cap);
			} else {
				re = re + 1 + (room[i] / cap);
			}
		}

		System.out.println(re);
	}
}
