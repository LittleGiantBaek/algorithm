package baekJoon_1904_01≈∏¿œ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int p = 15746;
		int fn2 = 1;
		int fn1 = 1;
		int fn = 1;

		for (int i = 1; i < N; i++) {
			fn = (fn1 % p + fn2 % p) % p;
			fn2 = fn1 % p;
			fn1 = fn % p;
		}

		System.out.println(fn);
	}
}
