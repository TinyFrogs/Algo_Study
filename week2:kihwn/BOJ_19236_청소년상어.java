import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
public class Main {
	static int[][] board = new int[4][4];
	static int[][] direction = new int[4][4];
	static int[] posX = new int[17];
	static int[] posY = new int[17];
	static int ans = 0;
	static int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
	static int[] dy = {1, 0, -1, -1, -1, 0, 1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 4; i++) {
			String s = br.readLine();
			String[] sarr = s.split(" ");
			for (int j = 0; j < 4; j++) {
				board[i][j] = Integer.parseInt(sarr[j * 2]);
				direction[i][j] = Integer.parseInt(sarr[j * 2 + 1]) % 8;
				posX[board[i][j]] = i;
				posY[board[i][j]] = j;
			}
		}
		dfs(0, 0, 0, board, direction, posX, posY);

		System.out.println(ans);
	}

	public static boolean valid(int x, int y) {
		return x >= 0 && x < 4 && y >= 0 && y < 4;
	}


	public static void dfs(int x, int y, int curAns, int[][] prevBoard, int[][] prevDir, int[] prevX, int[] prevY) {
		int[][] curBoard = new int[4][4];
		int[][] curDirection = new int[4][4];
		int[] curX = new int[17];
		int[] curY = new int[17];

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				curBoard[i][j] = prevBoard[i][j];
				curDirection[i][j] = prevDir[i][j];
			}
		}

		for (int i = 1; i < 17; i++) {
			curX[i] = prevX[i];
			curY[i] = prevY[i];
		}

		int sharkDirection = curDirection[x][y];
		curAns += curBoard[x][y];
		if (curAns > ans)
			ans = curAns;

		curX[curBoard[x][y]] = -1;
		curBoard[x][y] = 0; // shark


		for (int i = 1; i < 17; i++) {
			if (curX[i] == -1) {
				continue;
			}

			for (int j = 0; j < 8; j++) {
				int targetX = curX[i] + dx[curDirection[curX[i]][curY[i]]];
				int targetY = curY[i] + dy[curDirection[curX[i]][curY[i]]];

				if (!valid(targetX, targetY)) {
					curDirection[curX[i]][curY[i]] += 1;
					curDirection[curX[i]][curY[i]] %= 8;
					continue;
				}
				if (curBoard[targetX][targetY] == 0) {
					curDirection[curX[i]][curY[i]] += 1;
					curDirection[curX[i]][curY[i]] %= 8;
					continue;
				}

				if (curBoard[targetX][targetY] == -1) {
					int prevDirection = curDirection[curX[i]][curY[i]];
					curBoard[curX[i]][curY[i]] = -1;
					curX[i] = targetX;
					curY[i] = targetY;
					curBoard[targetX][targetY] = i;
					curDirection[targetX][targetY] = prevDirection;
				} else {
					int swap;

					swap = curBoard[targetX][targetY];
					curBoard[targetX][targetY] = i;
					curBoard[curX[i]][curY[i]] = swap;

					swap = curDirection[targetX][targetY];
					curDirection[targetX][targetY] = curDirection[curX[i]][curY[i]];
					curDirection[curX[i]][curY[i]] = swap;

					curX[curBoard[curX[i]][curY[i]]] = curX[i];
					curY[curBoard[curX[i]][curY[i]]] = curY[i];

					curX[i] = targetX;
					curY[i] = targetY;
				}
				break;
			}
		}

		curBoard[x][y] = -1; // empty
		while (valid(x + dx[sharkDirection], y + dy[sharkDirection])) {
			x += dx[sharkDirection];
			y += dy[sharkDirection];
			if (curBoard[x][y] < 1)
				continue;
			dfs(x, y, curAns, curBoard, curDirection, curX, curY);
		}
	}
}
