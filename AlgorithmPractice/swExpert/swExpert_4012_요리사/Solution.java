package swExpert_4012_�丮��;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int map[][]; // ���� �� �����ϴ� map
	static Integer foods[]; // ���ĵ� �迭
	static int checked[]; // ����� ���� check �迭

	static ArrayList<Integer> foodAList = new ArrayList<>(); // A ����� ����Ʈ
	static ArrayList<Integer> foodBList = new ArrayList<>(); // B ����� ����Ʈ
	static int listChecked[]; // ����Ʈ ���� check �迭
	static int min = 9999;

	static int A; // A���ĸ�
	static int B; // B���� ��

	static int cnt;

	static int calCnt;

	static int calA[] = { -1, -1 };
	static int calB[] = { -1, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int tc = Integer.parseInt(br.readLine());
		int t = 1;

		while (tc-- > 0) {
			N = Integer.parseInt(br.readLine());
			foods = new Integer[N];
			checked = new int[N];
			listChecked = new int[N / 2];
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				foods[i] = i;
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			dfs(0);
			System.out.println("#" + t + " " + min);
			min = 9999;
			t++;
		}
	}

	public static void dfs(int index) {
		if (cnt == N / 2) {
			listSet();
			cal(0);
			min = Math.min(min, Math.abs((A - B)));
			A = 0;
			B = 0;
			return;
		}
		for (int i = index; i < N; i++) {
			if (cnt == 0 && i > 0) { // ������ �����°��̱� ������ 1�� ���ո� �˻��ϸ� ��
				return;
			}
			if (checked[i] == 0) {
				checked[i] = 1;
				foodAList.add(foods[i]);
				cnt++;
				dfs(i);
				cnt--;
				foodAList.remove(foods[i]);
				checked[i] = 0;
			}
		}
	}

	public static void listSet() {
		foodBList.clear();
		for (int i = 0; i < N; i++) {
			if (!foodAList.contains(foods[i])) {
				foodBList.add(foods[i]);
			}
		}
	}

	public static void cal(int index) {
		if (calCnt == 2) {
			A += map[calA[0]][calA[1]];
			A += map[calA[1]][calA[0]];

			B += map[calB[0]][calB[1]];
			B += map[calB[1]][calB[0]];
			
			return;
		}

		for (int i = index; i < N / 2; i++) {
			if (calA[0] == -1) {
				calA[0] = foodAList.get(i);
				calB[0] = foodBList.get(i);
				calCnt++;
				cal(i + 1);
				calA[0] = -1;
				calCnt--;
			} else {
				calA[1] = foodAList.get(i);
				calB[1] = foodBList.get(i);
				calCnt++;
				cal(i+1);
				calCnt--;
			}
		}
	}

	public static void printMap(int map[][]) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
}
