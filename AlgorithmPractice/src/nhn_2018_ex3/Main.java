package nhn_2018_ex3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static class Coin{
		int price;
		int num;
		public Coin(int price, int num) {
			super();
			this.price = price;
			this.num = num;
		}
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int days = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		int price[] = new int[days];
		int max;
		int maxIndex;
		for (int i = 0; i < days; i++) {
			price[i] = Integer.parseInt(st.nextToken());
		}

		
		
		Coin dp[][] = new Coin[days][3]; // 사거나 팔거나 아무것도 안함 수익률
		
		int coinNum = 0;

		dp[0][0] = new Coin(-Integer.parseInt(st.nextToken()),1); //살경우 수익률
		dp[0][1] = new Coin(0,0); //팔경우
		dp[0][2] = new Coin(0,0); //아무것도 안할경우

		Coin[][] d = new Coin[days][2]; // N번째 날의 최대 수익률 & coin개수;
		
		
		for(int i = 1 ; i< days;i++) {
			dp[i][0] = d[i][1] ;
		}

	}

}