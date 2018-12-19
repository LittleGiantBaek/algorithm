package baekJoon_1987_¾ËÆÄºª;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static char arr[][];
	static int cnt;
	static int max;
	static int dy[] = { 1, -1, 0, 0 };
	static int dx[] = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int row = Integer.parseInt(st.nextToken());
		int col = Integer.parseInt(st.nextToken());
		int checked[] = new int[26];

		arr = new char[row][col];
		for (int i = 0; i < row; i++) {
			String s = br.readLine();
			for (int j = 0; j < col; j++) {
				arr[i][j] = s.charAt(j);
				if (i == 0 && j == 0) {
					checked[arr[i][j] - 'A'] = 1;
				}
			}
		}
		cnt = 1;
		dfs(0, 0, checked);
		System.out.println(max);
	}

	public static void dfs(int y, int x, int checked[]) {

		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if (ny == arr.length || nx == arr[0].length || ny < 0 || nx < 0) {
				continue;
			}
			char al = arr[ny][nx];
			if (checked[al - 'A'] != 1) {
				checked[al - 'A'] = 1;
				cnt++;

				dfs(ny, nx, checked);

				checked[al - 'A'] = 0;
				cnt--;
			}
		}
		max = max >= cnt ? max : cnt;
	}
}
