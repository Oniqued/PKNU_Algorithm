package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test {
    static int[][] board;
    static int n, x, y, x_, y_;
    static int[] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visited;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("C:\\Users\\Shawn Kim\\Desktop\\Files\\PKNU\\3-1\\Algorithm\\src\\assignment02\\input.txt"));

        // 입력 받기
        n = sc.nextInt();
        board = new int[n][n];
        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = sc.nextInt();
            }
        }
        x = sc.nextInt();
        y = sc.nextInt();
        x_ = sc.nextInt();
        y_ = sc.nextInt();

        // 시작 위치에서 도착 위치로 이동 가능한지 검사
        if (canMove(x, y)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

        sc.close();
    }

    // 재귀 함수
    private static boolean canMove(int sx, int sy) {
        visited[sx][sy] = true;
        if (sx == x_ && sy == y_) { // 목적지에 도착한 경우
            return true;
        }

        for (int i = 0; i < 4; i++) { // 상하좌우 네 방향 탐색
            int cnt = 0; // 건너뛰어야 하는 말의 개수
            int nx = sx + dx[i];
            int ny = sy + dy[i];

            if (nx < 0 || nx >= n || ny < 0 || ny >= n) { // 범위 체크
                continue;
            }

            if (board[nx][ny] == 1) { // 건너뛰어야 하는 말이 있는 경우
                int mx = (sx + nx) / 2;
                int my = (sy + ny) / 2;
                if (!visited[mx][my] && canMove(nx, ny)) { // 건너뛸 말이 하나인 경우 이동 가능
                    return true;
                }
            } else if (!visited[nx][ny] && canMove(nx, ny)) { // 건너뛰어야 하는 말이 없는 경우
                return true;
            }
        }
        return false;
    }
}
