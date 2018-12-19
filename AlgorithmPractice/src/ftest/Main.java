package ftest;

import java.util.LinkedList;
import java.util.Stack;

public class Main {
	static int adjMatrix[][] = new int[7][7];

	static LinkedList<Integer> adjList[] = new LinkedList[7];
	static boolean checked[] = new boolean[7];
	static Stack<Integer> topology = new Stack<>();
	public static void main(String args[]) {
		adjMatrix[0][1] = 1;
		adjMatrix[0][3] = 1;
		adjMatrix[1][2] = 1;
		adjMatrix[1][4] = 1;
		adjMatrix[3][4] = 1;
		adjMatrix[4][2] = 1;
		adjMatrix[4][5] = 1;
		adjMatrix[6][3] = 1;

		for(int i = 0 ; i< 7;i++) {
			adjList[i] = new LinkedList<>();
		}
		adjList[0].add(1);
		adjList[0].add(3);
		adjList[1].add(2);
		adjList[1].add(4);
		adjList[3].add(4);
		adjList[4].add(2);
		adjList[4].add(5);
		adjList[6].add(3);

		int size;
		for (int i = 0; i < 7; i++) {
			size = adjList[i].size();
			System.out.print(i + ": ");
			for (int s = 0; s < size; s++) {
				System.out.print(adjList[i].get(s) + ", ");
			}
			
			System.out.print("  ");
		}

		System.out.println();
		checked[0] = true;
		dfs(0);
		System.out.println();
		
		System.out.print("[");
		size = topology.size();
		for(int i = 0 ; i<size;i++) {
			System.out.print(topology.pop());
			if(i != size-1) {
				System.out.print(", ");
			}
		}
		System.out.print("]");
	}

	static void dfs(int a) {
		int size = adjList[a].size();
		int next = -1;
		topology.push(a);
		System.out.print("[" + a + "]"+" ");
		for (int i = 0; i < size; i++) {
			next = adjList[a].get(i);
			if (checked[next]) {
				continue;
			}
			checked[next] = true;
			dfs(next);
		}
	}
	
}
