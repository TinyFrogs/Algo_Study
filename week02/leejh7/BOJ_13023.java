import java.io.*;
import java.util.*;

public class BOJ_13023 {

	static int N, M;

	static List<Integer>[] graph;
	static boolean[] visited;
	static boolean ans;

	static void dfs(int depth, int node) {
		if (depth == 4) {
			ans = true;
			return;
		}

		for (int nextNode : graph[node]) {
			if (!visited[nextNode]) {
				visited[nextNode] = true;
				dfs(depth + 1, nextNode);
				visited[nextNode] = false;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new List[N];
		for (int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a, b;
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}

		visited = new boolean[N];
		ans = false;
		for (int i = 0; i < N; i++) {
			visited[i] = true;
			dfs(0, i);
			visited[i] = false;

			if (ans) {
				break;
			}
		}
		if (ans) {
			System.out.println(1);
		} else {
			System.out.println(0);
		}
	}
}
