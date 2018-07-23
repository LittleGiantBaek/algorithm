package baekJoon_5214;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int N;
	static Queue<Node> queue = new LinkedList<>();
	static Node nodes[];

	static class Node {
		int num;
		boolean marked;
		List<Node> adjList;

		public Node() {
		}

		public Node(int num) {
			this.num = num;
			marked = false;
			adjList = new ArrayList<>();
		}
	}

	public static void bfs() {
		int cnt = 2;
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int p = 0; p < size; p++) {
				int nodeNext = queue.poll().num;
				if (nodes[nodeNext].adjList.contains(nodes[N])) {
					System.out.println(cnt / 2 + 1);
					return;
				}
				for (int i = 0; i < nodes[nodeNext].adjList.size(); i++) {
					Node n = nodes[nodeNext].adjList.get(i);
					if (n.marked == false) {
						n.marked = true;
						queue.offer(n);
					}
				}
			}
			cnt++;
		}
		System.out.println(-1);

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt(); // 역 수
		int K = sc.nextInt();// 지하철이 연결하는 역 수
		int M = sc.nextInt();// 지하철 수
		nodes = new Node[N + M + 1];
		for (int i = 0; i < N + M + 1; i++) {
			nodes[i] = new Node(i);
		}
		int node[] = new int[K];

		if (K == 1) {
			System.out.println(-1);
			return;
		}

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < K; j++) {
				node[j] = sc.nextInt();
				nodes[node[j]].adjList.add(nodes[N + i + 1]);
				nodes[N + i + 1].adjList.add(nodes[node[j]]);
			}
		}

		nodes[1].marked = true;

		for (int i = N + 1; i < N + M + 1; i++) {
			if (nodes[1].adjList.contains(nodes[i])) {
				nodes[i].marked = true;
				queue.offer(nodes[i]);
			}
		}

		bfs();
	}
}
