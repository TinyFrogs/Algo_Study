package TinyFrogs;

import java.io.*;
import java.util.*;

public class BOJ_1261_알고스팟 {

	static int N, M;
	static int[][] map;
	static int[][] dist;
	static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

	static class Wall {
		int x, y, cnt;

		public Wall(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		dist = new int[N][M];

		for(int i = 0 ; i < N; i++) {
			String[] line = br.readLine().split("");
			for(int j = 0 ; j < M ; j++) {
				map[i][j] = Integer.parseInt(line[j]);
				dist[i][j] = Integer.MAX_VALUE;
			}
		}

		dijkstra(0,0);

		System.out.print(dist[N - 1][M - 1]);

	}

	static void dijkstra(int x, int y) {
		PriorityQueue<Wall> pq = new PriorityQueue<>(Comparator.comparing(wall -> wall.cnt));
		pq.offer(new Wall(x, y, 0));
		dist[0][0] = 0;

		while(!pq.isEmpty()) {
			Wall w = pq.poll();

			if(w.cnt > dist[w.y][w.x]) continue;

			for(int d = 0 ; d < 4; d++) {
				int nx = w.x + dir[d][0];
				int ny = w.y + dir[d][1];

				if(nx < 0 || nx >= M || ny < 0 || ny >= N) continue;

				int wall = map[ny][nx];
				int nextCnt = w.cnt + wall;

				if(dist[ny][nx] > nextCnt) {
					dist[ny][nx] = nextCnt;
					pq.offer(new Wall(nx, ny, nextCnt));
				}

			}
		}

	}
}
