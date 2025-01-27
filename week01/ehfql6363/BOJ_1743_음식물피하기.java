package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1743_음식물피하기 {
    static int N;
    static int M;
    static int K;

    static int[] rows = {-1, 1, 0, 0};
    static int[] cols = {0, 0, -1, 1};

    static boolean[][] map;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new boolean[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;

            map[r][c] = true;
        }

        int answer = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(visited[i][j]) continue;
                if(!map[i][j]) continue;
                answer = Math.max(answer, bfs(i, j));
            }
        }

        System.out.println(answer);
    }

    static int bfs(int row, int col) {
        Queue<int[]> ad = new ArrayDeque<>();
        ad.offer(new int[]{row, col});

        visited[row][col] = true;

        int count = 0;

        while (!ad.isEmpty()) {
            int[] cur = ad.poll();
            int r = cur[0];
            int c = cur[1];

            count++;

            for (int i = 0; i < 4; i++) {
                int dr = r + rows[i];
                int dc = c + cols[i];

                if (isRange(dr, dc) && map[dr][dc] && !visited[dr][dc]) {
                    ad.offer(new int[]{dr, dc});
                    visited[dr][dc] = true;
                }
            }
        }

        return count;
    }

    static boolean isRange(int dr, int dc) {
        return dr >= 0 && dr < N && dc >= 0 && dc < M;
    }
}
