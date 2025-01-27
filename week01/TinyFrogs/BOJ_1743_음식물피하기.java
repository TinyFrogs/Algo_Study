package TinyFrogs;

import java.util.*;
import java.io.*;

public class BOJ_1743_음식물피하기 {

    static int N, M, K, MAX;
    static int[][] map;
    static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M + 1];
        MAX = 0;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            map[n][m] = 1;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (map[i][j] == 0) continue;
                bfs(i, j);
            }
        }

        System.out.print(MAX);
    }

    static void bfs(int i, int j) {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{i, j});
        map[i][j] = 0;
        int count = 1;

        while (!q.isEmpty()) {
            int[] point = q.poll();

            for (int d = 0; d < 4; d++) {
                int ni = point[0] + dir[d][0];
                int nj = point[1] + dir[d][1];
                if (ni > 0 && ni <= N && nj > 0 && nj <= M && map[ni][nj] == 1) {
                    map[ni][nj] = 0;
                    q.add(new int[]{ni, nj});
                    count++;
                }
            }
        }

        MAX = Math.max(MAX, count);

    }
}
