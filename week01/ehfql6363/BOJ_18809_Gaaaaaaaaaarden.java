package boj;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_18809_Gaaaaaaaaaarden {

    static int N, M, G, R, res, allDrop, map[][];
    static int[] rows = {-1, 0, 1, 0};
    static int[] cols = {0, 1, 0, -1};
    static ArrayList<Point> area;
    static State[] select;
    static class State {
        int row, col;
        int color;    // 1: 초록, 2: 빨강

        public State(int row, int col, int color) {
            this.row = row;
            this.col = col;
            this.color = color;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());    // 행
        M = Integer.parseInt(st.nextToken());    // 열
        G = Integer.parseInt(st.nextToken());    // 초록색 배양액 개수
        R = Integer.parseInt(st.nextToken());    // 빨간색 배양액 개수
        allDrop = G + R;    // 전체 배양액 개수
        map = new int[N][M];
        select = new State[allDrop];    // 배양액이 뿌려질 좌표 저장
        area = new ArrayList<>();    // 배양액을 뿌릴 수 있는 좌표들 저장
        // 정원 정보 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                // 배양액을 뿌릴 수 있는 땅을 저장
                if(map[i][j] == 2) area.add(new Point(i, j));
            }
        }

        go(0, 0, 0, 0);
        System.out.println(res);
    }

    /**
     * 배양액 뿌리기
     * @param idx    배양액을 뿌릴 수 있는 좌표 index
     * @param cnt    현재까지 뿌린 배양액의 갯수
     * @param cntG    현재까지 뿌린 초록 배양액의 갯수
     * @param cntR  현재까지 뿌린 빨강 배양액의 갯수
     */
    private static void go(int idx, int cnt, int cntG, int cntR) {
        // 모든 배양액을 사용했을 경우
        if(cnt == allDrop) {
            // 배양액이 퍼져나간다.
            // 피울 수 있는 꽃의 최대 개수
            res = Math.max(res, spread());
            return;
        }
        if(idx == area.size())
            return;

        Point now = area.get(idx);

        // 여기에 초록색 배양액 뿌리기
        if(cntG < G) {
            select[cnt] = new State(now.x, now.y, 1);
            go(idx + 1, cnt + 1, cntG + 1, cntR);
        }

        // 여기에 빨간색 배양액 뿌리기
        if(cntR < R) {
            select[cnt] = new State(now.x, now.y, 2);
            go(idx + 1, cnt + 1, cntG, cntR + 1);
        }

        // 여기에 아무것도 뿌리지 않기
        go(idx + 1, cnt, cntG, cntR);
    }

    // 배양액 퍼뜨리기
    private static int spread() {

        // 배양액이 퍼지는데 걸린 시간 저장
        int[][] visitedGrn = new int[N][M];
        int[][] visitedRed = new int[N][M];

        int cnt = 0;
        // 먼저 큐에 배양액이 뿌려져있는 좌표를 넣고
        Queue<State> green = new LinkedList<>();
        Queue<State> red = new LinkedList<>();
        for (int i = 0; i < allDrop; i++) {
            State tmp = select[i];
            // 배양액에 뿌려진 곳은 1로 초기화
            if(select[i].color == 1) {
                green.add(new State(tmp.row, tmp.col, tmp.color));
                visitedGrn[tmp.row][tmp.col] = 1;
            }
            else {
                red.add(new State(tmp.row, tmp.col, tmp.color));
                visitedRed[tmp.row][tmp.col] = 1;
            }
        }

        // 배양액이 모두 퍼질 때까지
        while(!green.isEmpty() || !red.isEmpty()) {

            // 초록색 배양액부터 퍼진다.
            int size = green.size();
            for (int s = 0; s < size; s++) {
                State now = green.poll();
                // 꽃이 핀 자리라면 pass
                if(visitedGrn[now.row][now.col] == -100) continue;
                // 4방 탐색
                for (int d = 0; d < 4; d++) {
                    int dr = now.row + rows[d];
                    int dc = now.col + cols[d];
                    // 범위를 벗어날 경우, 호수가 있을 경우 pass
                    if(dr < 0 || dc < 0 || dr >= N || dc >= M || map[dr][dc] == 0) continue;
                    // 이미 같은 배양액이 뿌려져 있다면 pass
                    if(visitedGrn[dr][dc] != 0) continue;

                    // 초록색 배양액이 뿌려진 적이 없다면 퍼진다
                    visitedGrn[dr][dc] = visitedGrn[now.row][now.col] + 1;
                    green.add(new State(dr, dc, now.color));
                }
            }

            // 다음으로 빨간색 배양액부터 퍼진다.
            size = red.size();
            for (int s = 0; s < size; s++) {
                State now = red.poll();

                // 4방 탐색
                for (int d = 0; d < 4; d++) {
                    int dr = now.row + rows[d];
                    int dc = now.col + cols[d];
                    // 범위를 벗어날 경우, 호수가 있을 경우 pass
                    if(dr < 0 || dc < 0 || dr >= N || dc >= M || map[dr][dc] == 0) continue;
                    // 이미 같은 배양액이 뿌려져 있다면 pass
                    if(visitedRed[dr][dc] != 0) continue;

                    visitedRed[dr][dc] = visitedRed[now.row][now.col] + 1;

                    // 초록색 배양액이 동일한 시간대에 뿌려졌다면 꽃이 핀다.
                    if(visitedGrn[dr][dc] == visitedRed[dr][dc]) {
                        cnt++;
                        // 꽃이 핀 자리는 -100 저장
                        visitedGrn[dr][dc] = -100;
                        visitedRed[dr][dc] = -100;
                        continue;
                    }

                    // 그 외의 경우는 배양액이 퍼진다
                    red.add(new State(dr, dc, now.color));
                }
            }
        }

        return cnt;
    }
}

