package swExpert_1227;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 미로-D4 
 * 해법 :DFS
 */
public class Solution {
	static int map[][] = new int[100][100];
	static int startX, startY;
	static int dy[] = { 1, -1, 0, 0 };
	static int dx[] = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int TEST = 1; TEST <= 10; TEST++) {
			int t = Integer.parseInt(br.readLine());
			for (int i = 0; i < 100; i++) {
				String s = br.readLine();
				for (int j = 0; j < 100; j++) {
					map[i][j] = s.charAt(j) - '0';
					if (map[i][j] == 2) {
						startY = i;
						startX = j;
					}
				}
			}
			boolean f = false;
			if (dfs(startY, startX, f)) {
				System.out.println("#" + TEST + " 1");
			} else {
				System.out.println("#" + TEST + " 0");
			}
		}

	}

	private static boolean dfs(int y, int x, boolean flag) {
		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if (ny < 0 || nx < 0 || ny == 100 || nx == 100) {
				continue;
			}
			if (map[ny][nx] == 3) {
				flag = true;
				return flag;
			}
			if (map[ny][nx] == 0) {
				map[ny][nx] = 2;
				flag = dfs(ny, nx, flag);
			}
		}
		return flag;
	}
}
