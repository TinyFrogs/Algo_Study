import java.util.*;
import java.io.*;

public class BOJ_9663_NQueen {
    static class Location {
        int row;
        int col;

        Location(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    static int cases;
    static boolean diagonalCheck(int row, int col, List<Location> list) {
        for (Location l : list) {
            /**
             * 대각선 체크
             * 왼쪽 위, 오른쪽 아래 : abs(c - d) = abs(a - b)
             * 왼쪽 아래, 오른쪽 위 : c + d = a + b
             */
            if (row - col == l.row - l.col) return false;
            if (row + col == l.row + l.col) return false;
        }
        return true;
    }
    static void dfs(int row, boolean[] col, List<Location> list, int N, Location[][] board) {
        if (row == N) {
            cases++;
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!col[i] && diagonalCheck(row, i, list)) {
                list.add(board[row][i]);
                col[i] = true;
                dfs(row+1, col, list, N, board);
                col[i] = false;
                list.remove(list.size()-1);
            }
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        boolean[] col = new boolean[N];
        Location[][] board = new Location[N][N];
        List<Location> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = new Location(i, j);
            }
        }

        dfs(0, col, list, N, board);

        System.out.println(cases);
    }
}