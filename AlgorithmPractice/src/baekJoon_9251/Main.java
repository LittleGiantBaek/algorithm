package baekJoon_9251;

import java.util.Scanner;

public class Main {
	static void LCS(String s1, String s2) {
		int len1 = s1.length();
		int len2 = s2.length();
		int [][] lcsArr = new int [len2+1][len1+1];
		
		for(int i = 1; i<=len2 ; i++) {
			for(int j=1;j<=len1; j++) {
				if(s2.charAt(i-1) == s1.charAt(j-1)) {
					lcsArr[i][j] += lcsArr[i-1][j-1] + 1;
				}
				else {
					int lcsMax = lcsArr[i][j-1] >= lcsArr[i-1][j] ? lcsArr[i][j-1]:lcsArr[i-1][j];
					lcsArr[i][j] = lcsMax;
				}
				if(i==len2 && j==len1) {
					System.out.println(lcsArr[i][j]);
				}
			}
		}
	}

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		String s1 = sc.next();
		String s2 = sc.next();
		
		LCS(s1,s2);
	}
}
