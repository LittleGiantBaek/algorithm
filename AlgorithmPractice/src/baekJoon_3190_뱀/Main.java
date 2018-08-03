package baekJoon_3190_��;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int map[][];

	static Queue<Dir> headQ = new LinkedList<>();
	static Queue<Dir> tailQ = new LinkedList<>();

	static int dy[] = { 0, 1, 0, -1 };
	static int dx[] = { 1, 0, -1, 0 };

	static Head h = new Head(1, 1, 0);
	static Tail t = new Tail(1, 1, 0);

	static int totalTime;

	static int nhy, nhx; // next head y,x
	static int nty, ntx; // next tail y,x

	static class Dir {
		int time;
		int dir;

		public Dir(int time, int dir) {
			this.time = time;
			this.dir = dir;
		}
	}

	static class Head {
		int hy, hx;
		int dir;

		public Head(int hy, int hx, int dir) {
			this.hy = hy;
			this.hx = hx;
			this.dir = dir;
		}
	}

	static class Tail {
		int ty, tx;
		int dir;

		public Tail(int ty, int tx, int dir) {
			this.ty = ty;
			this.tx = tx;
			this.dir = dir;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N + 2][N + 2];

		int ti = 0;
		int tmpTi = 0;
		int term = 0;

		// �������
		for (int i = 0; i < N + 2; i++) {
			map[0][i] = 1;
			map[i][0] = 1;
			map[i][N + 1] = 1;
			map[N + 1][i] = 1;
		}

		int apple = Integer.parseInt(br.readLine());

		for (int i = 0; i < apple; i++) {
			st = new StringTokenizer(br.readLine());
			int y, x;
			y = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			map[y][x] = 3;
		}

		int di = Integer.parseInt(br.readLine());

		int direction = 0; // �ʱ���� ������

		for (int i = 0; i < di; i++) {
			st = new StringTokenizer(br.readLine());
			tmpTi = Integer.parseInt(st.nextToken());
			term = tmpTi - ti;
			ti = tmpTi;
			String s = st.nextToken();
			if (s.equals("D")) { // �ð����
				if (direction == 3) {
					direction = 0;
				} else {
					direction += 1;
				}
			} else { // �ݽð����
				if (direction == 0) {
					direction = 3;
				} else {
					direction -= 1;
				}
			}
			headQ.offer(new Dir(term, direction));
			tailQ.offer(new Dir(term, direction));
		}

		map[1][1] = 2;

		snake();

		System.out.println(totalTime);
	}

	public static void snake() {
		int size = headQ.size();
		for (int i = 0; i < size; i++) {
			Dir d = headQ.poll();
			for (int j = 0; j < d.time; j++) {
				if (moveHead()) {
					continue;
				} else {
					return;
				}
			}
			// �̵��� �Ϸ������� snake�� �̵������� ���� �̵��������� �ٲ��ش�.
			h.dir = d.dir;
		}
		while (true) {
			if (moveHead()) {
				continue;
			} else {
				return;
			}
		}
	}

	public static boolean moveHead() {

		totalTime++; // �̵��ø��� �ð� ����
		// printMap();

		// ������ũ�� ���� �̵��������� 1ĭ �̵���Ų��.
		nhy = h.hy + dy[h.dir];
		nhx = h.hx + dx[h.dir];

		// �̵��߿� ���� ������� ����
		if (map[nhy][nhx] == 1 || map[nhy][nhx] == 2) {
			return false;
		}

		// ����� �տ� �ִٸ� �����̵� X �Ӹ� ��ǥ �̵�
		else if (map[nhy][nhx] == 3) {
			h.hy = nhy;
			h.hx = nhx;
			map[nhy][nhx] = 2;
			return true;
		}

		else {
			// ����� ���ٸ� ������ ��ĭ �ٿ��ش�.
			h.hy = nhy;
			h.hx = nhx;
			map[nhy][nhx] = 2;
			moveTail();
			return true;
		}
	}

	public static void moveTail() {

		if (!tailQ.isEmpty()) {
			Dir d = tailQ.peek();
			if (d.time == 0) { // tail�� �̵���θ� �ٲ��ְ� tailQ ��� ����
				t.dir = d.dir;
				tailQ.poll();
			}
		}
		// ������ũ�� ���� �̵��������� 1ĭ �̵���Ų��.
		nty = t.ty + dy[t.dir];
		ntx = t.tx + dx[t.dir];

		map[t.ty][t.tx] = 0; // ���� ��ǥ 0���� ����

		t.ty = nty;
		t.tx = ntx;
		if (!tailQ.isEmpty()) {
			tailQ.peek().time--;
		}
	}

	public static void printMap() {
		for (int i = 0; i < N + 2; i++) {
			for (int j = 0; j < N + 2; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
}
