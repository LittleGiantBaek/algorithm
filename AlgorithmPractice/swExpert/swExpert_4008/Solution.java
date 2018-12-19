package swExpert_4008;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 숫자 만들기
 * 
 * @author Baek
 */
public class Solution {
	static int N;

	static Map<String, Integer> ops = new HashMap<>();
	static List<String> opList = new ArrayList<>();
	static List<Integer> numList = new ArrayList<>();
	static Queue<String> queue = new LinkedList<>();

	static int map[][];

	static int max;
	static int min;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int test = Integer.parseInt(br.readLine()); // testcase 수

		for (int t = 1; t <= test; t++) {
			N = Integer.parseInt(br.readLine()); // 총 숫자개수
			map = new int[N - 1][N - 1];
			st = new StringTokenizer(br.readLine());
			ops.put("+", Integer.parseInt(st.nextToken()));
			ops.put("-", Integer.parseInt(st.nextToken()));
			ops.put("*", Integer.parseInt(st.nextToken()));
			ops.put("/", Integer.parseInt(st.nextToken()));

			st = new StringTokenizer(br.readLine());

			for (int num = 0; num < N; num++) {
				int k = Integer.parseInt(st.nextToken());
				numList.add(k);
			}

			makeMap();
			max = -1000000;
			min = 1000000;

			dfs(0);
			int result = max - min;

			ops.clear();
			numList.clear();
			opList.clear();

			System.out.println("#" + t + " " + result);
		}
	}

	// map의 행부분 설정
	public static void makeMap() {
		for (int i = 0; i < ops.get("+"); i++) {
			opList.add("+");
		}
		for (int i = 0; i < ops.get("-"); i++) {
			opList.add("-");
		}
		for (int i = 0; i < ops.get("*"); i++) {
			opList.add("*");
		}
		for (int i = 0; i < ops.get("/"); i++) {
			opList.add("/");
		}
	}

	/**
	 * 열 검사
	 * 
	 * @param x
	 * @return 사용한적있으면 false, 없으면 true
	 */
	public static boolean checkMap(int x) {
		for (int i = 0; i < N - 1; i++) {
			if (map[i][x] == 1) {
				return false;
			}
		}
		return true;
	}

	public static void dfs(int y) {
		if (y == N - 1) {
			int re = numList.get(0);
			for (int i = 0; i < N - 1; i++) {
				String o = queue.poll();
				queue.offer(o);
				if (o.equals("+")) {
					re = re + numList.get(i + 1);
				} else if (o.equals("-")) {
					re = re - numList.get(i + 1);
				} else if (o.equals("*")) {
					re = re * numList.get(i + 1);
				} else {
					re = re / numList.get(i + 1);
				}
			}
			max = max >= re ? max : re;
			min = min <= re ? min : re;
		}
		for (int i = 0; i < N - 1; i++) {
			if (checkMap(i)) {
				// queue에 연산자 순서대로 삽입
				queue.offer(opList.get(i));
				map[y][i] = 1;
				dfs(y + 1);
				map[y][i] = 0;
				int size = queue.size();
				for (int q = 0; q < size; q++) {
					String k = queue.poll();
					if (q < size - 1) {
						queue.offer(k);
					}
				}
			}
		}
	}

}
