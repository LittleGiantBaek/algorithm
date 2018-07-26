package baekJoon_2573_빙산;

import java.util.Scanner;

public class Main {
	static int map[][];
	static boolean checked[][];
	static int row;
	static int col;
	
	// 현재 남은 빙산의 개수
	static int cnt;

	// 연결되어있는 빙산의 개수
	static int dfsCnt;

	public static int dfs(int y, int x) {
		dfsCnt++;
		checked[y][x] = true;
		if (x < map[y].length - 1) {
			if (map[y][x + 1] != 0 && checked[y][x + 1] == false) {
				dfs(y, x + 1);
			}
		}
		if (y < map.length - 1) {
			if (map[y + 1][x] != 0 && checked[y + 1][x] == false) {
				dfs(y + 1, x);
			}
		}
		if (x > 0) {
			if (map[y][x - 1] != 0 && checked[y][x - 1] == false) {
				dfs(y, x - 1);
			}
		}
		if (y > 0) {
			if (map[y - 1][x] != 0 && checked[y - 1][x] == false) {
				dfs(y - 1, x);
			}
		}
		
		return dfsCnt;
	}
	public static int checkZero(int y,int x) {
		int re=0;
		if (x < map[y].length - 1) {
			if (map[y][x + 1] == 0) {
				re++;
			}
		}
		if (y < map.length - 1) {
			if (map[y + 1][x] == 0) {
				re++;
			}
		}
		if (x > 0) {
			if (map[y][x - 1] == 0) {
				re++;
			}
		}
		if (y > 0) {
			if (map[y - 1][x] == 0) {
				re++;
			}
		}
		
		return re;
	}
	public static void nextMap() {
		int[][] tmpMap = new int[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				tmpMap[i][j] = map[i][j];
			}
		}

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (map[i][j] != 0) {
					int re = tmpMap[i][j]- checkZero(i, j);
					if (re <= 0) {
						tmpMap[i][j] = 0;
					} else {
						tmpMap[i][j] = re;
					}
				}
			}
		}
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				map[i][j] = tmpMap[i][j];
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		row = sc.nextInt();
		col = sc.nextInt();
		
		map = new int[row][col];
		checked = new boolean[row][col];
		boolean flag = true;
		int year = 0;

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] != 0) {
					cnt++;
				}
			}
		}

		while (flag) {
			cnt = 0;
			dfsCnt = 0;

			// 해당 연도의 빙산 데이터 초기화
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					checked[i][j] = false;
					if (map[i][j] != 0) {
						cnt++;
					}
				}
			}

			// 남아있는 빙산수와 연결되어있는 빙산 수가 다르면 종료
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					if (map[i][j] != 0) {
						dfsCnt = dfs(i, j);
						j=col;
						i=row;
						if (dfsCnt != cnt) {
							flag = false;
							System.out.println(year);
						}
					}
				}
			}

			nextMap();
			
			// 1년 증가 남아있는 빙산이 없으면 year는 0
			if (cnt == 0) {
				year = 0;
				System.out.println(year);
				break;
			}
			year++;
		}
	}
}
