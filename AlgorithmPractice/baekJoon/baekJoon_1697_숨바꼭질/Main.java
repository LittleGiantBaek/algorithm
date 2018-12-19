package baekJoon_1697_¼û¹Ù²ÀÁú;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * baekJoon 1697¹ø ¼û¹Ù²ÀÁú
 * BFS
 * @author Baek
 *
 */
public class Main {
	static int checked[] = new int[100001];
	static int cnt;
	static int start, fin;
	static int nx;
	static Queue<Integer> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		start = Integer.parseInt(st.nextToken());
		fin = Integer.parseInt(st.nextToken());
		if (start == fin) {
			System.out.println(0);
			return;
		}
		for (int i = 0; i < 3; i++) {
			nx = dx(start, i);
			if (nx != -1) {
				if (nx == fin) {
					System.out.println(1);
					return;
				}
				checked[nx] = 1;
				q.offer(nx);
			}
		}
		cnt = 1;
		checked[start] = 1;
		bfs();

		System.out.println(cnt);
	}

	public static int dx(int x, int dir) {
		if (dir == 0) { // x-1
			if (x <= 50000) { // x*2
				return x * 2;
			} else {
				return -1;
			}
		} else if (dir == 1) {// x+1
			if (x == 100000) {
				return -1;
			} else {
				return x + 1;
			}
		} else {
			return x - 1;
		}
	}

	public static void bfs() {
		int tmpnx = -1;
		int size = 0;
		while (!q.isEmpty()) {
			size = q.size();
			for (int s = 0; s < size; s++) {
				nx = q.poll();

				for (int i = 0; i < 3; i++) {
					tmpnx = dx(nx, i);
					if (tmpnx != -1) {
						if (tmpnx == fin) {
							cnt++;
							return;
						}
						if (checked[tmpnx] == 0) {
							checked[tmpnx] = 1;
							q.offer(tmpnx);
						}
					}
				}
			}
			cnt++;
		}
	}

}
