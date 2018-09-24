package codemonster_2018_ex1;

import java.util.Arrays;

public class Solution {
	public int solution(int[] people, int[] tshirts) {
		int answer = 0;
		int size = tshirts.length;

		Arrays.sort(people);
		Arrays.sort(tshirts);
		int pIdx = 0;

		for (int i = 0; i < size; i++) {
			if (people[pIdx] > tshirts[i]) {
				continue;
			} else {
				answer++;
				pIdx++;
			}
		}

		return answer;
	}
}