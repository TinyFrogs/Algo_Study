package leejh7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1743_음식물피하기 {

    static int N, M;
    static int[][] board;
    static boolean[][] visited;

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    static int bfs(int sr, int sc) {
        int result = 0;

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{sr, sc});
        visited[sr][sc] = true;

        while (!q.isEmpty()) {
            int[] node = q.poll();
            result += 1;

            int r = node[0];
            int c = node[1];

            for (int i = 0; i < 4; i++) {
                int rr = r + dy[i];
                int cc = c + dx[i];

                if (rr < 0 || rr >= N || cc < 0 || cc >= M) {
                    continue;
                }
                if (visited[rr][cc]) {
                    continue;
                }
                if (board[rr][cc] == 0) {
                    continue;
                }
                visited[rr][cc] = true;
                q.offer(new int[]{rr, cc});
            }
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r, c;
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            board[r - 1][c - 1] = 1;
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 0) {
                    continue;
                }
                if (visited[i][j]) {
                    continue;
                }
                answer = Math.max(answer, bfs(i, j));
            }
        }
        System.out.println(answer);
    }
}