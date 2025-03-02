package TinyFrogs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_12015_가장긴증가하는부분수열2 {
	static int N;
	static List<Integer> list = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			if (list.isEmpty() || list.get(list.size() - 1) < num) {
				list.add(num);
			} else {
				list.set(binarySearch(num), num);
			}
		}

		System.out.println(list.size());
	}

	static int binarySearch(int num) {
		int low = 0;
		int high = list.size();
		while (low < high) {
			int mid = (low + high) / 2;
			if (list.get(mid) < num) {
				low = mid + 1;
			} else {
				high = mid;
			}
		}
		return low;
	}
}