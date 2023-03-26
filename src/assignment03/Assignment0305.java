package assignment03;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Assignment0305 {
    static int N; // size of Maze
    static int[][] maze;
    static int endX, endY; // 출구의 위치 (x, y)
    static int PATH = 0, WALL = 1, BLOCKED = 2, VISITED = 3;
    // 애국이가 갈 수 있는 방향들 (우 하 좌 상)
    /* 일단 head 방향으로 전진, 조건검사 > 안되면 오른쪽 턴후 head+1
    * 동쪽으로 가고있으면 갈 수 있는 방향은
    * head = 0 (우)일때, 직(0,1)/오(1,0)
    * head = 1 (하)일때, 직(1,0)/오(0,-1)
    * head = 2 (좌)일때, 직(0,-1)/오(-1,0)
    * head = 3 (상)일때, 직(-1,0)/오(0,1)
    * */
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    static int[] head = {};

    public static void main(String[] args) throws IOException {
        String fileName = "input.txt";
        String filePath = "src/assignment03/" + fileName;
        Scanner sc = new Scanner(new File(filePath));

        int T = sc.nextInt();
        for(int curCase = 0; curCase < T; curCase++){
            N = sc.nextInt();
            maze = new int[N][N];
            for (int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++){
                    maze[i][j] = sc.nextInt();
                }
            }
            endX = sc.nextInt();
            endY = sc.nextInt();
            System.out.printf("현재 T%d의 (endX, endY) : (%d, %d)\n", curCase, endX, endY);

            if (findPath(0, 0, 0)) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
    }
// 고려해 볼것 : head기준으로 직진, 우회전 살펴보는데, 안간곳 우선으로 전진
    static boolean findPath(int x, int y, int head){
        int turn = (head+1)%4;
        System.out.printf("(%d, %d)\n", x,y);
        if (x == endX && y == endY) {
            return true;
        }
        int nx = x + dx[head];
        int ny = y + dy[head];

        if(nx < 0 || nx >= N || ny < 0 || ny >= N || maze[nx][ny] == WALL || maze[nx][ny] == BLOCKED) {
            return false;
        }
        if(findPath(x+dx[head], y+dy[head], head) || findPath(x, y, turn)) {
            return true;
        }
        maze[x][y] = BLOCKED;
        return false;
    }
}
