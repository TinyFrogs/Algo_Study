package TinyFrogs;

import java.io.*;
import java.util.*;

public class BOJ_1240_노드사이의거리 {

    static class Point {
        int to, dis;

        public Point(int to, int dis) {
            this.to = to;
            this.dis = dis;
        }
    }

    static int N, M, result;
    static List<Point>[] points;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        points = new ArrayList[N + 1];

        for (int n = 1; n <= N; n++) points[n] = new ArrayList<>();

        //연결된 노드 지정 (양방향)
        for (int n = 0; n < N - 1; n++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());
            points[from].add(new Point(to, dis));
            points[to].add(new Point(from, dis));
        }

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            result = 0;
            bfs(from, to);
            sb.append(result).append("\n");
        }

        System.out.print(sb);
    }

    static void bfs(int from, int end) {
        Queue<Point> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];
        q.offer(new Point(from, 0));
        visited[from] = true;

        while (!q.isEmpty()) {
            Point point = q.poll();

            if (point.to == end) {
                result = point.dis;
                return;
            }

            for (Point next : points[point.to]) {
                if (visited[next.to]) continue;
                visited[next.to] = true;
                q.offer(new Point(next.to, point.dis + next.dis));
            }
        }
    }
    /*
4 2
2 1 2
4 3 2
1 4 3
1 2
3 2

노드 개수 4개
알고싶은  노드 쌍의 개수 2개
다음 3개는 연결된 두 점 (i, j)와 거리 k를 주어진다고 가정
(2 - 1) 거리 2
(4 - 3) 거리 2
(1 - 4) 거리 3
1에서 2까지 거리
3에서 2까지 거리

1-4-2

* */
}
