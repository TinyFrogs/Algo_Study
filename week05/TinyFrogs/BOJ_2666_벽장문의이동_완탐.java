package TinyFrogs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2666_벽장문의이동_완탐 {

	static int N, M, f1, f2, result;
	static int[] sequence;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		// input
		N = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine(), " ");
		f1 = Integer.parseInt(st.nextToken());
		f2 = Integer.parseInt(st.nextToken());

		M = Integer.parseInt(br.readLine());
		result=Integer.MAX_VALUE;
		sequence = new int[M];
		visited = new boolean[M];

		for(int i = 0 ; i < M; i++){
			int value = Integer.parseInt(br.readLine());
			sequence[i] = value;
		}


		solve(0);


		System.out.println(result);
	}
	
	//조합
	static void solve(int count){
		if(count == M){
			int oper = operation();
			result = Math.min(result, oper);

			return;
		}


		//true = f1 선택
		visited[count] = true;
		solve(count+ 1);


		//false = f2 선택
		visited[count] = false;
		solve(count+1);
	}


	static int operation(){
		int n1 = f1;
		int n2 = f2;


		int sum = 0;
		for(int i = 0; i< M; i++){
			int value = sequence[i];

			//true = f1 선택
			if(visited[i]){
				int dis = Math.abs(n1 - value);
				n1 = value;
				sum += dis;
			}

			//false = f2 선택
			else{
				int dis = Math.abs(n2 - value);
				n2 = value;
				sum += dis;
			}
		}


		return sum;
	}



}
