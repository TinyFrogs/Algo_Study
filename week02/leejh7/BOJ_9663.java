import java.io.*;
import java.util.*;

public class BOJ_9663 {
	static int n, ans;
	static int[] col = new int[16];

	static boolean check(int r, int c) {
		for (int i = 0; i < r; i++) {
			if (col[i] == c)
				return false; // 같은 열에 퀸이 있음
			double d = Math.abs((double)(c - col[i]) / (r - i));
			if (d == 1.0)
				return false; // 대각선에 퀸이 있음
		}
		return true;
	}

	static void Nqueen(int depth) {
		if (depth == n) {
			ans++; // 모든 퀸을 배치했으면 답을 하나 증가
			return;
		}

		for (int i = 0; i < n; i++) {
			if (check(depth, i)) {
				col[depth] = i; // 퀸을 해당 위치에 놓음
				Nqueen(depth + 1); // 다음 행으로 이동
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		Nqueen(0);
		System.out.println(ans);
	}
}

