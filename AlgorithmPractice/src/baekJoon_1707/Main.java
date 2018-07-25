package baekJoon_1707;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	static final int RED = 1;
	static final int BLUE = -1;

	static boolean checkBipartite;

	public static void main(String[] args) throws IOException {

		int K; // 테스트케이스
		int V; // 정점의 개수
		int E; // 간선의 개수
		ArrayList<ArrayList<Integer>> graph;
		int[] color;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		K = Integer.parseInt(br.readLine());

		for (int k = 0; k < K; k++) {

			// input
			StringTokenizer st = new StringTokenizer(br.readLine());

			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());

			color = new int[V + 1];
			graph = new ArrayList<ArrayList<Integer>>();

			checkBipartite = true;

			for (int i = 0; i < V + 1; i++) {

				Arrays.fill(color, 0);

				graph.add(new ArrayList<Integer>());
			}

			for (int e = 0; e < E; e++) {

				st = new StringTokenizer(br.readLine());

				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				graph.get(a).add(b);
				graph.get(b).add(a);
			}

			for (int i = 1; i < V + 1; i++) {

				if (!checkBipartite) {

					break;
				}

				if (color[i] == 0) {

					dfs(i, RED, color, graph, V);
				}
			}

			System.out.println(checkBipartite ? "YES" : "NO");
		} // test case loop

	} // main

	static void dfs(int node, int c, int[] color, ArrayList<ArrayList<Integer>> graph, int V) {

		color[node] = c;

		for (int adjNode : graph.get(node)) {

			if (color[adjNode] == c) {

				checkBipartite = false;
				return;
			}

			if (color[adjNode] == 0) {

				dfs(adjNode, -c, color, graph, V);
			}
		}
	}
}



/* 시간초과
package baekJoon_1707;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static int v;
	static int e;
	static Node nodes[];

	static class Node {
		int y;
		int c;
		List<Node> adjList = new ArrayList<>();

		public Node(int y, int c) {
			this.y = y;
			this.c = c;
		}
	}

	public static boolean dfs(int y) {
		boolean flag = true;
		for (int i = 1; i <= v; i++) {
			if (nodes[y].adjList.contains(nodes[i])) {
				if (nodes[i].c != 0) {
					if (nodes[y].c == nodes[i].c) {
						flag = false;
						return flag;
					}
					continue;
				}
				nodes[i].c = -nodes[y].c;
				flag = dfs(i);
			}
		}
		return flag;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int t = 0; t < T; t++) {
			v = sc.nextInt();
			e = sc.nextInt();
			nodes = new Node[v + 1];

			for (int i = 1; i <= v; i++) {
				nodes[i] = new Node(i, 0);
			}

			for (int i = 0; i < e; i++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				nodes[a].adjList.add(nodes[b]);
				nodes[b].adjList.add(nodes[a]);
			}

			nodes[1].c = 1;
			if (dfs(1)) {
				System.out.println("YES");
				continue;
			}
			System.out.println("NO");
		}
	}
}
*/