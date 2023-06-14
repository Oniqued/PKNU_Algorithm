package exam.graph;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 인접리스트를 이용한 O(n+m)이 되는 경우
public class Coloring2 {
    static int n;
    static List<List<Integer>> adjList;
    static int[] visited;
    static boolean result = true;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("src/exam/graph/input.txt"));
        n = sc.nextInt();
        int m = sc.nextInt();
        adjList = new ArrayList<>();
        visited = new int[n];
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            adjList.get(a).add(b);
            adjList.get(b).add(a);
        }

        dfs(0, false);
        if (result)
            System.out.print("YES");
        else
            System.out.print("NO");
    }

    static void dfs(int v, boolean color) {
        if (visited[v] > 0)
            return;
        if (color)
            visited[v] = 2;
        else
            visited[v] = 1;

        for (int neighbor : adjList.get(v)) {
            if (visited[neighbor] == visited[v]) {
                result = false;
                return;
            } else if (visited[neighbor] == 0) {
                dfs(neighbor, !color);
            }
        }
    }
}
