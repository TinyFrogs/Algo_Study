import java.io.*;
import java.util.*;

public class BOJ_14461 {

	static class Node implements Comparable<Node> {
		int r, c, t, cnt;

		public Node(int r, int c, int t, int cnt) {
			this.r = r;
			this.c = c;
			this.t = t;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.t, o.t);
		}
	}

	static int N, T;
	static int[][] board;
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};

	static void dijkstra() {
		boolean[][][] visited = new boolean[N][N][3];
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(0, 0, 0, 0));
		visited[0][0][0] = true;

		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			if (cur.r == N - 1 && cur.c == N - 1) {
				System.out.println(cur.t);
				return;
			}

			for (int i = 0; i < 4; i++) {
				int rr = cur.r + dy[i];
				int cc = cur.c + dx[i];

				if (rr < 0 || rr >= N || cc < 0 || cc >= N)
					continue;
				if (visited[rr][cc][(cur.cnt + 1) % 3])
					continue;

				int nt = cur.t + T;

				if ((cur.cnt + 1) % 3 == 0) {
					nt += board[rr][cc];
				}

				visited[rr][cc][(cur.cnt + 1) % 3] = true;
				pq.offer(new Node(rr, cc, nt, cur.cnt + 1));
			}
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		board = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dijkstra();
	}
}
