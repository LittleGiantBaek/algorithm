package codemonster_2018_ex2;

import java.util.ArrayList;

public class Solution {
	int row, col;
	ArrayList<Node> houseNodes = new ArrayList<>();
	ArrayList<Node> facNodes = new ArrayList<>();
	class Node {
		int y, x;

		public Node(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}

	public int solution(int N, int[][] house) {
		int answer = 0;
		row = house.length;
		col = house[0].length;

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
					
			}
		}

		return answer;
	}

	public void dfs() {
		
	}
}