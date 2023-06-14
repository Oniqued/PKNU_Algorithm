package exam.graph;

import java.io.File;
import java.io.IOException;
import java.util.*;

// 그래프가 강연결인지 검사 하는 코드

public class StronglyConnectedGraph {
    private int[][] adjacencyMatrix;
    private int numVertices;

    public boolean isStronglyConnected(int[][] graph) {
        numVertices = graph.length;
        adjacencyMatrix = graph;

        // 모든 정점을 시작점으로 DFS 수행
        for (int v = 0; v < numVertices; v++) {
            boolean[] visited = new boolean[numVertices];
            dfs(v, visited);

            // 방문한 정점의 수가 전체 정점의 수와 같지 않다면 강연결 그래프가 아님
            if (countVisited(visited) != numVertices)
                return false;
        }

        // 역방향 그래프를 생성하여 다시 DFS 수행
        int[][] reverseGraph = reverseGraph(adjacencyMatrix);
        boolean[] visited = new boolean[numVertices];
        dfs(0, visited, reverseGraph);

        // 방문한 정점의 수가 전체 정점의 수와 같지 않다면 강연결 그래프가 아님
        if (countVisited(visited) != numVertices)
            return false;

        return true;
    }

    private void dfs(int vertex, boolean[] visited) {
        visited[vertex] = true;

        for (int v = 0; v < numVertices; v++) {
            if (adjacencyMatrix[vertex][v] == 1 && !visited[v])
                dfs(v, visited);
        }
    }

    private void dfs(int vertex, boolean[] visited, int[][] graph) {
        visited[vertex] = true;

        for (int v = 0; v < numVertices; v++) {
            if (graph[vertex][v] == 1 && !visited[v])
                dfs(v, visited, graph);
        }
    }

    private int[][] reverseGraph(int[][] graph) {
        int[][] reverse = new int[numVertices][numVertices];

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                reverse[i][j] = graph[j][i];
            }
        }

        return reverse;
    }

    private int countVisited(boolean[] visited) {
        int count = 0;
        for (boolean v : visited) {
            if (v) count++;
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        // 입력 그래프 생성
        Scanner sc = new Scanner(new File("src/exam/graph/input2.txt"));
        int n = sc.nextInt();
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        // 강연결 그래프인지 검사
        StronglyConnectedGraph checker = new StronglyConnectedGraph();
        boolean isStronglyConnected = checker.isStronglyConnected(graph);

        if (isStronglyConnected) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}

