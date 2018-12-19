package baekJoon_14499_주사위굴리기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * BaekJoon 14499번 주사위 굴리기
 * SW 검정 대비
 * 규칙성 찾기
 * @author Baek
 *
 */
public class Main {
	static int col, row;
	static int cy, cx; //현재 주사위 위치
	static int tc;
	static int map[];
	static int dy[] = { -1, 0, 0, -1, 1 }; //동서북남 순서
	static int dx[] = { -1, 1, -1, 0, 0 };
	static int dir; //동서북남
	static int dice[] = new int[6]; //주사위 값
	static int ny, nx;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		cy = Integer.parseInt(st.nextToken());
		cx = Integer.parseInt(st.nextToken());
		tc = Integer.parseInt(st.nextToken());
		map = new int[row * col];
		for (int i = 0; i < row; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < col; j++) {
				map[i * col + j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < tc; i++) {
			dir = Integer.parseInt(st.nextToken());
			ny = cy + dy[dir];
			nx = cx + dx[dir];
			if (ny >= row || nx >= col || ny < 0 || nx < 0) {
				continue;
			}
			cy = ny;
			cx = nx;
			nextDice(dir);
			if (map[cy * col + cx] == 0) {
				map[cy * col + cx] = dice[2];
			} else {
				dice[2] = map[cy * col + cx];
				map[cy * col + cx] = 0;
			}
			System.out.println(dice[0]);
		}

	}

	public static void nextDice(int dir) {
		int tmp = 0;
		switch (dir) {
		case 1: { //동
			tmp = dice[5];
			dice[5] = dice[0];
			dice[0] = dice[4];
			dice[4] = dice[2];
			dice[2] = tmp;
			break;
		}
		case 2: { //서
			tmp = dice[4];
			dice[4] = dice[0];
			dice[0] = dice[5];
			dice[5] = dice[2];
			dice[2] = tmp;
			break;
		}
		case 3: { //북
			tmp = dice[3];
			dice[3] = dice[0];
			dice[0] = dice[1];
			dice[1] = dice[2];
			dice[2] = tmp;
			break;
		}
		case 4: { //남
			tmp = dice[1];
			dice[1] = dice[0];
			dice[0] = dice[3];
			dice[3] = dice[2];
			dice[2] = tmp;
			break;
		}
		}
	}
}
