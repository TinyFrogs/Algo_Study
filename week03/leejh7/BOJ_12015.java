import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.lang.*;

public class BOJ_12015 {
	static int[] arr;

	public static int bs(int n, int cnt) {
		int start = 1;
		int end = cnt - 1;
		int mid;

		while (start <= end) {
			mid = (start + end) / 2;
			if (arr[mid] < n) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		return end + 1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int n = Integer.parseInt(br.readLine());

		arr = new int[n + 1];
		arr[0] = -1;

		st = new StringTokenizer(br.readLine());
		int cnt = 1;
		for (int i = 0; i < n; i++) {
			int input = Integer.parseInt(st.nextToken());

			if (arr[cnt - 1] < input) {
				arr[cnt] = input;
				cnt += 1;
			} else {
				int idx = bs(input, cnt);
				arr[idx] = input;
			}
		}

		System.out.println(cnt - 1);
	}
}