package baekJoon_2455_지능형기차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int max;
	static int cur;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int out = Integer.parseInt(st.nextToken());
			int in = Integer.parseInt(st.nextToken());
			cur = cur + in - out;
			max = max > cur ? max : cur;
		}
		System.out.println(max);
	}
}
