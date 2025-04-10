package TinyFrogs;

import java.io.*;
import java.util.*;

public class BOJ_2533_사회망서비스_DP {

	static int N;
	static StringTokenizer st;
	static int[][] dp;
	static List<Integer>[] nearPeople;
	static boolean[] checked;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		initInput(br);
		findEarly(1);

		System.out.print(Math.min(dp[1][0], dp[1][1]));
	}

	static void initInput(BufferedReader br) throws Exception {
		N = Integer.parseInt(br.readLine());
		nearPeople = new ArrayList[N + 1];
		dp = new int[N + 1][2];
		checked = new boolean[N + 1];
		checked[1] = true;

		for (int i = 1; i <= N; i++) {
			nearPeople[i] = new ArrayList<>();
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			nearPeople[u].add(v);
			nearPeople[v].add(u);
		}
	}

	static void findEarly(int start) {
		dp[start][0] = 0;
		dp[start][1] = 1;

		for (int nearPerson : nearPeople[start]) {
			if (checked[nearPerson]) continue;
			checked[nearPerson] = true;
			findEarly(nearPerson);
			dp[start][0] += dp[nearPerson][1];
			dp[start][1] += Math.min(dp[nearPerson][0], dp[nearPerson][1]);
		}
	}

}
