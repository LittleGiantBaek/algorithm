package baekJoon_2468;

import java.util.Scanner;

public class Main {
	static int [][] map;
	static boolean [][] checked;
	static int safe[][];
	
	public static void dfsR(int y,int x,int h) {
		checked[y][x] = true;
		
		if(map[y][x+1] > h && checked[y][x+1] == false) {
			dfsR(y,x+1,h);
		}
		if(map[y+1][x] > h && checked[y+1][x] == false) {
			dfsR(y+1,x,h);
		}
		if(x>0) {
			if(map[y][x-1] > h && checked[y][x-1] == false) {
				dfsR(y,x-1,h);
			}
		}
		if(y>0) {
			if(map[y-1][x] > h && checked[y-1][x] == false) {
				dfsR(y-1,x,h);
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//map 개수
		int n = sc.nextInt();
		map = new int[n+1][n+1];
		checked = new boolean[n+1][n+1];
		int max = 0;
		
		//safezone 최대 개수 저장하는 배열
		safe = new int [2][1];
		
		//값 입력
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				map[i][j] = sc.nextInt();
				max = max >= map[i][j] ? max : map[i][j];
			}
		}
		
		//높이에 따른 안전구역 체크 하는 영역
		for(int i=0;i<=max;i++) {
			//dfs checked 배열 초기화
			for(int c=0;c<=n;c++) {
				for(int c2=0;c2<=n;c2++) {
					checked[c][c2] = false;
				}
			}
			safe[1][0] = 0;
			
			for(int j=0;j<n;j++) {
				for(int k=0;k<n;k++) {
					if(map[j][k] > i && checked[j][k] == false) {
						dfsR(j,k,i);
						safe[1][0] += 1;
						
					}
					if(j == n-1 && k == n-1) {
						safe[0][0] = safe[0][0] >= safe[1][0] ? safe[0][0]: safe[1][0];
					}
				}
			}
		}
		System.out.println(safe[0][0]);
	}
}
