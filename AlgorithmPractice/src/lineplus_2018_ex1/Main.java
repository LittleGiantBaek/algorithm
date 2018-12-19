package lineplus_2018_ex1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int iniMoney = 20000;
	static int curFee;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int dis;

		while (st.hasMoreTokens()) {
			dis = Integer.parseInt(st.nextToken());
			if (calcFee(dis)) {
				continue;
			} else {
				System.out.println(iniMoney);
				return;
			}
		}

		System.out.println(iniMoney);
	}

	public static boolean calcFee(int dist) {
		int charge = 0;
		if (dist > 178 || dist < 4) {
			return false;
		} else if (dist < 41) {
			if (iniMoney - 720 < 0) {
				return false;
			} else {
				iniMoney -= 720;
				return true;
			}
		} else {
			dist -= 40;
			if (dist % 8 == 0) {
				dist = dist / 8;
			} else {
				dist = dist / 8 + 1;
			}

			charge = dist * 80;

			if (iniMoney - charge < 0) {
				return false;
			} else {
				iniMoney -= (charge + 720);
				return true;
			}
		}
	}

}
