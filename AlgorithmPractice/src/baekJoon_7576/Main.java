package baekJoon_7576;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int row;
	static int col;
	static int map[][];
	static boolean checked[][];
	static int zeroCnt;
	static Queue<Node> queue;
	
	static class Node{
		int x;
		int y;
		public Node(int y,int x) {
			this.x = x;
			this.y = y;
		}
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
	}
	public static void bfs(int y,int x) {
		int year = 0;
		
		while(!queue.isEmpty()) {
			int size = queue.size();
			for(int i = 0; i< size;i++) {
				Node pNode = queue.poll();
				int py=pNode.getY();
				int px=pNode.getX();
				if(py<row-1) {
					if(map[py+1][px] == 0 && checked[py+1][px] == false) {
						checked[py+1][px] = true;
						map[py+1][px] = 1;
						queue.offer(new Node(py+1, px));
					}
				}
				if(px<col-1) {
					if(map[py][px+1] == 0 && checked[py][px+1] == false) {
						checked[py][px+1] = true;
						map[py][px+1] = 1;
						queue.offer(new Node(py, px+1));
					}
				}
				if(py>0) {
					if(map[py-1][px] == 0 && checked[py-1][px] == false) {
						checked[py-1][px] = true;
						map[py-1][px] = 1;
						queue.offer(new Node(py-1, px));
					}
				}
				if(px>0) {
					if(map[py][px-1] == 0 && checked[py][px-1] == false) {
						checked[py][px-1] = true;
						map[py][px-1] = 1;
						queue.offer(new Node(py, px-1));
					}
				}
			}//for문 종료
			year++;
		}//while문 종료
		
		for(int i = 0 ; i<row;i++) {
			for(int j = 0 ; j<col;j++) {
				if(map[i][j] == 0) {
					System.out.println("-1");
					i = row;
					j = col;
					break;
				}
				if(i==(row-1) && j==(col-1)) {
					if(year != 0) {
						System.out.println(year-1);
					}
					else {
						System.out.println(year);
					}
				}
			}
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		col = sc.nextInt();
		row = sc.nextInt();
		map = new int[row][col];
		checked = new boolean[row][col];
		queue = new LinkedList<>();
		
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] == 1) {
					queue.offer(new Node(i, j));
					checked[i][j] = true;
				}
			}
		}
		
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				if(map[i][j] == 1) {
					bfs(i,j);
					i=row;
					j=col;
				}
			}
		}
	}
}
