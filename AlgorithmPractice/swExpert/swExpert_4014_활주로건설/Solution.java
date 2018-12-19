package swExpert_4014_활주로건설;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * SW Expert 4014번 모의SW 역량테스트 시뮬레이션 , 브루트포스, 완전탐색
 * 
 * @author Baek
 *
 */
public class Solution {
	static int cnt;
	static int X;
	static int N;
	static int map[];
	static int slope[];
	static int slopeCpy[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int tc = Integer.parseInt(br.readLine());
		int t = 1;
		while (tc-- > 0) {
			cnt = 0;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			map = new int[N * N];
			slope = new int[N];
			slopeCpy = new int[N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i * N + j] = Integer.parseInt(st.nextToken());
				}
			}

			check(1); // 가로
			check(0); // 세로
			System.out.println("#" + t + " " + cnt);
			t++;
		} // while문 종료
	}// main 종료

	public static void check(int rc) {
		int tmp = 0;
		int m = 0;
		int y = 0;
		int x = 0;
		for (int i = 0; i < N; i++) {
			m = rc == 1 ? i * N : i;
			tmp = map[m]; // 제일 첫번째 값
			slope = Arrays.copyOf(slopeCpy, N);
			for (int j = 1; j < N; j++) {
				m = rc == 1 ? i * N + j : j * N + i; // 가로세로에 따라 i,j 위치 변경
				if (Math.abs(tmp - map[m]) > 1) { // 차이가 1이상이 나면 활주로를 못만듦
					break;
				}

				if (Math.abs(tmp - map[m]) == 1) {
					y = rc == 1 ? i : j;
					x = rc == 1 ? j : i;
					if (slope(tmp, map[m], y, x, rc)) { // 경사로를 만들 수 있다면 tmp 변경
						tmp = map[m];
					} else { // 경사로 만들 수 없으면 활주로 못만듦
						break;
					}
				}
				if (j == N - 1) {
					cnt++;
				}
			}
		}
	}

	public static boolean slope(int cur, int next, int row, int col, int rc) {
		int block = 0;
		int m = 0;
		int m2 = 0;

		if (next > cur) { // 다음 지역 경사가 더 높을경우 앞쪽에 경사로를 만들 수 있는지 검사
			m = rc == 1 ? col - 1 : row - 1; // 가로면 열 감소, 세로면 행감소

			for (int k = m; k >= 0; k--) {
				m2 = rc == 1 ? row * N + k : k * N + col;
				if (map[m2] == cur && slope[k] == 0) { // slope 겹침 방지
					block++;
					slope[k] = 1;
					if (block == X) {
						break;
					}
					continue;
				} else { // slope 겹치면 종료
					break;
				}
			}
			return block == X ? true : false;
		} else { // 다음지역 경사가 더 낮을경우
			m = rc == 1 ? col : row; // 가로면 열 증가, 세로면 행증가
			for (int k = m; k < N; k++) {
				m2 = rc == 1 ? row * N + k : k * N + col;
				if (map[m2] == next) {
					block++;
					slope[k] = 1; // 다음지역 경사가 낮을경우 slope 겹칠 수 없음
					if (block == X) {
						break;
					}
					continue;
				} else {
					break;
				}
			}
			return block == X ? true : false;
		}
	}
}
