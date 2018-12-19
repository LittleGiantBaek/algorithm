package swExpert_1768_숫자야구게임_B형대비;

import swExpert_1768_숫자야구게임_B형대비.Solution.Result;

class UserSolution {
	public final static int N = 4;
	static int num[] = {0,1,2,3,4,5,6,7,8,9};
	static int nums[][];
	public void doUserImplementation(int guess[]) {
		Result re;
		while(true) {
			for(int i = 0 ;i <N;i++) {
				guess[i] = num[i];
			}
			re = Solution.query(guess);
			
			if(re.strike == 4) {
				return;
			}
			
		}
	}
	
	public void makeList() {
		
	}
	
}