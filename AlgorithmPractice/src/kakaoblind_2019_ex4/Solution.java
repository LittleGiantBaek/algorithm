package kakaoblind_2019_ex4;

import java.util.ArrayList;

class Solution {

	 class Food {
		int num;
		int time;

		public Food(int num, int time) {
			this.num = num;
			this.time = time;
		}

	}

	public int solution(int[] food_times, long k) {
		int answer = 0;
		int index = 0;

		ArrayList<Food> foodList = new ArrayList<>();

		for (int i = 0; i < food_times.length; i++) {
			foodList.add(new Food(i + 1, food_times[i]));
			foodList.get(index).time--;

			if (foodList.get(index).time == 0) {
				foodList.remove(index);
			} else {
				index = (index + 1) % (foodList.size() + 1);
				if (i == food_times.length - 1) {
					index--;
				}
			}
			k--;
			if (k == 0) {
				break;
			}
		}
		if (k != 0) {
			while (k > 0) {
				foodList.get(index).time--;
				if (foodList.get(index).time == 0) {
					foodList.remove(index);
					index--;
				} else {
					index = (index + 1) % foodList.size();
				}
				k--;
			}
		}
		if (foodList.size() != 0) {
			return answer = foodList.get(index).num;
		} else {
			return -1;
		}
	}

}