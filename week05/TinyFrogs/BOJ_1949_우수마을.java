package TinyFrogs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1949_우수마을 {

	static House[] houses;
	static int[][] dp;

	static class House {
		int people;
		List<Integer> nearHouse;

		public House(){
			nearHouse = new ArrayList<>();
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int N = Integer.parseInt(br.readLine());

		houses = new House[N + 1];

		for (int i = 0; i <= N; i++) {
			houses[i] = new House();
		}

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) houses[i].people = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int to = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			houses[to].nearHouse.add(from);
			houses[from].nearHouse.add(to);
		}

		dp = new int[N + 1][2];

		solve(1, 0);

		//우수마을이 아닐 상황, 우수마을인 상황
		System.out.println(Math.max(dp[1][0], dp[1][1]));
	}

	//0은 우수마을이 아닐 때, 1은 우수마을일 때
	static void solve(int current, int previous) {
		dp[current][1] = houses[current].people;

		for (int next : houses[current].nearHouse) {
			if (next == previous) continue;
			solve(next, current);

			dp[current][0] += Math.max(dp[next][0], dp[next][1]);
			dp[current][1] += dp[next][0];
		}

	}
}
