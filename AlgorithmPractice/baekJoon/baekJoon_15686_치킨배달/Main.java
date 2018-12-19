package baekJoon_15686_치킨배달;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
/**
 * BaekJoon 15686번 치킨 배달 (삼성 기출)
 * DFS, 순열, 백트래킹
 * @author Baek
 *
 */
public class Main {

	static int N;
	static int M;

	static int map[];

	static ArrayList<Node> homeList = new ArrayList<>(); // 집리스트
	static ArrayList<Node> chickenList = new ArrayList<>(); // 치킨집 전체 리스트
	static ArrayList<Node> surviveChickenList = new ArrayList<>(); // 폐업 안하는 치킨집 리스트

	static int min = 9999; // 치킨거리 최소값

	static int hTocMin; // 집에서 치킨집까지 거리 최소값
	static int hToc; // 집에서 치킨집까지의 거리
	static int disTot; // 전체 치킨 거리

	static int cy, cx, hy, hx; // 치킨집좌표, 집 좌표

	static class Node {
		int y, x;
		boolean live = true;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N * N];

		int val;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				val = Integer.parseInt(st.nextToken());
				if (val == 1) {
					homeList.add(new Node(i, j));
					map[i * N + j] = 1;
				}
				if (val == 2) {
					chickenList.add(new Node(i, j));
				}
			}
		}
		chickenPickDfs(0, 0);
		System.out.println(min);
	}

	public static void chickenPickDfs(int depth, int s) {
		if (depth == M) {
			disTot = 0;
			
			for (int i = 0; i < homeList.size(); i++) { //집에서 치킨집까지 거리 측정 후 최소값 저장
				hTocMin = 999;
				hy = homeList.get(i).y;
				hx = homeList.get(i).x;
				for (int j = 0; j < surviveChickenList.size(); j++) {
					cy = surviveChickenList.get(j).y;
					cx = surviveChickenList.get(j).x;
					hToc = Math.abs(cy - hy) + Math.abs(cx - hx);
					hTocMin = hTocMin > hToc ? hToc : hTocMin;
				}
				disTot += hTocMin;
			}

			min = disTot > min ? min : disTot;
			return;
		}
		for (int i = s; i < chickenList.size(); i++) {
			
			Node n = chickenList.get(i);
			
			surviveChickenList.add(n);
			map[n.y * N + n.x] = 2;
			
			chickenPickDfs(depth + 1, i + 1); //폐업안하는 숫자만큼 순열 dfs
			
			surviveChickenList.remove(n);
			map[n.y * N + n.x] = 0;
		}
	}
}
