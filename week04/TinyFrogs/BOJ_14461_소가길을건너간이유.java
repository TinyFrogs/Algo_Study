package TinyFrogs;

import java.io.*;
import java.util.*;

public class BOJ_14461_소가길을건너간이유 {

	static int N, T;
	static int[][] map;
	static boolean[][][] visited;

	static int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

	static class Cow {
		int x, y, time, value;

		public Cow(int x, int y, int time, int value) {
			this.x = x;
			this.y = y;
			this.time = time;
			this.value = value;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		//input
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		visited = new boolean[N][N][3];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		djk();
	}

	static void djk() {
		PriorityQueue<Cow> q = new PriorityQueue<>(Comparator.comparing(cow -> cow.value));
		q.offer(new Cow(0, 0, 0, 0));

		while (!q.isEmpty()) {
			Cow cow = q.poll();

			if(cow.x == N - 1 && cow.y == N - 1){
				System.out.println(cow.value);
				return;
			}

			if(visited[cow.x][cow.y][cow.time]) continue;
			visited[cow.x][cow.y][cow.time] = true;

			for(int d = 0 ; d < 4; d++ ){
				int nx = cow.x + dir[d][0];
				int ny = cow.y + dir[d][1];
				if(!isInGrid(nx, ny, cow.time)) continue;
				if(cow.time== 2){
					q.offer(new Cow(nx, ny, 0, cow.value + map[nx][ny] + T));
				}
				else{
					q.offer(new Cow(nx, ny, cow.time+1, cow.value + T));
				}
			}
		}

	}

	static boolean isInGrid(int x, int y, int time){
		if(x >= 0 && x < N && y >= 0 && y < N && !visited[x][y][(time+1)%3]) return true;
		return false;
	}
}
