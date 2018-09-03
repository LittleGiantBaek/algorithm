package kakao_2018_blind_¸ðÀÇ2;

public class Solution {
	static int check[];

	public boolean solution(int[] arr) {
		boolean answer = true;

		int size = arr.length;
		check = new int[size + 1];
		int next = 0;
		for (int i = 0; i < size; i++) {
			next = arr[i];
			if(next > size) {
				return false;
			}
			if (check[next] != 0) {
				return false;
			} else {
				check[next] = 1;
			}
		}
		return answer;
	}
}
