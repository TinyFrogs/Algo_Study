package TinyFrogs;

import java.io.*;
import java.util.*;

public class BOJ_18809_Gaaaaaaaaaarden {

    static int N, M, G, R, answer, count;
    static Point[] spreadMap, spreadGreen, spreadRed;
    static int[][] map;
    static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        spreadMap = new Point[10];
        spreadGreen = new Point[G];
        spreadRed = new Point[R];
        answer = 0;

        //배양할 수 있는 위치 찾기
        count = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    spreadMap[count++] = new Point(i, j);
                }
            }
        }

        greenFind(0, 0);
        System.out.print(answer);
    }

    //녹색 설정하는 구간(2 -> 3)
    static void greenFind(int idx, int greenCount) {
        //배양 수가 G보다 작을 때까지
        if (greenCount < G) {
            for (int g = idx; g < count; g++) {
                if (map[spreadMap[g].x][spreadMap[g].y] != 2) continue;

                map[spreadMap[g].x][spreadMap[g].y] = 3;
                greenFind(g + 1, greenCount + 1);
                map[spreadMap[g].x][spreadMap[g].y] = 2;
            }
        } else {
            redFind(0, 0);
        }
    }

    //빨간색 설정하는 구간(2 -> 4)
    static void redFind(int idx, int redCount) {
        //배양 수가 R보다 작을때까지
        if (redCount < R) {
            for (int r = idx; r < count; r++) {
                if (map[spreadMap[r].x][spreadMap[r].y] != 2) continue;

                map[spreadMap[r].x][spreadMap[r].y] = 4;
                redFind(r + 1, redCount + 1);
                map[spreadMap[r].x][spreadMap[r].y] = 2;
            }
        } else {
            //bfs 실행(겹치는 부분 찾기)
            bfs();
            return;
        }
    }

    //초당 겹치는 부분 찾기(꽃 -> 5)
    static void bfs() {
        //Map 임시 맵 필요
        int[][] tempMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                tempMap[i][j] = map[i][j];
            }
        }

        //꽃 개수 초기화
        int flowerCount = 0;

        //3개의 Queue 필요(greenQ, reeQ, AllQ)
        Queue<Point> greenQ = new ArrayDeque<>();
        Queue<Point> redQ = new ArrayDeque<>();

        for (int c = 0; c < count; c++) {
            if (tempMap[spreadMap[c].x][spreadMap[c].y] == 3)
                greenQ.add(spreadMap[c]);
            if (tempMap[spreadMap[c].x][spreadMap[c].y] == 4)
                redQ.add(spreadMap[c]);

        }

        while (!greenQ.isEmpty() || !redQ.isEmpty()) {
            Queue<Point> AllQ = new ArrayDeque<>();
            int greenCount = greenQ.size();
            int redCount = redQ.size();

            //녹색 배양
            for (int g = 0; g < greenCount; g++) {
                Point green = greenQ.poll();
                for (int d = 0; d < 4; d++) {
                    int nx = green.x + dir[d][0];
                    int ny = green.y + dir[d][1];
                    if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                        if (tempMap[nx][ny] == 1 || tempMap[nx][ny] == 2) {
                            AllQ.add(new Point(nx, ny));
                            tempMap[nx][ny] = 6;
                        }
                    }
                }
            }

            //빨간색 배양
            for (int r = 0; r < redCount; r++) {
                Point red = redQ.poll();
                for (int d = 0; d < 4; d++) {
                    int nx = red.x + dir[d][0];
                    int ny = red.y + dir[d][1];
                    if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                        if (tempMap[nx][ny] == 6) {
                            flowerCount++;
                            tempMap[nx][ny] = 5;
                        }
                        if (tempMap[nx][ny] == 1 || tempMap[nx][ny] == 2) {
                            AllQ.add(new Point(nx, ny));
                            tempMap[nx][ny] = 7;
                        }
                    }
                }
            }


            //겹쳐진 위치 확인
            for (Point p : AllQ) {
                if (tempMap[p.x][p.y] == 6) {
                    tempMap[p.x][p.y] = 3;
                    greenQ.add(p);
                }
                if (tempMap[p.x][p.y] == 7) {
                    tempMap[p.x][p.y] = 4;
                    redQ.add(p);
                }
            }
        }
        answer = Math.max(flowerCount, answer);
    }
}
