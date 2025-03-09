import java.io.*;
import java.util.*;

public class BOJ_4386 {
	static class Point {
		double x, y;

		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N;
	static double[][] mat;
	static double[] dist;
	static Point[] points;

	public static void prim() {
		for (int i = 1; i < N; i++) {
			dist[i] = mat[0][i];
		}

		int repeat = N;
		while (--repeat > 0) {
			double minVal = Double.POSITIVE_INFINITY;
			int nearIdx = -1;

			for (int i = 1; i < N; i++) {
				if (dist[i] >= 0 && dist[i] < minVal) {
					minVal = dist[i];
					nearIdx = i;
				}
			}

			if (nearIdx == -1)
				break;
			dist[nearIdx] *= -1;

			for (int i = 1; i < N; i++) {
				if (mat[i][nearIdx] < dist[i]) {
					dist[i] = mat[i][nearIdx];
				}
			}
		}

		double ret = 0;
		for (int i = 1; i < N; i++) {
			ret += -dist[i];
		}

		System.out.printf("%.2f\n", ret);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		points = new Point[N];
		mat = new double[N][N];
		dist = new double[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			points[i] = new Point(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
		}

		for (int i = 0; i < N - 1; i++) {
			for (int j = i + 1; j < N; j++) {
				double d = Math.sqrt(Math.pow(points[i].x - points[j].x, 2) +
					Math.pow(points[i].y - points[j].y, 2));
				mat[i][j] = mat[j][i] = d;
			}
		}

		prim();
	}
}

