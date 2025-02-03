package TinyFrogs;

import java.io.*;
import java.util.*;

public class BOJ_13023_ABCDE {

	static int N, M, result = 0;
	static List<Integer>[] friends;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		friends = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			friends[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			friends[from].add(to);
			friends[to].add(from);
		}

		for (int i = 0; i < N; i++) {
			visited = new boolean[N];
			dfs(i, 1);
			if (result == 1) break;
		}

		System.out.println(result);
	}

	static void dfs(int start, int depth) {
		if (depth == 5) {
			result = 1;
			return;
		}
		visited[start] = true;
		for (int to : friends[start]) {
			if (visited[to]) continue;
			dfs(to, depth + 1);
		}
		visited[start] = false;
	}
}
