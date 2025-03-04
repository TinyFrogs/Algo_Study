package TinyFrogs;

import java.io.*;
import java.util.*;

public class BOJ_2666_벽장문의이동_DP {

	static int N, M, f1, f2, result;
	static int[] sequence;
	static int[][][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		// input
		N = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine(), " ");
		f1 = Integer.parseInt(st.nextToken());
		f2 = Integer.parseInt(st.nextToken());

		M = Integer.parseInt(br.readLine());
		result = Integer.MAX_VALUE;
		sequence = new int[M];
		dp = new int[20][N + 1][N + 1];

		for (int i = 0; i < M; i++) {
			for (int j = 0; j <= N; j++) {
				Arrays.fill(dp[i][j], Integer.MAX_VALUE);
			}
		}

		for (int i = 0; i < M; i++) {
			int value = Integer.parseInt(br.readLine());
			sequence[i] = value;
		}

		System.out.println(solve(0, f1, f2));

	}

	static int solve(int count, int n1, int n2) {
		if (count == M)
			return 0;

		if (dp[count][n1][n2] != Integer.MAX_VALUE)
			return dp[count][n1][n2];

		int value = sequence[count];

		int left = Math.abs(value - n1) + solve(count + 1, value, n2);
		int right = Math.abs(value - n2) + solve(count + 1, n1, value);

		return dp[count][n1][n2] = Math.min(left, right);
	}

}