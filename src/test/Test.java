package test;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Test {
    static int N; // size of Maze
    static int[][] maze;
    static boolean[][] visited;
    static int endX, endY; // position of exit (x, y)
    // Directions that Patriot can go (right, down, left, up)
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int curDir; // current direction (0: right, 1: down, 2: left, 3: up)

    public static void main(String[] args) throws IOException {
        String fileName = "input.txt";
        String filePath = "src/assignment03/" + fileName;
        Scanner sc = new Scanner(new File(filePath));

        int T = sc.nextInt();
        for(int curCase = 0; curCase < T; curCase++){
            N = sc.nextInt();
            maze = new int[N][N];
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++){
                    maze[i][j] = sc.nextInt();
                }
            }
            endX = sc.nextInt();
            endY = sc.nextInt();
            curDir = 0; // start facing right
            System.out.printf("Current position (endX, endY): (%d, %d)\n", endX, endY);

            if (findPath(0, 0)) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
    }

    static boolean findPath(int x, int y){
        System.out.printf("(%d, %d)\n", x, y);
        if (x == endX && y == endY) {
            return true;
        }
        // check straight and right direction in order
        for (int i = 0; i < 2; i++) {
            int nx = x + dx[curDir];
            int ny = y + dy[curDir];
            curDir = (curDir + i) % 4; // update direction
            if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
            if(maze[nx][ny] == 1) continue;
            if (visited[nx][ny]) continue;
            if(findPath(nx, ny)) return true;
        }
        visited[x][y] = true;
        System.out.println("Crashed!");
        return false;
    }
}
