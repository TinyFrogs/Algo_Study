import java.util.*;
import java.io.*;

public class BOJ_1240_노드사이의_거리 {
	static int bfs(int from, int to, int N, List<int[]>[] tree) {
		boolean[] isVisited = new boolean[N+1];
		Deque<int[]> q = new ArrayDeque<>();
		isVisited[from] = true;
		q.offer(new int[]{from, 0});

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int node = cur[0];
			int sumDist = cur[1];

			for (int[] i : tree[node]) {
				if (i[0] == to) {
					return sumDist + i[1];
				}
				if (!isVisited[i[0]]) {
					isVisited[i[0]] = true;
					q.offer(new int[]{i[0], sumDist + i[1]});
				}
			}
		}

		return 0;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		List<int[]>[] tree = new ArrayList[N+1];

		for (int i = 1; i <= N; i++) {
			tree[i] = new ArrayList<>();
		}

		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			tree[from].add(new int[]{to, dist});
			tree[to].add(new int[]{from, dist});
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			sb.append(bfs(from, to, N, tree)).append("\n");
		}

		System.out.print(sb);
	}
}