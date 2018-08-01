package baekJoon_13460_����Ż��2;

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

	static int dy[] = { -1, 0, 1, 0 }; // �� �� �� �� �ð����
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
		if (re != 1) { // ������ �߰����� ����ä ������ ��� or ���ۿ� ����, �Ķ��� �Բ� �����
			min = -1;
		}

		System.out.println(min);
	}

	public static void bfs() {
		int ry, rx, by, bx; // Queue���� ���� ���� ��ǥ
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

				for (int j = 0; j < 4; j++) { // �����¿� �˻�
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
		} // while�� ����
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
		while (true) { // '#', 'O' ���� �����Ҷ����� �̵�
			// ���� �ȿ� ������������ �̵�
			if (rny > 0 && rnx > 0 && rny < row - 1 && rnx < col - 1) {
				rny += dy;
				rnx += dx;
			}
			if (bny > 0 && bnx > 0 && bny < row - 1 && bnx < col - 1) {
				bny += dy;
				bnx += dx;
			}

			// �Ķ����� ���� O�� ã�� ��� Ž�� ���� (btmpy == - 1 �ǹ� : �Ķ����� ���� �������� ����.)
			if (map.get(bny).get(bnx) == 'O' && btmpy == -1) {
				break;
			}
			// ���� ���� O�� ã�� ���
			if (map.get(rny).get(rnx) == 'O' && rtmpy == -1) {
				re = 1;
				// �Ķ����� ���� �̹� ������� ����
				if (btmpy != -1) {
					break;
				}
				// �̵����� �Ķ����� �ļ����� �������� �˻�
				while (bny > 0 && bnx > 0 && bny < row - 1 && bnx < col - 1) {
					// ���� �����ٸ� ����
					if (map.get(bny).get(bnx) == '#') {
						break;
					}
					// �Բ� ���ۿ� �� ��� return flag ����
					if (map.get(bny).get(bnx) == 'O') {
						re = 0;
						break;
					}
					bny += dy;
					bnx += dx;
				}
				break;
			}

			// �Ķ����� ���� ���� ���
			if (map.get(bny).get(bnx) == '#' && btmpy == -1) {
				bny -= dy;
				bnx -= dx;
				// ������ ���� ���� �������� �������
				if (rtmpy == -1) {
					btmpy = bny;
					btmpx = bnx;
				} else if (rtmpy == bny && rtmpx == bnx) { // �������� �������ְ� �Ķ����� �������� ��ǥ�� �����Ұ�� ��ĭ �ڷ� �̵�
					if (j == 0) {
						// ��
						bny++;
					} else if (j == 1) {
						// ��
						bnx--;
					} else if (j == 2) {
						// ��
						bny--;
					} else {
						// ��
						bnx++;
					}
					if (checked[rtmpy][rtmpx][bny][bnx] == true) { // �湮 üũ
						break;
					}
					rq.offer(new Point(rtmpy, rtmpx));
					bq.offer(new Point(bny, bnx));
					checked[rtmpy][rtmpx][bny][bnx] = true;
					break;
				} else { // �������� �Ķ����� ���� �ٸ� ���� �����Ұ�� ť�� �ٷ� ����
					if (checked[rtmpy][rtmpx][bny][bnx] == true) { // �湮 üũ
						break;
					}
					rq.offer(new Point(rtmpy, rtmpx));
					bq.offer(new Point(bny, bnx));
					checked[rtmpy][rtmpx][bny][bnx] = true;
					break;
				}
			}
			// �������� ���� �����Ұ��
			if (map.get(rny).get(rnx) == '#' && rtmpy == -1) {
				rny -= dy;
				rnx -= dx;
				if (btmpy == -1) { // �Ķ����� ���� �������� �������
					rtmpy = rny;
					rtmpx = rnx;
				} else if (btmpy == rny && btmpx == rnx) {// �Ķ����� �������ְ� �������� ���������� �����Ұ�� ������ ��ĭ �ڷ� �̵�
					if (j == 0) {
						// ��
						rny++;
					} else if (j == 1) {
						// ��
						rnx--;
					} else if (j == 2) {
						// ��
						rny--;
					} else {
						// ��
						rnx++;
					}
					if (checked[rny][rnx][btmpy][btmpx] == true) { // �湮 üũ
						break;
					}
					rq.offer(new Point(rny, rnx));
					bq.offer(new Point(btmpy, btmpx));
					checked[rny][rnx][btmpy][btmpx] = true;
					break;
				} else { // �� ���� ���� ������ �ٸ���� ť�� ����
					if (checked[rny][rnx][btmpy][btmpx] == true) {
						break;
					}
					rq.offer(new Point(rny, rnx));
					bq.offer(new Point(btmpy, btmpx));
					checked[rny][rnx][btmpy][btmpx] = true;
					break;
				}
			} // if�� ��
		} // while�� ��
	}// �Լ� ��

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
