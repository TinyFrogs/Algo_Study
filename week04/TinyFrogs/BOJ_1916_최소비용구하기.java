package TinyFrogs;

import java.io.*;
import java.util.*;
public class BOJ_1916_최소비용구하기 {

	static int N, M;
	static boolean[] visited;
	static int[] dist;
	static List<Bus>[] busList;

	static class Bus {
		int dest, value;

		public Bus(int dest, int value){
			this.dest = dest;
			this.value = value;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		busList = new ArrayList[N+1];
		visited = new boolean[N+1];
		dist = new int[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		for(int i = 1; i <= N; i++){
			busList[i] = new ArrayList<>();
		}


		for(int i = 0 ; i < M; i++){
			st = new StringTokenizer(br.readLine(), " ");
			int to = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			busList[to].add(new Bus(from, value));
		}

		st = new StringTokenizer(br.readLine(), " ");
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		dij(start);
		System.out.println(dist[end]);
	}

	static void dij(int start){
		PriorityQueue<Bus> q = new PriorityQueue<>(Comparator.comparing(bus -> bus.value)) ;
		q.offer(new Bus(start, 0));
		dist[start] = 0;

		while(!q.isEmpty()){
			Bus bus = q.poll();
			int busEnd = bus.dest;

			if(visited[busEnd]) continue;

			visited[busEnd] = true;
			for(Bus b : busList[busEnd]){
				if (!visited[b.dest] && dist[b.dest] > dist[busEnd] + b.value){
					dist[b.dest] = dist[busEnd] + b.value;
					q.offer(new Bus(b.dest, dist[b.dest]));
				}
			}
		}
	}
}
