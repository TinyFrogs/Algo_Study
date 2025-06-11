import java.io.*;
import java.util.*;

public class BOJ_3108_로고 {

	static int N;
	static Rec[] recs;

	static class Rec {
		int x1, y1, x2, y2;

		public Rec(int x1, int y1, int x2, int y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}
	}

	static int[] parents;

	public static int find(int a) {
		if (parents[a] == a)
			return a;
		return parents[a] = find(parents[a]);
	}

	public static void union(int x, int y) {
		int rootX = find(x);
		int rootY = find(y);
		if (rootX == rootY)
			return;
		if (rootX < rootY)
			parents[rootY] = rootX;
		else
			parents[rootX] = rootY;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		parents = new int[N + 1];
		recs = new Rec[N + 1];

		for (int i = 0; i <= N; i++) {
			parents[i] = i;
		}

		recs[0] = new Rec(0, 0, 0, 0);
		for (int i = 1; i <= N; i++) {
			String[] parts = br.readLine().split(" ");
			int x1 = Integer.parseInt(parts[0]);
			int y1 = Integer.parseInt(parts[1]);
			int x2 = Integer.parseInt(parts[2]);
			int y2 = Integer.parseInt(parts[3]);
			recs[i] = new Rec(x1, y1, x2, y2);
		}

		findAndUnion();

		Set<Integer> roots = new HashSet<>();
		for(int i = 0; i <= N; i++){
			roots.add(find(i));
		}

		System.out.println(roots.size() - 1);
	}

	static void findAndUnion() {
		for (int i = 0; i <= N; i++) {
			for (int j = i + 1; j <= N; j++) {
				if (isColliding(i, j)) {
					union(i, j);
				}
			}
		}
	}

	static boolean isColliding(int a, int b) {
		if(recs[a].x1 < recs[b].x1 && recs[a].y1 < recs[b].y1 && recs[a].x2 > recs[b].x2 && recs[a].y2 > recs[b].y2)
			return false;

		if(recs[b].x1 < recs[a].x1 && recs[b].y1 < recs[a].y1 && recs[b].x2 > recs[a].x2 && recs[b].y2 > recs[a].y2)
			return false;

		if(recs[a].y2 <recs[b].y1 || recs[b].y2 < recs[a].y1 || recs[a].x2 < recs[b].x1 || recs[b].x2 < recs[a].x1)
			return false;

		return true;
	}
}
