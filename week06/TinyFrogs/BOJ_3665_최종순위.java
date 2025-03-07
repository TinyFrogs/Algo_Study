import java.io.*;
import java.util.*;


public class BOJ_3665_최종순위 {

	static StringTokenizer st;
	static StringBuilder sb;
	static ArrayDeque<Integer> q;
	static Team[] teams;
	static int[] preRank;
	static int N, M;
	static boolean flag;

	static class Team{
		int pre;
		List<Integer> post;

		public Team(){
			pre = 0;
			post = new ArrayList<>();
		}
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for(int tc = 0; tc < T; tc++){
			inputInit(br);
			inputPreRank(br);
			changeTeam(br);

			checkRank();
			if(!flag) continue;

			findRank();
			if(!flag) continue;
			System.out.println(sb);
		}
	}

	// N, M 입력
	static void inputInit(BufferedReader br) throws Exception {
		N = Integer.parseInt(br.readLine());
		teams = new Team[N + 1];
		preRank = new int[N + 1];
		flag = true;
		for(int i = 1 ; i<= N ; i++) teams[i] = new Team();
	}

	//작년의 등수 입력
	static void inputPreRank(BufferedReader br) throws Exception {
		st = new StringTokenizer(br.readLine());

		for(int i = 1 ; i <= N ; i++){
			int team = Integer.parseInt(st.nextToken());
			preRank[i] = team;
		}

		for(int i = 1 ; i <= N ; i++){
			int team = preRank[i];
			for(int j = i + 1 ; j <= N; j++){
				teams[team].post.add(preRank[j]);
				teams[preRank[j]].pre++;
			}
		}
	}

	//순위 바꾸기
	static void changeTeam(BufferedReader br) throws Exception {
		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int t1 = Integer.parseInt(st.nextToken());
			int t2 = Integer.parseInt(st.nextToken());

			if (teams[t1].post.contains(t2)) {
				teams[t1].post.remove((Integer)t2);
				teams[t1].pre++;
				teams[t2].post.add(t1);
				teams[t2].pre--;
			}
			else {
				teams[t2].post.remove((Integer)t1);
				teams[t2].pre++;
				teams[t1].post.add(t2);
				teams[t1].pre--;
			}
		}
	}

	//순위 확인
	static void checkRank(){
		q = new ArrayDeque<>();
		for(int i = 1; i <= N; i++){
			int team = preRank[i];
			if(teams[team].pre == 0) q.offer(team);
		}

		// 1등이 여러팀일 경우 -> 확실한 순위를 찾을 수 없다
		// if(q.size() >= 2) {
		// 	System.out.println("?");
		// 	flag = false;
		// }
	}

	// 순위 찾기
	static void findRank(){
		sb = new StringBuilder();
		sb.append(q.peek());

		for(int i = 1 ; i <= N; i++){
			if(q.isEmpty()){
				System.out.println("IMPOSSIBLE");
				flag = false;
				break;
			}

			int team = q.poll();

			for(int nextTeam : teams[team].post){
				teams[nextTeam].pre--;
				if(teams[nextTeam].pre == 0) {
					q.offer(nextTeam);
					sb.append(" ").append(nextTeam);
				}
			}
		}

	}
}
