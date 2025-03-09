import java.io.*;
import java.util.*;

public class BOJ_3665 {

	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static boolean[][] graph;
	static int[] inDegree;

	static void solution() {
		StringBuilder result = new StringBuilder();
		Queue<Integer> q = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) {
			if (inDegree[i] == 0) {
				q.offer(i);
			}
		}

		if (q.isEmpty()) {
			sb.append("IMPOSSIBLE\n");
			return;
		}

		int cnt = 0;
		while (!q.isEmpty()) {
			if (q.size() > 1) {
				sb.append("?\n");
				return;
			}

			int cur = q.poll();
			cnt += 1;
			result.append(cur).append(" ");
			for (int i = 1; i <= N; i++) {
				if (i == cur)
					continue;
				if (graph[cur][i]) {
					inDegree[i]--;
					if (inDegree[i] == 0) {
						q.offer(i);
					}
				}
			}
		}
		if (cnt != N) {
			sb.append("IMPOSSIBLE\n");
			return;
		}
		sb.append(result).append("\n");
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			graph = new boolean[N + 1][N + 1];
			inDegree = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				Arrays.fill(graph[i], true);
			}

			st = new StringTokenizer(br.readLine());
			boolean[] visited = new boolean[N + 1];
			for (int i = 0; i < N; i++) {
				int ti = Integer.parseInt(st.nextToken());
				visited[ti] = true;
				for (int j = 1; j <= N; j++) {
					if (visited[j])
						continue;
					graph[j][ti] = false;
				}
			}

			M = Integer.parseInt(br.readLine());
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				graph[a][b] = !graph[a][b];
				graph[b][a] = !graph[b][a];
			}

			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (i == j)
						continue;
					if (!graph[i][j]) {
						inDegree[i]++;
					}
				}
			}

			solution();
		}

		System.out.println(sb.toString());
	}
}
