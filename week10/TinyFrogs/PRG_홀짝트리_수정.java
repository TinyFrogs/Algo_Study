import java.util.*;

public class PRG_홀짝트리_수정 {

	public class Solution {

		static int oddEvenTreeCount, reverseOddEvenTreeCount;
		static List<Integer>[] nodeList = new ArrayList[1000001];
		static int[] degree = new int[1000001];
		static boolean[] visited = new boolean[1000001];
		static ArrayDeque<Integer> q = new ArrayDeque<>();

		public int[] solution(int[] nodes, int[][] edges) {
			oddEvenTreeCount = 0;
			reverseOddEvenTreeCount = 0;

			init(nodes, edges);
			solve(nodes);

			return new int[]{oddEvenTreeCount, reverseOddEvenTreeCount};
		}

		// 인접 리스트와 degree 계산
		static void init(int[] nodes, int[][] edges) {
			for (int node : nodes) {
				nodeList[node] = new ArrayList<>();
				degree[node] = 0;
				visited[node] = false;
			}
			for (int[] e : edges) {
				int u = e[0], v = e[1];
				nodeList[u].add(v);
				nodeList[v].add(u);
				degree[u]++;
				degree[v]++;
			}
		}


		static void solve(int[] nodes) {
			for (int start : nodes) {
				if (visited[start]) continue;

				int compSize = 0;
				int fCountOnes = 0;

				q.clear();
				visited[start] = true;
				q.offer(start);

				while (!q.isEmpty()) {
					int cur = q.poll();
					compSize++;

					// forest classification bit: (node%2) != ((degree-1)%2)
					// degree-1 패리티는 (degree[cur] - 1) & 1 로 구할 수 있음
					if ((cur & 1) != ((degree[cur] - 1) & 1)) {
						fCountOnes++;
					}


					for (int nxt : nodeList[cur]) {
						if (!visited[nxt]) {
							visited[nxt] = true;
							q.offer(nxt);
						}
					}
				}

				int fCountZeros = compSize - fCountOnes;
				if (fCountOnes == 1) oddEvenTreeCount++;
				if (fCountZeros == 1) reverseOddEvenTreeCount++;
			}
		}
	}
}
