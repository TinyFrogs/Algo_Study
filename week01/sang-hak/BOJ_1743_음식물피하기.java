import java.util.*;
import java.io.*;

public class BOJ_1743_음식물피하기 {
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int bfs(int startY, int startX, int[][] path, boolean[][] isVisited, int N, int M) {
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{startY, startX});
        isVisited[startY][startX] = true;
        int scale = 1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int y = cur[0];
            int x = cur[1];

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if (ny >= 0 && ny <= N && nx >= 0 && nx <= M && !isVisited[ny][nx] && path[ny][nx] == 1) {
                    isVisited[ny][nx] = true;
                    q.offer(new int[]{ny, nx});
                    scale++;
                }
            }
        }

        return scale;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] path = new int[N+1][M+1];
        boolean[][] isVisited = new boolean[N+1][M+1];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            path[a][b] = 1;
        }

        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (!isVisited[i][j] && path[i][j] == 1) max = Math.max(max, bfs(i, j, path, isVisited, N, M));
            }
        }

        System.out.println(max);
    }
}