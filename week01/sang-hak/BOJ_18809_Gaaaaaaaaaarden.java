import java.util.*;
import java.io.*;

public class BOJ_18809_Gaaaaaaaaaarden {
    /**
     * 틀린 부분 찾은 것
     * choose 부분 부분집합으로 바꾸는 것
     */
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int max;
    static int bfs(int[][] garden, List<int[]> l2, int N, int M) {
        Deque<int[]> q = new ArrayDeque<>();
        int[][] spread = new int[N][M];
        for (int[] i : l2) {
            q.offer(i);
            spread[i[0]][i[1]] = i[2];
        }

        int flower = 0;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int y = cur[0];
            int x = cur[1];
            int num = cur[2];
            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if (ny >= 0 && ny < N && nx >= 0 && nx < M && garden[ny][nx] != 1 && spread[ny][nx] != 1) {
                    if (spread[ny][nx] == 0) {
                        spread[ny][nx] = num + 1;
                        q.offer(new int[]{ny, nx, spread[ny][nx]});
                    } else {
                        int time = spread[ny][nx] % 100000;
                        int color = spread[ny][nx] / 100000;
                        if ((num / 100000) != color && ((num+1) % 100000) == time) {
                            flower++;
                            spread[ny][nx] = 1;
                        }
                    }
                }
            }
        }
        return flower;
    }
    static void choose(int R, int G, boolean[] isUsed, int[][] spread, List<int[]> l, List<int[]> l2) {
        if (R == 0 && G == 0) {
            max = Math.max(max, bfs(spread, l2, spread.length, spread[0].length));
            return;
        }

        for (int i = 0; i < l.size(); i++) {
            if (!isUsed[i]) {
                int[] cur = l.get(i);
                int y = cur[0];
                int x = cur[1];
                if (R > 0) {
                    isUsed[i] = true;
                    spread[y][x] = 100000;
                    l2.add(new int[]{y, x, 100000});
                    choose(R-1, G, isUsed, spread, l, l2);
                    l2.remove(l2.size()-1);
                    spread[y][x] = 0;
                    isUsed[i] = false;
                }
                if (G > 0) {
                    isUsed[i] = true;
                    spread[y][x] = 200000;
                    l2.add(new int[]{y, x, 200000});
                    choose(R, G-1, isUsed, spread, l, l2);
                    l2.remove(l2.size()-1);
                    spread[y][x] = 0;
                    isUsed[i] = false;
                }
            }
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int[][] spread = new int[N][M];
        List<int[]> l = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int cur = Integer.parseInt(st.nextToken());
                if (cur == 0) spread[i][j] = 1;
                if (cur == 2) l.add(new int[]{i, j});
            }
        }

        max = Integer.MIN_VALUE;
        boolean[] isUsed = new boolean[l.size()];
        List<int[]> l2 = new ArrayList<>();
        choose(R, G, isUsed, spread, l, l2);

        System.out.println(max);
    }
}