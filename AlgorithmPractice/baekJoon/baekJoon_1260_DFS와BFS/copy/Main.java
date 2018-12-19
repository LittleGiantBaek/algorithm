package baekJoon_1260_DFS¿ÍBFS.copy;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static int[][] mapD;
	public static int[][] mapB;
	
	public static void dfs(int y, int x) {
		System.out.print(x+" ");
		for(int i = 0; i<mapD[y].length;i++) {
			mapD[y][i] = 0;
			mapD[i][y] = 0;
		}
		for(int i = 0; i< mapD[y].length;i++) {
			if(mapD[x][i] == 1) {
				dfs(x,i);
			}
		}
	}
	
	public static void bfs(int y) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(y);
		while(!queue.isEmpty()) {
			int next = queue.poll();
			System.out.print(next+" ");
			for( int i =0 ;i<mapB.length ; i++) {
				if(mapB[next][i] == 1) {
					queue.add(i);
					for(int j = 0 ; j<mapB.length;j++) {
						mapB[j][i] = 0;
					}
					mapB[next][i] = 0;
					mapB[i][next] = 0;
				}
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int startV = sc.nextInt();
		mapD = new int[N+1][N+1];
		mapB = new int[N+1][N+1];
		
		for (int i = 0; i < M; i++) {
			int fv = sc.nextInt();
			int lv = sc.nextInt();
			mapD[fv][lv] = 1;
			mapD[lv][fv] = 1;
			mapB[fv][lv] = 1;
			mapB[lv][fv] = 1;
		}

		for (int i = 0; i < N; i++) {
			if (mapD[startV][i] == 1) {
				System.out.print(startV+" ");
				dfs(startV, i);
			}
		}
		System.out.println();
		
		bfs(startV);
		
	}
}

/*
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
  
public class Main {
      
    static int[][] graph;
    static boolean visited[];
    static int N;
    static int E;
    static int startPoint;
          
    public static void dfs(int i){
        visited[i] = true;
        System.out.print(i+" ");
          
        for(int j=1; j<=N; j++){
            if(graph[i][j]==1&& visited[j]==false){
                dfs(j);
            }
        }
    }
      
    public static void bfs(int i){
  
        Queue<Integer> q = new LinkedList<Integer>();
        q.offer(i);
        visited[i] = true;
        System.out.print(i+" ");
          
        int temp;
        while(!q.isEmpty()){
            temp = q.poll();
            for(int j=0; j<N+1; j++){
                if(graph[temp][j]==1&&visited[j]==false){
                    q.offer(j);
                    visited[j]=true;
                    System.out.print(j+" ");
                }
            }                       
        }
    }
      
    public static void main(String[] args) {
                  
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        graph = new int[1001][1001];
        E = sc.nextInt();
        visited = new boolean[10001];
        startPoint = sc.nextInt();
          
        int a,b;
          
        for(int i=1; i<=E; i++){
            a = sc.nextInt();
            b = sc.nextInt();
              
            graph[a][b] = graph[b][a] = 1;
        }
          
        dfs(startPoint);
          
        for(int j=1; j<=N; j++){
            visited[j]=false;
        }
        System.out.println();
          
        bfs(startPoint);
    }
}
*/

