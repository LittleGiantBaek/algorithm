package baekJoon_9372_상근이의여행;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class Country {
		int loc;
		int checked = 0;
		ArrayList<Country> nosun = new ArrayList<>();

		public Country(int loc) {
			this.loc = loc;
		}
	}

	static int N, M;
	static Country countrys[];
	static Queue<Country> q = new LinkedList<>();
	static int answer;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int tc = Integer.parseInt(br.readLine());

		while (tc-- > 0) {
			answer = 0;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			countrys = new Country[N + 1];

			for (int i = 0; i < N + 1; i++) {
				countrys[i] = new Country(i);
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());

				if (!countrys[s].nosun.contains(countrys[e])) {
					countrys[s].nosun.add(countrys[e]);
				}
				if (!countrys[e].nosun.contains(countrys[s])) {
					countrys[e].nosun.add(countrys[s]);
				}
			}
			q.clear();
			q.add(countrys[1]);
			countrys[1].checked = 1;
			BFS();
			System.out.println(answer);
		}
	}

	public static void BFS() {
		while (!q.isEmpty()) {
			Country c = q.poll();
			int size = c.nosun.size();
			for (int i = 0; i < size; i++) {
				Country j = c.nosun.get(i);
				if (j.checked == 0) {
					q.add(countrys[j.loc]);
					answer++;
					j.checked = 1;
				}
			}
		}
	}

}
