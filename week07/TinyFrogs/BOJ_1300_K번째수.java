package TinyFrogs;

import java.io.*;
import java.util.*;

public class BOJ_1300_K번째수 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		long N = Long.parseLong(br.readLine());
		long K = Long.parseLong(br.readLine());


		//---- start
		int index = (int) Math.pow(N, 2);
		long[][] a = new long[(int)N+1][(int)N+1];
		long[] b = new long[index + 1];

		int curIndex = 1;
		for(int i = 1; i<=N; i++){
			for(int j = 1 ; j <= N; j++){
				a[i][j] = i * j;
				b[curIndex++] = i * j;
			}
		}

		Arrays.sort(b);
		System.out.println(b[(int)K]);
		//----end

	}
}
