package baekJoon_2589_º¸¹°¼¶;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * º¸¹°¼¶ BFS
 * 
 * @author Baek
 *
 */
public class Main {
	static int row, col;
	static ArrayList<ArrayList<Character>> map = new ArrayList<>();
	static ArrayList<Node> zeroList = new ArrayList<>(); // À°Áö ÁÂÇ¥ ÀúÀåÇÏ´Â º¯¼ö
	static int checked[];
	static int checkCopy[];

	static Queue<Node> q = new LinkedList<>();

	static int dy[] = { 0, 0, 1, -1 };
	static int dx[] = { 1, -1, 0, 0 };

	static int max;
	static int depth;

	static class Node {
		int y, x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		checked = new int[row * col + 1];
		checkCopy = new int[row * col + 1];

		// MAP »ý¼º
		for (int i = 0; i < row; i++) {
			map.add(new ArrayList<>());
			String s = br.readLine();
			for (int j = 0; j < col; j++) {
				Character c = s.charAt(j);
				map.get(i).add(c);
				if (c.equals('L')) {
					zeroList.add(new Node(i, j));
				}
			}
		}
		// À°ÁöÀÎ ÁÂÇ¥ÀüºÎ BFS ÀÛ¾÷
		for (int i = 0; i < zeroList.size(); i++) {
			Node node = zeroList.get(i);
			checked[(node.y * col) + node.x] = 1;
			q.add(node);
			depth = -1;
			bfs();
			checked = Arrays.copyOf(checkCopy, checked.length);
		}

		System.out.println(max);

	}

	public static void bfs() {
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Node node = q.poll();

				for (int j = 0; j < 4; j++) {
					int ny = node.y + dy[j];
					int nx = node.x + dx[j];
					if (ny >= row || nx >= col || ny < 0 || nx < 0) {
						continue;
					}
					if (map.get(ny).get(nx).equals('L') && checked[(ny * col) + nx] == 0) {
						checked[(ny * col) + nx] = 1;
						q.offer(new Node(ny, nx));
					}
				}

			}

			depth++;
		}
		max = max >= depth ? max : depth;
	}
}
