package baekJoon_13460_구슬탈출2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int row, col;
	static ArrayList<ArrayList<Character>> map = new ArrayList<>();
	static boolean checked[][][][];
	static Queue<Point> rq = new LinkedList<>();
	static Queue<Point> bq = new LinkedList<>();
	static int qsize;
	static int min;

	static int re;
	static int rny, rnx; // Red next point
	static int bny, bnx; // Blue next point

	static int rtmpy, rtmpx; // Red temporary point
	static int btmpy, btmpx; // Blue temporary point

	static int dy[] = { -1, 0, 1, 0 }; // 상 우 하 좌 시계방향
	static int dx[] = { 0, 1, 0, -1 };

	static class Point {
		int y, x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		checked = new boolean[row][col][row][col];
		int sry = 0;
		int srx = 0;
		int sby = 0;
		int sbx = 0;
		for (int i = 0; i < row; i++) {
			map.add(new ArrayList<>());
			String s = br.readLine();
			for (int j = 0; j < col; j++) {
				Character c = s.charAt(j);
				map.get(i).add(c);
				if (c == 'R') {
					rq.add(new Point(i, j));
					sry = i;
					srx = j;
				} else if (c == 'B') {
					bq.add(new Point(i, j));
					sby = i;
					sbx = j;
				}
			}
		}
		checked[sry][srx][sby][sbx] = true;
		bfs();
		if (re != 1) { // 구멍을 발견하지 못한채 끝났을 경우 or 구멍에 빨강, 파랑공 함께 들어갈경우
			min = -1;
		}

		System.out.println(min);
	}

	public static void bfs() {
		int ry, rx, by, bx; // Queue에서 꺼낸 공의 좌표
		ry = rx = by = bx = 0;
		while (!rq.isEmpty()) {
			qsize = rq.size();
			for (int i = 0; i < qsize; i++) {
				Point pointR = rq.poll();
				Point pointB = bq.poll();
				ry = pointR.y;
				rx = pointR.x;
				by = pointB.y;
				bx = pointB.x;

				for (int j = 0; j < 4; j++) { // 상하좌우 검색
					move(ry, rx, by, bx, dy[j], dx[j], j);
					if (re == 1) {
						min++;
						if (min == 11) {
							re = -1;
						}
						return;
					}
				}
			}
			min++;
			if (min == 11) {
				re = -1;
				return;
			}
		} // while문 종료
	}

	private static void move(int ry, int rx, int by, int bx, int dy, int dx, int j) {
		rny = ry;
		rnx = rx;
		bny = by;
		bnx = bx;
		rtmpy = -1;
		rtmpx = -1;
		btmpy = -1;
		btmpx = -1;
		while (true) { // '#', 'O' 까지 도달할때까지 이동
			// 범위 안에 있을때까지만 이동
			if (rny > 0 && rnx > 0 && rny < row - 1 && rnx < col - 1) {
				rny += dy;
				rnx += dx;
			}
			if (bny > 0 && bnx > 0 && bny < row - 1 && bnx < col - 1) {
				bny += dy;
				bnx += dx;
			}

			// 파랑공이 먼저 O를 찾을 경우 탐색 종료 (btmpy == - 1 의미 : 파란공이 벽을 만난적이 없다.)
			if (map.get(bny).get(bnx) == 'O' && btmpy == -1) {
				break;
			}
			// 빨간 공이 O를 찾을 경우
			if (map.get(rny).get(rnx) == 'O' && rtmpy == -1) {
				re = 1;
				// 파란공은 벽을 이미 만난경우 종류
				if (btmpy != -1) {
					break;
				}
				// 이동중인 파란공이 후속으로 들어오는지 검색
				while (bny > 0 && bnx > 0 && bny < row - 1 && bnx < col - 1) {
					// 벽에 막힌다면 종료
					if (map.get(bny).get(bnx) == '#') {
						break;
					}
					// 함께 구멍에 들어갈 경우 return flag 변경
					if (map.get(bny).get(bnx) == 'O') {
						re = 0;
						break;
					}
					bny += dy;
					bnx += dx;
				}
				break;
			}

			// 파랑공이 벽을 만날 경우
			if (map.get(bny).get(bnx) == '#' && btmpy == -1) {
				bny -= dy;
				bnx -= dx;
				// 빨강이 아직 벽에 도착하지 않은경우
				if (rtmpy == -1) {
					btmpy = bny;
					btmpx = bnx;
				} else if (rtmpy == bny && rtmpx == bnx) { // 빨간공이 도착해있고 파란공이 빨간공의 좌표와 동일할경우 한칸 뒤로 이동
					if (j == 0) {
						// 상
						bny++;
					} else if (j == 1) {
						// 우
						bnx--;
					} else if (j == 2) {
						// 하
						bny--;
					} else {
						// 좌
						bnx++;
					}
					if (checked[rtmpy][rtmpx][bny][bnx] == true) { // 방문 체크
						break;
					}
					rq.offer(new Point(rtmpy, rtmpx));
					bq.offer(new Point(bny, bnx));
					checked[rtmpy][rtmpx][bny][bnx] = true;
					break;
				} else { // 빨간공과 파란공이 서로 다른 벽에 도착할경우 큐에 바로 삽입
					if (checked[rtmpy][rtmpx][bny][bnx] == true) { // 방문 체크
						break;
					}
					rq.offer(new Point(rtmpy, rtmpx));
					bq.offer(new Point(bny, bnx));
					checked[rtmpy][rtmpx][bny][bnx] = true;
					break;
				}
			}
			// 빨간공이 벽에 도착할경우
			if (map.get(rny).get(rnx) == '#' && rtmpy == -1) {
				rny -= dy;
				rnx -= dx;
				if (btmpy == -1) { // 파란공이 아직 도착하지 않은경우
					rtmpy = rny;
					rtmpx = rnx;
				} else if (btmpy == rny && btmpx == rnx) {// 파란공이 도착해있고 빨간공의 도착지점과 동일할경우 빨간공 한칸 뒤로 이동
					if (j == 0) {
						// 상
						rny++;
					} else if (j == 1) {
						// 우
						rnx--;
					} else if (j == 2) {
						// 하
						rny--;
					} else {
						// 좌
						rnx++;
					}
					if (checked[rny][rnx][btmpy][btmpx] == true) { // 방문 체크
						break;
					}
					rq.offer(new Point(rny, rnx));
					bq.offer(new Point(btmpy, btmpx));
					checked[rny][rnx][btmpy][btmpx] = true;
					break;
				} else { // 두 공의 도착 지점이 다를경우 큐에 삽입
					if (checked[rny][rnx][btmpy][btmpx] == true) {
						break;
					}
					rq.offer(new Point(rny, rnx));
					bq.offer(new Point(btmpy, btmpx));
					checked[rny][rnx][btmpy][btmpx] = true;
					break;
				}
			} // if문 끝
		} // while문 끝
	}// 함수 끝

	public static void printMap() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.print(map.get(i).get(j));
			}
			System.out.println();
		}

		System.out.println("----------------------------------------");
	}
}
