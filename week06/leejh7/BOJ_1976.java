import java.io.*;
import java.util.*;

public class BOJ_1976 {

	static int N, M;
	static int[] parent;

	static int find(int x) {
		if (x == parent[x]) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}

	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a < b) {
			parent[b] = a;
		} else {
			parent[a] = b;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		parent = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				int input = Integer.parseInt(st.nextToken());
				if (input == 1) {
					union(i, j);
				}
			}
		}

		st = new StringTokenizer(br.readLine());
		int start = find(Integer.parseInt(st.nextToken()));
		for (int i = 1; i < M; i++) {
			if (start != find(Integer.parseInt(st.nextToken()))) {
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");
	}
}
