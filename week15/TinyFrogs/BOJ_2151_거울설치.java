import java.io.*;
import java.util.*;

public class BOJ_2151_거울설치 {

	static int N, answer = Integer.MAX_VALUE;
	static int startX, startY;
	static char[][] map;
	static int[][][] dist;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static PriorityQueue<Mirror> queue;

	static class Mirror {
		int x, y, dir, count;

		public Mirror(int x, int y, int dir, int count) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.count = count;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		dist = new int[N][N][4];
		startX = startY = -1;
		queue = new PriorityQueue<>(Comparator.comparing(m -> m.count));

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = line.charAt(j);
				dist[i][j][0] = dist[i][j][1] = dist[i][j][2] = dist[i][j][3] = Integer.MAX_VALUE;

				if (map[i][j] == '#' && startX == -1 && startY == -1) {
					startX = j;
					startY = i;
				}
			}
		}

		dijkstra();
		System.out.print(answer);

	}

	static void dijkstra() {
		for(int i = 0 ; i < 4; i++) {
			dist[startY][startX][i] = 0;
			queue.offer(new Mirror(startX, startY, i, 0));
		}


		while(!queue.isEmpty()) {
			int qSize = queue.size();

			for(int i = 0 ; i < qSize; i++) {
				Mirror current = queue.poll();
				int nx = current.x + dir[current.dir][0];
				int ny = current.y + dir[current.dir][1];

				if (nx < 0 || nx >= N || ny < 0 || ny >= N || dist[ny][nx][current.dir] < current.count
					|| map[ny][nx] == '*')
					continue;

				if (map[ny][nx] == '.') {
					dist[ny][nx][current.dir] = current.count;
					queue.offer(new Mirror(nx, ny, current.dir, current.count));
				}
				if (map[ny][nx] == '!') {
					dist[ny][nx][current.dir] = current.count;
					if(current.dir <= 1) {
						dist[ny][nx][2] = current.count;
						dist[ny][nx][3] = current.count;
						queue.offer(new Mirror(nx, ny, current.dir, current.count));
						queue.offer(new Mirror(nx, ny, 2, current.count + 1));
						queue.offer(new Mirror(nx, ny, 3, current.count + 1));
					}
					else {
						dist[ny][nx][0] = current.count;
						dist[ny][nx][1] = current.count;
						queue.offer(new Mirror(nx, ny, current.dir, current.count));
						queue.offer(new Mirror(nx, ny, 0, current.count + 1));
						queue.offer(new Mirror(nx, ny, 1, current.count + 1));
					}
				}
				if(map[ny][nx] == '#'){
					answer = Math.min(answer, current.count);
				}

			}
		}
	}
}
