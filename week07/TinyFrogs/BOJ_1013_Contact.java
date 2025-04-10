package TinyFrogs;

import java.util.*;
import java.io.*;

public class BOJ_1013_Contact {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for(int tc = 0; tc < T; tc++) {
			if(br.readLine().matches("(100+1+|01)+")) System.out.println("YES");
			else System.out.println("NO");
		}
	}
}
