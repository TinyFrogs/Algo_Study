import java.util.*;
import java.io.*;

public class BOJ_17951 {

	static int N, K;
	static int[] arr;

	static int bs() {
		int start = 0, end = 2000000;
		int mid;

		while (start <= end) {
			mid = (start + end) / 2;
			if (check(mid)) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}

		return end;
	}

	static boolean check(int mid) {
		int cnt = 0, sum = 0;

		for (int i = 0; i < N; i++) {
			sum += arr[i];
			if (sum >= mid) {
				cnt++;
				sum = 0;
			}
		}

		return K <= cnt;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		arr = new int[N];
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int result = bs();
		System.out.println(result);
	}
}
