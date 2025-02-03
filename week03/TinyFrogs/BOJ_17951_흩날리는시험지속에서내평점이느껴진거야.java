package TinyFrogs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17951_흩날리는시험지속에서내평점이느껴진거야 {
	static int N, K, max, result;
	static int[] arr;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N];
		result = 0;

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			int value = Integer.parseInt(st.nextToken());
			arr[i] = value;
			max += value;
		}

		System.out.println(BS());
	}

	static int BS() {
		int low = 0;
		int high = max + 1;

		while (low + 1 < high) {
			int mid = (low + high) / 2;

			int sum = 0;
			int count = 0;
			for (int i = 0; i < N; i++) {
				sum += arr[i];
				if (sum >= mid) {
					count++;
					sum = 0;
				}
			}

			if (count >= K) {
				low = mid;
			} else {
				high = mid;
			}
		}
		return low;
	}
}
