package TinyFrogs;

import java.io.*;
import java.util.*;

public class BOJ_2211_네트워크복구 {

	static int N, M, count;
	static List<Line>[] lineList;
	static int[] times;
	static int[] connect;

	static class Line{
		int end, time;

		public Line(int end, int time){
			this.end = end;
			this.time = time;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		count = 0;
		times = new int[N+1];
		connect = new int[N+1];
		lineList = new ArrayList[N+1];

		Arrays.fill(times, Integer.MAX_VALUE);
		for(int i = 1; i<= N; i++){
			lineList[i] = new ArrayList<>();
		}

		for(int i = 0 ; i < M; i++){
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			lineList[start].add(new Line(end, time));
			lineList[end].add(new Line(start, time));
		}

		dij();

		for(int i = 2; i <= N; i++){
			if(connect[i] == 0) continue;
			count++;
			sb.append(i + " " + connect[i] + "\n");
		}

		System.out.println(count);
		System.out.print(sb);
	}

	static void dij(){
		PriorityQueue<Line> q = new PriorityQueue<>(Comparator.comparing(line -> line.time));
		times[1] = 0;
		q.offer(new Line(1, 0));

		while(!q.isEmpty()){
			Line line = q.poll();
			int lineEnd = line.end;

			if(line.time > times[lineEnd]) continue;


			for(Line l : lineList[lineEnd]){
				if(times[l.end] > l.time + times[lineEnd]){
					times[l.end] = l.time + times[lineEnd];
					connect[l.end] = lineEnd;
					q.offer(new Line(l.end, times[l.end]));
				}
			}

		}
	}
}
