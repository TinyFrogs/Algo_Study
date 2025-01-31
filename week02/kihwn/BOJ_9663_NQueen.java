import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
public class Main {
	static int n;
	static int ans;
	static boolean[]col; // 열 충돌 체크
	// 행은 depth로 이미 충돌 체크 되는 중
	static boolean[]dae1; //대각선
	static boolean[]dae2; //대각선
	public static void main(String[]args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		col = new boolean[n];
		dae1 = new boolean[2*n-1];
		dae2 = new boolean[2*n-1];
		ans = 0;
		dfs(0);
		System.out.println(ans);
	}

	static void dfs(int depth) {
		if (depth==n) {
			ans++;
			return;
		}


		for (int i=0; i<n; i++) {
			//(n-1)은 음수 보정값
			if (!col[i]&& !dae1[depth+i]&&!dae2[depth-i+n-1]) {
				col[i]=true;
				dae1[depth+i]=true;
				dae2[depth-i+n-1]=true;

				dfs(depth+1);
				col[i]=false;
				dae1[depth+i]=false;
				dae2[depth-i+n-1]=false;
			}

		}
	}
}