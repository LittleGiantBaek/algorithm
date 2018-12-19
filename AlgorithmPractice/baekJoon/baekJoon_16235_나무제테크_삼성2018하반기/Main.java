package baekJoon_16235_나무제테크_삼성2018하반기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static int yangAdd[][];
	static int yang[][];
	static int checked[][];
	static int dy[] = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int dx[] = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static ArrayList<Integer>[][] treeList; // 해당지역의 나무 리스트 값은 나이
	static ArrayList<Point> treeLoc = new ArrayList<>(); // 나무가 있는 지역 리스트
	static Map<Point, Integer> fiveYeartree = new HashMap<>();

	static class Point {
		int y, x;
		int num;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}

		public Point(int y, int x, int num) {
			this.y = y;
			this.x = x;
			this.num = num;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		int sy, sx, age;

		yang = new int[N][N];
		yangAdd = new int[N][N];
		checked = new int[N][N];
		treeList = new ArrayList[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				yangAdd[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				treeList[i][j] = new ArrayList<>();
			}
		}
		for (int i = 0; i < N; i++) {
			Arrays.fill(yang[i], 5);
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			sy = Integer.parseInt(st.nextToken());
			sx = Integer.parseInt(st.nextToken());
			age = Integer.parseInt(st.nextToken());
			treeLoc.add(new Point(sy - 1, sx - 1));
			treeList[sy - 1][sx - 1].add(age);
			checked[sy - 1][sx - 1] = 1;
		}
		while (K-- > 0) {
			spring();
			fall();
			winter();

			for (int i = 0; i < treeLoc.size(); i++) {
				Point p = treeLoc.get(i);
				Collections.sort(treeList[p.y][p.x]);
			}
		}

		int ans = 0;
		for (int i = 0; i < treeLoc.size(); i++) {
			Point p = treeLoc.get(i);
			ans += treeList[p.y][p.x].size();
		}

		System.out.println(ans);

	}

	static void spring() {
		int size = 0;
		int idx = 0;
		int toYang = 0;
		int a = 0;
		for (int i = 0; i < treeLoc.size(); i++) {
			Point p = treeLoc.get(i);
			size = treeList[p.y][p.x].size();
			toYang = 0;
			idx = 9999;
			for (int k = 0; k < size; k++) {
				if (yang[p.y][p.x] >= treeList[p.y][p.x].get(k)) {
					a = treeList[p.y][p.x].get(k);
					yang[p.y][p.x] -= a;
					a++;

					treeList[p.y][p.x].set(k, a);

					if (a % 5 == 0) {
						if (fiveYeartree.containsKey(p)) {
							a = fiveYeartree.get(p);
							fiveYeartree.replace(p, a + 1);
						} else {
							fiveYeartree.put(p, 1);
						}
					}
				} else {
					idx = k;
					break;
				}
			}
			// summer
			for (int k = idx; k < size; k++) {
				toYang = treeList[p.y][p.x].get(idx);
				yang[p.y][p.x] += toYang / 2;
				treeList[p.y][p.x].remove(idx);
			}
		}
	}

	static void fall() {
		int ny, nx;
		Set<Point> set = fiveYeartree.keySet();
		Iterator<Point> it = set.iterator();
		while (it.hasNext()) {
			Point p = it.next();
			int num = fiveYeartree.get(p);
			for (int i = 0; i < num; i++) {
				for (int d = 0; d < 8; d++) {
					ny = p.y + dy[d];
					nx = p.x + dx[d];

					if (ny >= N || nx >= N || ny < 0 || nx < 0) {
						continue;
					}
					treeList[ny][nx].add(1);
					if (checked[ny][nx] == 0) {
						treeLoc.add(new Point(ny, nx));
						checked[ny][nx] = 1;
					}
				}
			}
		}

		fiveYeartree.clear();
	}

	static void winter() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				yang[i][j] += yangAdd[i][j];
			}
		}
	}

	static void printMap() {
		System.out.println("-----------------------");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(yang[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("-----------------------");
	}
}
