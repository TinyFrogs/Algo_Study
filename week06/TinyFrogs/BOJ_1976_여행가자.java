import java.io.*;
import java.util.*;

public class BOJ_1976_여행가자 {

	static int N, M;
	static int[] travelList;
	static List<Integer>[] cities;
 	static boolean flag = true;
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		inputCities(br);
		inputTravelCities(br);
		solve();

		if(flag) System.out.print("YES");
		if(!flag) System.out.println("NO");
	}

	// 연결된 도시이면(== 1) list에 넣는다
	static void inputCities(BufferedReader br) throws Exception {
		cities = new List[N];
		for(int i = 0; i < N; i++) cities[i] = new ArrayList<>();

		for(int i = 0; i < N; i++){
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++){
				int city = Integer.parseInt(st.nextToken());
				if(city == 1) cities[i].add(j);
			}
		}
	}

	// 여행 계획 ( 배열은 0부터 시작, 도시 순서는 1부터 시작이므로 입력 받을 때 -1을 한다)
	static void inputTravelCities(BufferedReader br) throws Exception {
		travelList = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++){
			travelList[i] = Integer.parseInt(st.nextToken()) - 1;
		}
	}

	// 도시 순서대로 방문하는지 확인
	static void solve(){
		for(int i = 0 ; i < M-1; i++){
			if(travelList[i] == travelList[i+1]) continue;
			if(checkCity(travelList[i], travelList[i+1])) continue;
			break;
		}
	}

	// start에서 출발하여 end에 도착하는지 확인
	static boolean checkCity(int start, int end){
		boolean[] visited = new boolean[N+1];
		ArrayDeque<Integer> q = new ArrayDeque<>();
		visited[start] = true;
		q.add(start);

		while(!q.isEmpty()){
			int cur = q.poll();

			for(int next : cities[cur]){
				if(next == end) return true;
				if(visited[next]) continue;
				visited[next] = true;
				q.offer(next);
			}
		}
		return flag = false;
	}
}
