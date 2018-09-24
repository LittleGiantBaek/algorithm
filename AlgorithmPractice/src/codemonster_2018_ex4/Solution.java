package codemonster_2018_ex4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {

	Queue<Node> q = new LinkedList<>();
	Node[] nodes;
	int checked[];

	class Node {
		int num;
		ArrayList<Node> childList = new ArrayList<>();

		public Node(int num) {
			super();
			this.num = num;
		}
	}

	public int[] solution(int N, int[][] directory, int[][] query) {
		int[] answer = new int[query.length];
		nodes = new Node[N];
		checked = new int[N];

		for (int i = 0; i < N; i++) {
			nodes[i] = new Node(i);
		}

		int parent;
		int child;

		for (int i = 0; i < N - 1; i++) {
			parent = directory[i][0] - 1;
			child = directory[i][1] - 1;
			nodes[parent].childList.add(nodes[child]);
			nodes[child].childList.add(nodes[parent]);
		}

		int start;
		int end;
		int re;

		for (int i = 0; i < query.length; i++) {
			start = query[i][0] - 1;
			end = query[i][1] - 1;
			Arrays.fill(checked, 0);
			checked[start] = 1;

			re = bfs(start, end);

			answer[i] = re;

		}

		return answer;
	}

	public int bfs(int start, int end) {

		int count = 1;
		q.clear();

		if (start == end) {
			return 1;
		}

		for (int i = 0; i < nodes[start].childList.size(); i++) {
			Node n = nodes[start].childList.get(i);
			if (n.num == end) {
				return 2;
			}
			q.add(n);
		}
		count++;

		int size = 0;

		while (!q.isEmpty()) {
			size = q.size();
			for (int i = 0; i < size; i++) {
				Node n = q.poll();
				for (int j = 0; j < n.childList.size(); j++) {
					Node next = n.childList.get(j);
					if (next.num == end) {
						count++;
						return count;
					}
					if (checked[next.num] == 1) {
						continue;
					}

					checked[next.num] = 1;
					q.add(n.childList.get(j));

				}
			}

			count++;
		}

		return count;
	}

}
