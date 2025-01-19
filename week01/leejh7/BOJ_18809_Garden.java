package leejh7;

import java.util.*;
import java.io.*;

public class BOJ_18809_Garden {

    static class Box {

        int value; // 0: 호수, 1: 아무것도 없는 곳, 2: 초록색 배양액, 3: 빨간색 배양액, 4: 꽃
        int time = 0; // 배양액이 처음으로 가게 된 시간

        public Box(int value) {
            this.value = value;
        }
    }

    static int N, M, G, R;

    static int[][] board;
    static Box[][] simBoard;
    static List<Integer> brownPos;

    static int answer;

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    static int[] convertTo2D(int value) {
        int row = value / M;
        int col = value % M;
        return new int[]{row, col};
    }

    static int convertTo1D(int[] value) {
        return value[0] * M + value[1];
    }

    static void selectGreen(int depth, int start, List<Integer> selected, boolean[] checked) {
        if (depth == G) {
            for (int i = 0; i < checked.length; i++) {
                int[] pos2D = convertTo2D(selected.get(i));
                if (checked[i]) {
                    simBoard[pos2D[0]][pos2D[1]].value = 2;
                } else {
                    simBoard[pos2D[0]][pos2D[1]].value = 3;
                }
            }
            answer = Math.max(answer, solution());
            for (int pos : selected) {
                int[] pos2D = convertTo2D(pos);
                simBoard[pos2D[0]][pos2D[1]].value = 1;
            }
            return;
        }

        for (int i = start; i < selected.size(); i++) {
            checked[i] = true;
            selectGreen(depth + 1, i + 1, selected, checked);
            checked[i] = false;
        }
    }

    static void selectGround(int depth, int start, List<Integer> selected) {
        if (depth == G + R) {
            selectGreen(0, 0, selected, new boolean[selected.size()]);
            return;
        }

        for (int i = start; i < brownPos.size(); i++) {
            selected.add(brownPos.get(i));
            selectGround(depth + 1, i + 1, selected);
            selected.remove(selected.size() - 1);
        }
    }

    static void permutation(int depth, boolean[] visited) {
        if (depth == G + R) {
            answer = Math.max(answer, solution());
            return;
        }

        for (int pos : brownPos) {
            if (visited[pos]) {
                continue;
            }
            visited[pos] = true;
            int[] pos2D = convertTo2D(pos);
            if (depth < G) {
                simBoard[pos2D[0]][pos2D[1]].value = 2;
            } else {
                simBoard[pos2D[0]][pos2D[1]].value = 3;
            }
            permutation(depth + 1, visited);
            simBoard[pos2D[0]][pos2D[1]].value = 1;
            visited[pos] = false;
        }
    }

    static int solution() {
        Box[][] cpyBoard = new Box[N][M];

        Queue<int[]> q = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                cpyBoard[i][j] = new Box(simBoard[i][j].value);
                if (simBoard[i][j].value == 2) {
                    q.add(new int[]{i, j, 0, 0});
                } else if (simBoard[i][j].value == 3) {
                    q.add(new int[]{i, j, 0, 1});
                }
            }
        }

        while (!q.isEmpty()) {
            int[] node = q.poll();
            int r = node[0];
            int c = node[1];
            int time = node[2];
            boolean isGreen = (node[3] == 0);

            if (cpyBoard[r][c].value == 4) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int rr = r + dy[i];
                int cc = c + dx[i];

                if (rr < 0 || rr >= N || cc < 0 || cc >= M) {
                    continue;
                }
                if (cpyBoard[rr][cc].value == 0 || cpyBoard[rr][cc].value == 4) {
                    continue;
                }
                if (cpyBoard[rr][cc].value == 2 && isGreen) {
                    continue;
                }
                if (cpyBoard[rr][cc].value == 3 && !isGreen) {
                    continue;
                }

                if (cpyBoard[rr][cc].value == 3 && cpyBoard[rr][cc].time == time + 1) {
                    cpyBoard[rr][cc].value = 4;
                    continue;
                }
                if (cpyBoard[rr][cc].value == 2 && cpyBoard[rr][cc].time == time + 1) {
                    cpyBoard[rr][cc].value = 4;
                    continue;
                }
                if (cpyBoard[rr][cc].value == 1) {
                    cpyBoard[rr][cc].value = isGreen ? 2 : 3;
                    cpyBoard[rr][cc].time = time + 1;
                    q.offer(new int[]{rr, cc, time + 1, isGreen ? 0 : 1});
                }
            }
        }

        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (cpyBoard[i][j].value == 4) {
                    result += 1;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        answer = 0;
        board = new int[N][M];
        simBoard = new Box[N][M];
        brownPos = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());

                if (board[i][j] == 0) {
                    simBoard[i][j] = new Box(0);
                } else {
                    simBoard[i][j] = new Box(1);
                }

                if (board[i][j] == 2) {
                    brownPos.add(convertTo1D(new int[]{i, j}));
                }
            }
        }

        selectGround(0, 0, new ArrayList<>());
        System.out.println(answer);
    }
}
