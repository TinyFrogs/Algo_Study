import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2666 {

	static int N, M;
	static int[] inputs;
	static int[][][] dp;

	static int solution(int depth, int d1, int d2) {
		if (depth == M) {
			return 0;
		}
		int target = inputs[depth];
		dp[target][d1][d2] = Math.min(
			Math.abs(target - d1) + solution(depth + 1, target, d2),
			Math.abs(target - d2) + solution(depth + 1, d1, target)
		);
		return dp[target][d1][d2];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		dp = new int[N + 1][N + 1][N + 1];

		st = new StringTokenizer(br.readLine());
		int d1 = Integer.parseInt(st.nextToken());
		int d2 = Integer.parseInt(st.nextToken());

		M = Integer.parseInt(br.readLine());
		inputs = new int[M];
		for (int i = 0; i < M; i++) {
			inputs[i] = Integer.parseInt(br.readLine());
		}
		System.out.println(solution(0, d1, d2));
	}
}
