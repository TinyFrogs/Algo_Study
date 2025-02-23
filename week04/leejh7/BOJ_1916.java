import java.io.*;
import java.util.*;

public class BOJ_1916 {

	static int N, M;
	static List<List<Node>> graph;
	static int[] dist;

	static class Node implements Comparable<Node> {
		int vertex, cost;

		Node(int vertex, int cost) {
			this.vertex = vertex;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node other) {
			return Integer.compare(this.cost, other.cost);
		}
	}

	public static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));
		dist[start] = 0;

		while (!pq.isEmpty()) {
			Node current = pq.poll();
			int cost = current.cost;
			int u = current.vertex;

			// 시간 초과 방지 (중복 방문 체크)
			if (cost > dist[u])
				continue;

			for (Node neighbor : graph.get(u)) {
				int v = neighbor.vertex;
				int newCost = cost + neighbor.cost;

				if (newCost < dist[v]) {
					dist[v] = newCost;
					pq.add(new Node(v, newCost));
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		graph = new ArrayList<>();
		dist = new int[N + 1];

		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		Arrays.fill(dist, Integer.MAX_VALUE);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph.get(u).add(new Node(v, w));
		}

		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());

		dijkstra(start);
		System.out.println(dist[end]);
	}
}
