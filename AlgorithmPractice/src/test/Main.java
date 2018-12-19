package test;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
	static class SortTest {
		int idx;
		int num;
		String eng;
		String kor;

		public SortTest(int idx, int num, String eng, String kor) {
			this.idx = idx;
			this.num = num;
			this.eng = eng;
			this.kor = kor;
		}

	}

	public static void main(String[] args) {
		SortTest[] s = new SortTest[10];
		for (int i = 0; i < 10; i++) {
			int a = (int) (Math.random() * 100) % 10;
			String b = Character.toString((char) ('a' + a));
			String c = Character.toString((char) ('가' + a + 30));
			s[i] = new SortTest(i, a, b, c);
		}

		System.out.println("-------------------sort전----------------------------");
		for (int i = 0; i < 10; i++) {
			System.out.println(s[i].idx + "," + s[i].num + "," + s[i].eng + "," + s[i].kor);
		}
		System.out.println("-----------------------------------------------");
		System.out.println();
		System.out.println("-------------------sort후----------------------------");

		Arrays.sort(s, new Comparator<SortTest>() {
			@Override
			public int compare(SortTest o1, SortTest o2) {
				if(o1.eng.compareTo(o2.eng)>1) {
					return 1;
				}else if(o1.eng.compareTo(o2.eng)==0) {
					if(o1.idx>o2.idx) {
						return -1;
					}else if(o1.idx < o2.idx) {
						return 1;
					}else {
						return 0;
					}
				}else {
					return -1;
				}
			}
		}); // compare  return 1 이면 자리 바꿈,  -1이면 안바꿈 , 0이어도 안바꿈 , 조건에 조건 추가하여 2중 sort 가능
		
		/*Arrays.sort(s,new Comparator<SortTest>() {
			@Override
			public int compare(SortTest o1, SortTest o2) {
				if(o1.num == o2.num) {
					System.out.println("o2.idx=="+o2.idx);
					System.out.println("o1.idx=="+o1.idx);
					return o2.idx-o1.idx;
				}
				return 0;
			}
		});*/
		
		for (int i = 0; i < 10; i++) {
			System.out.println(s[i].idx + "," + s[i].num + "," + s[i].eng + "," + s[i].kor);
		}

		System.out.println("-----------------------------------------------");

	}
}
