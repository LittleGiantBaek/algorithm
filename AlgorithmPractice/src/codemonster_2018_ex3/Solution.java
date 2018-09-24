package codemonster_2018_ex3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Solution {
	class Item {
		int itemNum;
		int gong;
		int che;

		public Item(int itemNum, int gong, int che) {
			this.itemNum = itemNum;
			this.gong = gong;
			this.che = che;
		}
	}

	class People {
		int item = -1;
		int che;

		public People(int item, int che) {
			this.item = item;
			this.che = che;
		}

	}

	List<Item> itemList = new ArrayList<>();
	List<People> cheList = new ArrayList<>();
	List<Integer> usedList = new ArrayList<>();

	public int[] solution(int[] healths, int[][] items) {
		int[] answer;

		int pNum = healths.length; // 사람수

		Arrays.sort(healths); // 사람 체력 순으로 정렬 //체력 동일할 수 있음

		for (int i = 0; i < pNum; i++) {
			if (healths[i] <= 100) {
				continue;
			} else {
				cheList.add(new People(-1, healths[i]));
			}
		}

		for (int i = 0; i < items.length; i++) {
			if (items[i][1] >= healths[pNum - 1]) { // 체력 최대값보다 아이템 체감 효과가 클경우
				continue;
			} else {
				itemList.add(new Item(i + 1, items[i][0], items[i][1]));
			}
		}

		Collections.sort(itemList, new Comparator<Item>() {
			@Override
			public int compare(Item o1, Item o2) {
				if (o1.che > o2.che) {
					return 1;
				} else if (o1.che < o2.che) {
					return -1;
				} else {
					return 0;
				}
			}
		}); // 아이템 체력순으로 오름차순 정렬

		for (int i = 0; i < cheList.size(); i++) {
			int che = cheList.get(i).che;
			Item t = maxGong(che);
			if (t != null) {
				itemList.remove(t);
				usedList.add(t.itemNum);
			}
		}

		answer = new int[usedList.size()];

		for (int i = 0; i < usedList.size(); i++) {
			answer[i] = usedList.get(i);
		}

		Arrays.sort(answer);

		return answer;
	}

	public Item maxGong(int che) {
		Item it = null;

		int max = -999;

		int index = 0;

		if (itemList.size() == 0) {
			return null;
		}

		while (che - itemList.get(index).che >= 100) {
			if (max < itemList.get(index).gong) {
				max = itemList.get(index).gong;
				it = itemList.get(index);
			}
			index++;
			if (itemList.size() == index) {
				break;
			}
		}

		return it;
	}
}