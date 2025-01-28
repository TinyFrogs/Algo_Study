package TinyFrogs;

import java.io.*;
import java.util.*;

public class BOJ_19236_청소년상어 {

	//0:없음 1:(1,0) 2:(1,-1) 3:(0,-1) 4:(-1,-1) 5:(-1,0) 6:(-1,1) 7:(0, 1) 8:(1,1)
	static int[][] dir = {{0, 0}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}};

	static class Fish {
		int value, dir;
		Fish(int value, int dir) {
			this.value = value;
			this.dir = dir;
		}
	}

	static Fish[][] board = new Fish[4][4];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for(int i = 0 ;  i < 4; i++){
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0 ; j < 4; j++){
				int value = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				board[i][j] = new Fish(value, dir);
			}
		}
	}
	
	static boolean isBoard(int i, int j) {
		return i >= 0 && i < 4 && j >= 0 && j < 4;
	}

}
