import java.io.*;
import java.util.*;

public class BOJ_4386_별자리만들기 {

	static int N;
	static double result = 0;
	static Star[] stars;
	static double[][] disStar;

	static class Star {
		double x, y;

		public Star(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws Exception {
		inputStars();
		initStarDis();
		prim();
		System.out.print(result);
	}

	static void inputStars() throws Exception {
		// init N
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		// init Star
		stars = new Star[N];
		StringTokenizer st = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
			stars[i] = new Star(x, y);
		}
	}

	static void initStarDis() {
		disStar = new double[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == j || disStar[i][j] != 0) continue;
				disStar[i][j] = getStarDis(stars[i], stars[j]);
			}
		}
	}

	static void prim() {
		// init prim
		double[] cost = new double[N];
		boolean[] visited = new boolean[N];
		Arrays.fill(cost, Double.MAX_VALUE);

		PriorityQueue<double[]> q = new PriorityQueue<>(Comparator.comparingDouble(o -> o[1]));
		cost[0] = 0;
		q.offer(new double[] {0, cost[0]});

		// prim start
		while (!q.isEmpty()) {
			double[] current = q.poll();
			int vertex = (int)current[0];
			double minCost = current[1];

			if (visited[vertex]) continue;
			visited[vertex] = true;

			result += minCost;
			for (int i = 0; i < N; i++) {
				if (!visited[i] && disStar[vertex][i] != 0 && disStar[vertex][i] < cost[i]) {
					cost[i] = disStar[vertex][i];
					q.offer(new double[] {i, cost[i]});
				}
			}
		}
	}

	static double getStarDis(Star star1, Star star2) {
		double disX = Math.abs(star1.x - star2.x);
		double disY = Math.abs(star1.y - star2.y);
		double dis = Math.sqrt(Math.pow(disX, 2) + Math.pow(disY, 2));
		return convert2Point(dis);
	}

	static double convert2Point(double value) {
		return Math.floor(value * 100) / 100.0;
	}
}