package baekJoon_15649_N°úM_1;

import java.util.Scanner;

public class Main {
	static int N, M;
	static boolean checked[];

	public static void dfs(String str) {
		if (str.length() == M) {
			System.out.println(str);
			return;
		}
		for (int i = 1; i <= N; i++) {
			if (checked[i] == false) {
				str += i + " ";
				checked[i] = true;
				dfs(str);
				str = str.substring(0, str.length() - 2);
				checked[i] = false;
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		M = M * 2;
		checked = new boolean[N + 1];
		dfs("");
	}
}

/*
 import java.io.*;
import java.util.*;
// _15649_N°úM1
public class Main {

	static int N,M;
	static int visited[];
	static void dfs(String str) {
		if(str.length() == M) {
			System.out.println(str);
			return;
		}
		for(int i = 1 ; i <= N ; i++) {
			if(visited[i] == 0) {
				visited[i] = 1;
				String tmp = str;
				str += (i+ " ");
				dfs(str);
				visited[i] = 0;
				str = tmp;
				
			}
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		M = M*2;
		visited = new int[N+1];
		dfs("");
	}

}

 */
