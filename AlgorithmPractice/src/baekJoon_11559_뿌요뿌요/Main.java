package baekJoon_11559_뿌요뿌요;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * baekJoon 11559번 뿌요뿌요
 * dfs
 * @author Baek
 *
 */
public class Main {
	static ArrayList<ArrayList<Character>> map = new ArrayList<>(); // 열 행
	static ArrayList<Node> nodeList = new ArrayList<>(); // 같은 색 구슬 좌표 저장 리스트

	static boolean checked[][] = new boolean[12][6];

	static int dy[] = { -1, 0, 1, 0 };
	static int dx[] = { 0, -1, 0, 1 };
	static int ny, nx;

	static int cnt = 1; // 연속된 구슬 개수
	static boolean status = true; // 구슬 제거가 되는지 여부 상태변수
	static int explosion; // 폭발 회수

	public static class Node {
		int y, x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input;

		for (int j = 0; j < 6; j++) {
			map.add(new ArrayList<>());
		}
		for (int i = 0; i < 12; i++) {
			input = br.readLine();
			for (int j = 0; j < 6; j++) {
				map.get(j).add(input.charAt(j));
			}
		} // map 생성

		while (status) {
			status = false;
			// 연쇄 1번에 for문 한번
			for (int i = 11; i >= 0; i--) {
				for (int j = 0; j < 6; j++) {
					if (map.get(j).get(i) == 'C' || map.get(j).get(i) == '.') { // 한번에 같이 터지는 경우 처리 위해 임시로 C 문자로 변경
						continue;
					}
					cnt = 1;
					for (int k = 0; k < 12; k++) {
						Arrays.fill(checked[k], false); // check 배열 초기화
					}
					checked[i][j] = true;
					dfs(i, j);
					if (cnt >= 4) { // 연결된 구슬 개수
						status = true; // 연쇄가 한번이라도 있으면 상태값 true
						map.get(j).set(i, 'C');
						for (int n = 0; n < nodeList.size(); n++) {
							ny = nodeList.get(n).y;
							nx = nodeList.get(n).x;
							map.get(nx).set(ny, 'C');
						}
					}
					nodeList.clear();
				}
			}
			if (status) { // 연쇄폭발 실행
				nextMap();
				explosion++;
			}
		}

		System.out.println(explosion);
	}

	public static void dfs(int y, int x) {
		for (int d = 0; d < 4; d++) {
			ny = y + dy[d];
			nx = x + dx[d];
			if (ny < 0 || nx < 0 || ny > 11 || nx > 5 || checked[ny][nx] == true) {
				continue;
			}
			if (map.get(nx).get(ny) == map.get(x).get(y)) {
				cnt++;
				checked[ny][nx] = true;
				nodeList.add(new Node(ny, nx));
				dfs(ny, nx);
			}
		}
	}

	public static void nextMap() {
		for (int j = 0; j < 6; j++) { // 열단위로 아래 행부터 검사
			for (int i = 11; i >= 0; i--) {
				if (map.get(j).get(i) == '.') { // .일경우 위에는 존재할 수 없음
					break;
				}
				if (map.get(j).get(i) == 'C') {
					map.get(j).remove(i);
					map.get(j).add(0, '.');
					nextMap(); // 맵이 이동하므로 재귀로 처음부터 다시 검사
				}
			}
		}
	}

	public static void printMap() {
		System.out.println("-------------------");
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 6; j++) {
				System.out.print(map.get(j).get(i) + " ");
			}
			System.out.println();
		}
		System.out.println("-------------------");
		System.out.println();
	}
}
