package kakao_2018_blind_¸ğÀÇ4;

public class Solution {
	public int solution(int[][] board) {
		int answer = -9999;
		int row = board.length;
		int col = board[0].length;
		int ar = 0;
		int ac = 0;
		
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if(board[i][j] == 1) {
					int tmp = j;
					while(board[i][j] == 1) {
						j++;
					}
					
				}
			}
		}
		return answer;
	}
	public void dfs() {
		
	}
}
