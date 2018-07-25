//package swExpert_4008;
//
//import java.util.Queue;
//import java.util.Scanner;
//
//public class Solution {
//	static int tn;
//	static int add[];
//	static int sub[];
//	static int mul[];
//	static int div[];
//	static int nums[][];
//	static Queue<String> queue;
//	static int min;
//	static int max;
//	static int map[][][];
//	
//	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//
//		tn = sc.nextInt();
//		map = new int[tn][][];
//		
//		add = new int[tn];
//		sub = new int[tn];
//		mul = new int[tn];
//		div = new int[tn];
//		nums = new int[tn][];
//
//		for (int i = 0; i < tn; i++) {
//			int n = sc.nextInt();
//			nums[i] = new int[n];
//			map[i] = new int[n-1][n-1];
//			
//			add[i] = sc.nextInt();
//			sub[i] = sc.nextInt();
//			mul[i] = sc.nextInt();
//			div[i] = sc.nextInt();
//			for (int j = 0; j < n; j++) {
//				nums[i][j] = sc.nextInt();
//			}
//		}
//
//		for (int i = 0; i < tn; i++) {
//			cal(0, 0, nums[i][0]);
//		}
//	}
//}
