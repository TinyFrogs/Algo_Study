package TinyFrogs;

import java.io.*;

public class BOJ_9663_NQueen {

	static int N, result = 0;
	static int[] board;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new int[N];
		roof(0);
		System.out.print(result);
	}

	static void roof(int queenCount) {
		if (queenCount == N) {
			result++;
			return;
		}

		for (int i = 0; i < N; i++) {
			board[queenCount] = i;
			if (!checkQueen(queenCount)) continue;
			roof(queenCount + 1);
		}
	}

	// 하나의 행에 배치되어있는지 && 대각선에 배치되어있는지
	static boolean checkQueen(int idx) {
		for (int i = 0; i < idx; i++) {
			if (board[i] == board[idx]) return false;
			else if (Math.abs(board[i] - board[idx]) == Math.abs(i - idx)) return false;
		}
		return true;
	}

}
