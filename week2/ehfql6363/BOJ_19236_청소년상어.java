package boj;

import java.io.*;
import java.util.*;

public class BOJ_19236_청소년상어 {
    static final int BOARD_SIZE = 4;
    static int maxSum = 0;

    static final int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static final int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};

    static class Fish {
        int num;
        int direction;
        int x;
        int y;
        boolean isAlive;

        public Fish(int num, int direction, int x, int y) {
            this.num = num;
            this.direction = direction;
            this.x = x;
            this.y = y;
            this.isAlive = true;
        }

        public Fish copy() {
            Fish newFish = new Fish(this.num, this.direction, this.x, this.y);
            newFish.isAlive = this.isAlive;
            return newFish;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] board = new int[BOARD_SIZE][BOARD_SIZE];
        Fish[] fishes = new Fish[17];  // 1번부터 16번까지의 물고기 저장

        // 입력 처리
        for (int i = 0; i < BOARD_SIZE; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < BOARD_SIZE; j++) {
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken()) - 1;
                board[i][j] = num;
                fishes[num] = new Fish(num, dir, i, j);
            }
        }

        // 초기 상어 위치(0,0)의 물고기를 먹고 시작
        int firstFishNum = board[0][0];
        Fish firstFish = fishes[firstFishNum];
        int sharkDir = firstFish.direction;
        firstFish.isAlive = false;
        board[0][0] = -1;  // 상어 위치 표시

        // DFS 시작
        dfs(0, 0, sharkDir, firstFishNum, board, fishes);

        System.out.println(maxSum);
    }

    private static void dfs(int sharkX, int sharkY, int sharkDir, int sum, int[][] board, Fish[] fishes) {
        maxSum = Math.max(maxSum, sum);

        // 현재 상태 복사
        int[][] newBoard = copyBoard(board);
        Fish[] newFishes = copyFishes(fishes);

        // 물고기 이동
        moveFishes(newBoard, newFishes);

        // 상어 이동 (최대 3칸까지 이동 가능)
        for (int dist = 1; dist <= 3; dist++) {
            int nx = sharkX + dx[sharkDir] * dist;
            int ny = sharkY + dy[sharkDir] * dist;

            if (isValidPosition(nx, ny) && newBoard[nx][ny] > 0) {
                // 물고기 잡아먹기
                int eatenFishNum = newBoard[nx][ny];
                Fish eatenFish = newFishes[eatenFishNum];
                int nextDir = eatenFish.direction;

                // 상태 변경
                newBoard[sharkX][sharkY] = 0;
                newBoard[nx][ny] = -1;
                eatenFish.isAlive = false;

                // 다음 단계 진행
                dfs(nx, ny, nextDir, sum + eatenFishNum, newBoard, newFishes);

                // 상태 복구
                newBoard[sharkX][sharkY] = -1;
                newBoard[nx][ny] = eatenFishNum;
                eatenFish.isAlive = true;
            }
        }
    }

    private static void moveFishes(int[][] board, Fish[] fishes) {
        for (int num = 1; num <= 16; num++) {
            Fish fish = fishes[num];
            if (!fish.isAlive) continue;

            for (int dir = 0; dir < 8; dir++) {
                int nextDir = (fish.direction + dir) % 8;
                int nx = fish.x + dx[nextDir];
                int ny = fish.y + dy[nextDir];

                if (isValidPosition(nx, ny) && board[nx][ny] != -1) {
                    // 물고기 위치 교환
                    if (board[nx][ny] > 0) {
                        Fish targetFish = fishes[board[nx][ny]];
                        targetFish.x = fish.x;
                        targetFish.y = fish.y;
                    }

                    // 현재 물고기 이동
                    board[fish.x][fish.y] = board[nx][ny];
                    board[nx][ny] = fish.num;
                    fish.x = nx;
                    fish.y = ny;
                    fish.direction = nextDir;
                    break;
                }
            }
        }
    }

    private static boolean isValidPosition(int x, int y) {
        return x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE;
    }

    private static int[][] copyBoard(int[][] board) {
        int[][] newBoard = new int[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            newBoard[i] = board[i].clone();
        }
        return newBoard;
    }

    private static Fish[] copyFishes(Fish[] fishes) {
        Fish[] newFishes = new Fish[17];
        for (int i = 1; i <= 16; i++) {
            if (fishes[i] != null) {
                newFishes[i] = fishes[i].copy();
            }
        }
        return newFishes;
    }
}
