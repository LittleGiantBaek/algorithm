package kakaoblind_2019_ex2;

import java.util.ArrayList;

class Solution {
	double stage[];
	double stageCount[];
	int sortStage[];

	class StageRate {
		int stage;
		double rate;

		public StageRate(int stage, int rate) {
			this.stage = stage;
			this.rate = rate;
		}

	}

	public int[] solution(int N, int[] stages) {
		stage = new double[N + 1]; // stage에 클리어하지 못하고 머물러 있는 사람수
		stageCount = new double[N + 1]; // 해당 스테이지에 도달한 사람 수

		sortStage = new int[N];

		ArrayList<StageRate> list = new ArrayList<>(); // stage별 실패율 객체

		for (int i = 0; i < N + 1; i++) {
			list.add(new StageRate(i, 0));
		}

		for (int i = 0; i < stages.length; i++) {
			if (stages[i] != N + 1) {
				stage[stages[i]]++;
				for (int j = 0; j <= stages[i]; j++) {
					stageCount[j]++;
				}
			} else {
				for (int j = 0; j < N; j++) {
					stageCount[j]++;
				}
			}
		}

		for (int i = 0; i < N + 1; i++) {
			if (stageCount[i] != 0) {
				list.get(i).rate = (stage[i] / stageCount[i]);
			}
		}
		list.remove(0);

		quickSort(list, 0, list.size() - 1);

		for (int i = 0; i < list.size() - 1; i++) {
			int index = i;
			int tmpindex = i;

			int min = 999;
			int minindex = i;

			while (index < list.size() - 1 && list.get(index).rate == list.get(index + 1).rate) {
				index++;
				if (min > list.get(index).stage) {
					min = list.get(index).stage;
					minindex = index;
				}
			}
			if (min != 999) {
				int tmpStage = list.get(tmpindex).stage;
				list.get(tmpindex).stage = list.get(minindex).stage;
				list.get(minindex).stage = tmpStage;

				double tmpRate = list.get(tmpindex).rate;
				list.get(tmpindex).rate = list.get(minindex).rate;
				list.get(minindex).rate = tmpRate;
			}
		}

		for (int i = 0; i < list.size(); i++) {
			sortStage[i] = list.get(i).stage;
		}
		return sortStage;
	}

	public int part(ArrayList<StageRate> stageList, int left, int right) {

		int pivot = right;

		while (left < right) {
			while (stageList.get(left).rate >= stageList.get(pivot).rate && (left < right)) {
				left++;
			}
			while (stageList.get(right).rate <= stageList.get(pivot).rate && (left > right)) {
				right--;
			}
			if (left < right) {
				int tmpStage = stageList.get(left).stage;
				stageList.get(left).stage = stageList.get(right).stage;
				stageList.get(right).stage = tmpStage;

				double tmpRate = stageList.get(left).rate;
				stageList.get(left).rate = stageList.get(right).rate;
				stageList.get(pivot).rate = tmpRate;
			}
		}
		int tmpStage = stageList.get(right).stage;
		stageList.get(right).stage = stageList.get(pivot).stage;
		stageList.get(right).stage = tmpStage;

		double tmpRate = stageList.get(right).rate;
		stageList.get(right).rate = stageList.get(pivot).rate;
		stageList.get(pivot).rate = tmpRate;

		return right;
	}

	public void quickSort(ArrayList<StageRate> stageList, int left, int right) {
		if (left < right) {
			int pivotNewIndex = part(stageList, left, right);

			quickSort(stageList, left, pivotNewIndex - 1);
			quickSort(stageList, pivotNewIndex + 1, right);
		}

	}

}