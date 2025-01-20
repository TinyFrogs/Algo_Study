package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1240_노드사이의거리 {
    static int N;
    static int M;

    static Edge[] adjList;

    static StringBuilder sb;

    static class Edge {
        int to;
        int cost;
        Edge next;

        public Edge(int to, int cost, Edge next) {
            this.to = to;
            this.cost = cost;
            this.next = next;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        setInit();
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            addEdge(from, to, cost);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int destination = Integer.parseInt(st.nextToken());

            dfs(from, destination, new boolean[N + 1], 0);
        }

        System.out.println(sb);
    }

    static void setInit() {
        adjList = new Edge[N + 1];
        sb = new StringBuilder();
    }

    static void addEdge(int from, int to, int cost) {
        adjList[from] = new Edge(to, cost, adjList[from]);
        adjList[to] = new Edge(from, cost, adjList[to]);
    }

    static void dfs(int cur, int destination, boolean[] visited, int sum) {
        if (cur == destination) {
            sb.append(sum).append("\n");
            return;
        }

        visited[cur] = true;

        for (Edge nextEdge = adjList[cur]; nextEdge != null; nextEdge = nextEdge.next) {
            if(visited[nextEdge.to]) continue;
            dfs(nextEdge.to, destination, visited, sum + nextEdge.cost);
        }

        visited[cur] = false;
    }
}
