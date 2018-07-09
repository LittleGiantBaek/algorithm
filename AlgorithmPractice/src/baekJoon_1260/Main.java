package baekJoon_1260;

import java.util.Scanner;

public class Main {
	public static int reVD[];
	public static int reVB[];
	public static int[][] map;
	public static int cnt = 0;
	
	public static void dfs(int y, int x) {
		reVD[cnt++] = x;
		for(int i = 0; i<map[y].length;i++) {
			map[y][i] = 0;
			map[i][y] = 0;
		}
		for(int i = 0; i< map[y].length;i++) {
			if(map[x][i] == 1) {
				dfs(x,i);
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int startV = sc.nextInt();
		map = new int[N+1][N+1];
		reVD = new int[N+1];
		reVB = new int[N+1];
		reVD[cnt++] = startV;
		reVB[cnt++] = startV;
		for (int i = 0; i < M; i++) {
			int fv = sc.nextInt();
			int lv = sc.nextInt();
			map[fv][lv] = 1;
			map[lv][fv] = 1;
		}

		for (int i = 0; i < N; i++) {
			if (map[startV][i] == 1) {
				dfs(startV, i);
			}
		}
		for(int i =0; i< reVD.length;i++) {
			if(reVD[i] != 0) {
				System.out.print(reVD[i]+" ");
			}
		}
		System.out.println();
		
		
	}
}
