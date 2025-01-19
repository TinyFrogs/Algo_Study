package leejh7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1240_노드사이의_거리 {

    static class Node {

        int v;
        int dist;

        Node(int v, int dist) {
            this.v = v;
            this.dist = dist;
        }
    }

    static List<Node>[] graph;

    static int query(int start, int end, int N) {
        boolean[] visited = new boolean[N + 1];
        Queue<int[]> q = new ArrayDeque<>();

        q.offer(new int[]{start, 0});
        visited[start] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            if (cur[0] == end) {
                return cur[1];
            }

            for (Node node : graph[cur[0]]) {
                if (visited[node.v]) {
                    continue;
                }
                q.offer(new int[]{node.v, node.dist + cur[1]});
                visited[node.v] = true;
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int N, M;
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int u, v, dist;
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            dist = Integer.parseInt(st.nextToken());

            graph[u].add(new Node(v, dist));
            graph[v].add(new Node(u, dist));
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start, end;
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            sb.append(query(start, end, N)).append("\n");
        }

        System.out.println(sb.toString());
    }
}