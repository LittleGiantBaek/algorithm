package baekJoon_9095;

import java.util.Scanner;

public class baekJoon_9095 {

	public static int numAdd(int num) {
		int cnt = 0;
		if(num==0)
			return 1;
		if(num==1)
			return 1;
		if(num==2)
			return 2;
		cnt = numAdd(num-1) + numAdd(num-2) + numAdd(num-3);
		return cnt;
	}
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int tcNum = sc.nextInt();
		int []rs = new int [tcNum];
		
		for(int i=0;i<tcNum; i++) {
			int test = sc.nextInt();
			rs[i] = numAdd(test);
		}
		for(int re : rs) {
			System.out.println(re);
		}
	}
}
