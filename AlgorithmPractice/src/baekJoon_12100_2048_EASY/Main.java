package baekJoon_12100_2048_EASY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * BaekJoon 12100번 2048(EASY)
 * 브르투포스 , dfs ,bfs
 * @author Baek
 *
 */
public class Main {
	static int N;
	static int map[];
	static boolean checked[];
	static boolean checkedcpy[];
	static int cnt;
	static int max;
	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = { 0, 0, -1, 1 }; // 상하 좌우

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N * N];
		checked = new boolean[N * N];
		checkedcpy = new boolean[N * N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int k = Integer.parseInt(st.nextToken());
				map[i * N + j] = k;
			}
		}

		dfs(map);

		System.out.println(max);
	}

	public static void dfs(int nmap[]) {
		if (cnt == 5) {
			findMax(nmap);
			return;
		}
		int tmpMap[] = new int[N * N];

		for (int i = 0; i < 4; i++) {
			
			tmpMap = Arrays.copyOf(nmap, map.length);
			nmap = moveMap(i, nmap); // 숫자 이동
			cnt++;

			checked = Arrays.copyOf(checkedcpy, checked.length); // checked 초기화

			dfs(nmap);

			nmap = Arrays.copyOf(tmpMap, map.length); //nmap 초기화
			cnt--;
		}
	}

	public static int[] moveMap(int dir, int nmap[]) {
		if (dir == 0) { // 상
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int k = i; // 행을 조절
					while (k > -1) { // 제일 윗줄부터 차레로이동
						k--;
						if (k == -1) { // 제일 첫째줄일때
							break;
						}
						if (nmap[k * N + j] == 0) { // 이동할 위치가 0일경우
							if (k == 0) { // 첫줄이 0일경우 값 변경
								nmap[j] = nmap[i * N + j];
								nmap[i * N + j] = 0;
							} else { // 중간에 있는 0은 건너감
								continue;
							}
						}

						if (nmap[k * N + j] != 0) { // 이미 이동한 숫자가 있는 경우 ( 둘째줄부터 )
							if (nmap[k * N + j] == nmap[i * N + j]) { // 숫자가 동일하고 합쳐지지 않았을경우 2배
								if (checked[k * N + j] == false) {
									nmap[k * N + j] *= 2;
									nmap[i * N + j] = 0;
									checked[k * N + j] = true;
								} else { // 이미 숫자가 합쳐진 숫자라면 한칸 뒤에 위치후 원래 자리 0으로 변경
									nmap[(k + 1) * N + j] = nmap[i * N + j];
									if (k + 1 != i) {
										nmap[i * N + j] = 0;
									}
								}
							} else {
								nmap[(k + 1) * N + j] = nmap[i * N + j];
								if (k + 1 != i) {
									nmap[i * N + j] = 0;
								}
							}
							break;
						}
					}
				}
			}
		}
		if (dir == 1) { // 하
			for (int i = N - 1; i >= 0; i--) {
				for (int j = 0; j < N; j++) {
					int k = i; // 행을 조절
					while (k < N) {
						k++;
						if (k == N) { // 제일 첫째줄일때
							break;
						}
						if (nmap[k * N + j] == 0) {
							if (k == N - 1) {
								nmap[(N - 1) * N + j] = nmap[i * N + j];
								nmap[i * N + j] = 0;
							} else {
								continue;
							}
						}

						if (nmap[k * N + j] != 0) {
							if (nmap[k * N + j] == nmap[i * N + j]) {
								if (checked[k * N + j] == false) {
									nmap[k * N + j] *= 2;
									nmap[i * N + j] = 0;
									checked[k * N + j] = true;
								} else {
									nmap[(k - 1) * N + j] = nmap[i * N + j];
									if (k - 1 != i) {
										nmap[i * N + j] = 0;
									}
								}
							} else {
								nmap[(k - 1) * N + j] = nmap[i * N + j];
								if (k - 1 != i) {
									nmap[i * N + j] = 0;
								}
							}
							break;
						}
					}
				}
			}
		}
		if (dir == 2) { // 좌
			for (int j = 0; j < N; j++) {
				for (int i = 0; i < N; i++) {
					int k = j; // 열을 조절
					while (k > -1) {
						k--;
						if (k == -1) { // 제일 첫째줄일때
							break;
						}
						if (nmap[i * N + k] == 0) {
							if (k == 0) {
								nmap[i * N] = nmap[i * N + j];
								nmap[i * N + j] = 0;
							} else {
								continue;
							}
						}

						if (nmap[i * N + k] != 0) {
							if (nmap[i * N + k] == nmap[i * N + j]) {
								if (checked[i * N + k] == false) {
									nmap[i * N + k] *= 2;
									nmap[i * N + j] = 0;
									checked[i * N + k] = true;
								} else {
									nmap[i * N + (k + 1)] = nmap[i * N + j];
									if (k + 1 != j) {
										nmap[i * N + j] = 0;
									}
								}
							} else {
								nmap[i * N + (k + 1)] = nmap[i * N + j];
								if (k + 1 != j) {
									nmap[i * N + j] = 0;
								}
							}
							break;
						}
					}
				}
			}
		}
		if (dir == 3) { // 우
			for (int j = N - 1; j >= 0; j--) {
				for (int i = 0; i < N; i++) {
					int k = j;
					while (k < N) {
						k++;
						if (k == N) { // 제일 첫째줄일때
							break;
						}
						if (nmap[i * N + k] == 0) {
							if (k == N - 1) {
								nmap[i * N + k] = nmap[i * N + j];
								nmap[i * N + j] = 0;
							} else {
								continue;
							}
						}

						if (nmap[i * N + k] != 0) {
							if (nmap[i * N + k] == nmap[i * N + j]) {
								if (checked[i * N + k] == false) {
									nmap[i * N + k] *= 2;
									nmap[i * N + j] = 0;
									checked[i * N + k] = true;
								} else {
									nmap[i * N + (k - 1)] = nmap[i * N + j];
									if (k - 1 != j) {
										nmap[i * N + j] = 0;
									}
								}
							} else {
								nmap[i * N + (k - 1)] = nmap[i * N + j];
								if (k - 1 != j) {
									nmap[i * N + j] = 0;
								}
							}
							break;
						}
					}
				}
			}
		}
		return nmap;
	}

	public static void findMax(int nmap[]) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				max = max > nmap[i * N + j] ? max : nmap[i * N + j];
			}
		}
	}

	public static void printMap(int[] mmap) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(mmap[i * N + j]);
			}
			System.out.println();
		}
	}
}

/**
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main 
{
	static int max = 0;
	static int n = 0;
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		
		int[][] arr = new int[n][n];
		for (int i=0; i<n; i++)
		{
			for (int j=0; j<n; j++)
			{
				arr[i][j] = sc.nextInt();				
			}
		}
		
		for (int i=0; i<4; i++)
		{
			DFS(i, 0, arr);
		}
		
		System.out.println(max);
	}
	
	public static void DFS(int index, int count, int[][] arr)
	{
		if (count == 5)
		{
			for (int i=0; i<n; i++)
			{
				for (int j=0; j<n; j++)
				{
					if (max < arr[i][j])
					{
						max = arr[i][j];
					}
				}
			}
			return;
		}
		
		int [][] next = new int [n][n];
		
		ArrayList<Integer> temp = new ArrayList<Integer>();
		ArrayList<Integer> list = new ArrayList<Integer>();		
		for (int i=0; i<n; i++)
		{			
			temp.clear(); list.clear();
			
			for (int j=0; j<n; j++)
			{
				// L or R
				if (index <2)
				{
					if (arr[i][j] != 0) temp.add(arr[i][j]);
				}
				// U or D
				else
				{
					if (arr[j][i] != 0) temp.add(arr[j][i]);
				}
			}
			
			// R or D
			if (index % 2 != 0)
			{
				Collections.reverse(temp);
			}
			
			for (int j=0; j<temp.size(); j++)
			{
				if (j != temp.size()-1 && temp.get(j).equals(temp.get(j+1)))
				{
					list.add(temp.get(j++)*2);
				}
				else
				{
					list.add(temp.get(j));
				}
			}
			
			for (int j=0; j<list.size(); j++)
			{
				// L
				if (index == 0) next[i][j] = list.get(j);
				// R
				else if (index == 1) next[i][n-1-j] = list.get(j);
				// U
				else if (index == 2) next[j][i] = list.get(j);
				// D
				else if (index == 3) next[n-1-j][i] = list.get(j);
			}
		}	
		
		for (int i=0; i<4; i++)
		{
			DFS(i, count+1, next);
		}
	}
}
 */
