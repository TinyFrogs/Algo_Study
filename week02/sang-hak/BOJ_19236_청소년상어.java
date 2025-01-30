import java.util.*;
import java.io.*;

public class BOJ_19236_청소년상어 {
    /**
     * 물고기 번호 : 1 ~ 16, 같은 번호 없음
     * 물고기 방향 : 상하좌우 + 대각선 총 8가지
     * 0, 0 먹고 0, 0 방향과 같음, 이후 물고기들 이동
     * 물고기는 한 칸을 이동, 빈 칸과 다른 물고기가 있는 칸만 가능(상어 or 범위 밖 X)
     * - 이동할 수 있는 칸을 향할 때까지 45도 "반시계" 회전, 이동 가능 칸 없으면 X
     * 물고기 이동 이후 상어 이동
     * - 여러 칸 이동 가능, 이동하는 중 지나가는 칸의 물고기는 먹지 X
     * - 물고기 칸 이동했다면, 먹고 그 물고기 방향을 가짐
     * - 물고기 없는 칸 이동 불가
     * - 3^15 < 100,000,000 -> dfs로 브루트 포스해서 품.. 백트래킹 안 씀
     */
    static int[] dy = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dx = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int max;
    static class fish {
        int y;
        int x;
        int dir;
        int number;
        boolean isEaten;

        fish(int y, int x, int dir, int number, boolean isEaten) {
            this.y = y;
            this.x = x;
            this.dir = dir;
            this.number = number;
            this.isEaten = isEaten;
        }
    }

    static void proc(int sum, fish[][] space, fish[] list, fish shark) {
        /**
         * 물고기는 항상 번호 순으로 움직여야 하니까 배열 인덱스에 물고기 번호 매칭시킴
         * 먹혔으면 움직일 수 없으니까 continue
         * 모듈러 연산 -> 방향이 1부터 주어지니까 삼항 연산자 사용 (처음에 실수한 부분)
         */
        for (int i = 1; i <= 16; i++) {
            if (list[i].isEaten) continue;

            fish moveFish = list[i];
            for (int j = 0; j < 8; j++) {
                int ny = moveFish.y + dy[moveFish.dir];
                int nx = moveFish.x + dx[moveFish.dir];
                if (ny >= 0 && ny < 4 && nx >= 0 && nx < 4 && space[ny][nx] != shark) {
                    if (space[ny][nx] != null) {
                        fish changedFish = space[ny][nx];
                        int tempY = moveFish.y;
                        int tempX = moveFish.x;
                        space[ny][nx] = moveFish;
                        space[tempY][tempX] = changedFish;
                        moveFish.y = changedFish.y;
                        moveFish.x = changedFish.x;
                        changedFish.y = tempY;
                        changedFish.x = tempX;
                    } else {
                        space[ny][nx] = moveFish;
                        space[moveFish.y][moveFish.x] = null;
                        moveFish.y = ny;
                        moveFish.x = nx;
                    }
                    break;
                }
                moveFish.dir = (moveFish.dir + 1) % 9 == 0 ? 1 : (moveFish.dir + 1) % 9;
            }
        }

        /**
         * 어떤 위치, 어떤 방향이어도 최대 3번 움직일 수 있음
         * so for문으로 3번까지 지나쳐 갈 수 있도록 함
         * dfs 방식이기 때문에 배열 복사(space, list)해야 하는 점 주의
         */
        for (int i = 0; i < 3; i++) {
            shark.y = shark.y + dy[shark.dir];
            shark.x = shark.x + dx[shark.dir];
            if (shark.x >= 0 && shark.x < 4 && shark.y >= 0 && shark.y < 4) {
                if (space[shark.y][shark.x] == null) continue;
                fish[][] temp = new fish[4][4];
                fish[] tempList = new fish[17];

                for (int j = 1; j <= 16; j++) {
                    fish f = list[j];
                    tempList[f.number] = new fish(f.y, f.x, f.dir, f.number, f.isEaten);
                    if (f.isEaten) continue;
                    temp[f.y][f.x] = tempList[f.number];
                }

                temp[shark.y][shark.x].isEaten = true;
                int num = temp[shark.y][shark.x].number;
                int sharkY = shark.y;
                int sharkX = shark.x;
                int sharkDir = shark.dir;
                shark.dir = temp[shark.y][shark.x].dir;
                temp[shark.y][shark.x] = shark;
                proc(sum+num, temp, tempList, shark);
                shark.y = sharkY;
                shark.x = sharkX;
                shark.dir = sharkDir;
                space[shark.y][shark.x].isEaten = false;
            } else {
                break;
            }
        }

        max = Math.max(max, sum);
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        fish[] list = new fish[17];
        fish[][] space = new fish[4][4];
        int num = 0;

        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int number = Integer.parseInt(st.nextToken());
                if (i == 0 && j == 0) num = number;
                int dir = Integer.parseInt(st.nextToken());
                list[number] = new fish(i, j, dir, number, false);
                space[i][j] = list[number];
            }
        }

        max = Integer.MIN_VALUE;
        space[0][0].isEaten = true;
        fish shark = new fish(0, 0, space[0][0].dir, 100, false);
        space[0][0] = shark;
        proc(num, space, list, shark);

        System.out.println(max);
    }
}