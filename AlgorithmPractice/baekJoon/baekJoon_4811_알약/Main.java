package baekJoon_4811_¾Ë¾à;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static long D[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = -1;
		while (true) {
			n = Integer.parseInt(br.readLine());
			if (n == 0)
				break;

			long ans = 0;
			D = new long[31][31];
			Arrays.fill(D[0], 1);

			ans = dp(n, 0);

			System.out.println(ans);

		}
	}

	static long dp(int w, int h) {
		if (D[w][h] != 0)
			return D[w][h];
		if (w == 0) {
			return D[0][h];
		}
		if (h == 0) {
			D[w][h] = dp(w - 1, 1);
			return D[w][h];
		}
		D[w][h] = dp(w - 1, h + 1) + dp(w, h - 1);
		return D[w][h];
	}
}
