package TinyFrogs;

import java.io.*;
import java.util.*;

public class BOJ_2533_사회망서비스_완탐 {

	static StringTokenizer st;
	static int N, result = Integer.MAX_VALUE;

	static Friend[] friends;
	static boolean[] checked;

	static boolean[] earlyList;

	static class Friend {

		int team;
		List<Integer> nearTeams;

		public Friend(int team) {
			this.team = team;
			nearTeams = new ArrayList<>();
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		initInput(br);

		earlyList = new boolean[N+1];

		findEarly(0, 1);

		checkEarly();



		System.out.println(result);
	}

	static void initInput(BufferedReader br) throws Exception {
		N = Integer.parseInt(br.readLine());
		friends = new Friend[N + 1];
		checked = new boolean[N + 1];

		for (int i = 0; i <= N; i++) {
			friends[i] = new Friend(i);
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			friends[u].nearTeams.add(v);
			friends[v].nearTeams.add(u);
		}
	}

	static void findEarly(int sum, int start){
		if(result < sum || start > N) return;

		if(checkEarly()){
			result = Math.min(result, sum);
			return;
		}


		for(int i = start ; i <= N; i++){
			earlyList[i] = true;
			findEarly(sum + 1, i+1);
			earlyList[i] = false;
		}
	}

	static boolean checkEarly(){
		for(int i = 1  ; i <= N; i++){
			if(earlyList[i]) continue;
			for(int nearTeam : friends[i].nearTeams){
				if(!earlyList[nearTeam]) return false;
			}
		}
		return true;
	}
}
