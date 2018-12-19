package nhn_2018_ex2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static String map[][];
	static int N;
	static int rdy[] = {-1,0,1,0}; //북동남서 0, 1, 2, 3 
	static int rdx[] = {0,1,0,-1};
	static int ldy[] = {1,0,-1,0}; //남동북서 0, 1, 2, 3 
	static int ldx[] = {0,1,0,-1};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int rot = Integer.parseInt(st.nextToken());

		map = new String[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = st.nextToken();
			}
		}

		for (int i = 0; i < N / 2; i++) {
			rotate(i, rot);
			rot *= (-1);
		}
	}

	public static void rotate(int depth, int rot) {
		int outter = N-depth*2;
		int totlen = outter*4-4;
		int ny = 0 ;
		int nx = 0 ; 
		
		int dir = 0;
		
		if(rot < 0) { //반시계방향
			rot *= -1;
			rot = rot%totlen;

			String s = map[depth][depth];
			
			for(int i = 0; i< rot;i++) {
				ny = ny + ldy[dir];
				nx = nx + ldx[dir];
				if(ny < depth || nx < depth || ny > N-depth || nx > N-depth) {
					ny = ny - ldy[dir];
					nx = nx - ldx[dir];
					dir = (dir+1)%4;
				}
			}
			
			
		}
		
	}

}