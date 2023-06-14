package exam.graph;

/*
* 하나의 연결된 무방향 그래프가 입력으로 주어진다.
* 두 가지 색을 이용하여 정점들을 색칠하는데, 인접한 노드는 서로 다른 색으로 칠해야 한다
*
* NO 입력 예
*
6 9
0 1
2 0
0 5
1 3
4 1
2 4
3 5
5 4
1 5
* */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
public class Coloring {
    static int n;
    static boolean[][] map;
    static int[] visited;
    static boolean result = true;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("src/exam/graph/input.txt"));
        n = sc.nextInt();
        int m = sc.nextInt();
        map = new boolean[n][n];
        visited = new int[n];
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            map[a][b] = true;
            map[b][a] = true;
        }

        dfs(0, false);
        if(result)
            System.out.print("YES");
        else
            System.out.print("NO");


    }

    static void dfs(int v, boolean color) {
        // color : false=1, true = 2
        if (visited[v] > 0)
            return;
        if (color)
            visited[v] = 2;
        else
            visited[v] = 1;

        for (int i = 0; i < n; i++) {
            if (map[v][i]) {
                if (visited[i] == visited[v]) {
                    result = false;
                    return;
                } else if (visited[i] == 0) {
                    dfs(i, !color);
                }
            }
        }
    }

}


