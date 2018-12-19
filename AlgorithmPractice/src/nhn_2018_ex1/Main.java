package nhn_2018_ex1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

	static int curCardCnt;
	static ArrayList<Integer> cardList = new ArrayList<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int C = 0; // 카드 개수
		int P = 0; // 섞는 횟수
		int N; // 위아래 제외할 카드수
		C = Integer.parseInt(br.readLine());
		P = Integer.parseInt(br.readLine());
		curCardCnt = C;

		for (int i = 1; i <= C; i++) {
			cardList.add(i);
		}

		for (int i = 0; i < P; i++) {
			N = Integer.parseInt(br.readLine());
			curCardCnt = C;
			mix(N);
		}

		for (int i = 0; i < 5; i++) {
			System.out.println(cardList.get(i));
		}

	}

	public static void mix(int N) {
		int cardNum = 0;
		for (int i = N; i < curCardCnt - N; i++) {
			cardNum = cardList.get(i);
			cardList.remove(i);
			cardList.add(i - N, cardNum);
		}
		if (curCardCnt - (2 * N) > 2 * N) {
			curCardCnt = curCardCnt - (2 * N);
			mix(N);
		}
	}

}
