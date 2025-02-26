package TinyFrogs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1915_가장큰정사각형 {

	static int N, M;
	static int[][] arr;
	static long result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		result = 0;

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N+1][M+1];


		for (int i = 1; i <= N; i++) {
			String[] split = br.readLine().split("");
			for (int j = 1; j <= split.length; j++) {
				arr[i][j] = Integer.parseInt(split[j-1]);
				if(arr[i][j] != 1) continue;
				int min = Math.min(arr[i-1][j-1], Math.min(arr[i][j-1], arr[i-1][j]));
				arr[i][j] = min + 1;
				result = Math.max(result, arr[i][j]);
			}
		}

		System.out.print(result * result);
	}

}
