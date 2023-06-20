package exam.graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MaxConnectedComp2_arr {
    private static int N;
    private static int[][] graph;
    private static boolean[] visited;

    public static void main(String[] args) {
        String filePath = "src/exam/graph/input6txt";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            N = Integer.parseInt(reader.readLine());
            graph = new int[N][N];
            visited = new boolean[N];

            // 그래프 정보 읽어오기
            for (int i = 0; i < N; i++) {
                String[] weights = reader.readLine().split(" ");
                for (int j = 0; j < N; j++) {
                    graph[i][j] = Integer.parseInt(weights[j]);
                }
            }

            reader.close();

            int maxWeightSum = 0;

            // 모든 정점을 시작점으로 하여 DFS 탐색
            for (int i = 0; i < N; i++) {
                if (!visited[i]) {
                    int weightSum = dfs(i);
                    if (weightSum > maxWeightSum) {
                        maxWeightSum = weightSum;
                    }
                }
            }

            System.out.println(maxWeightSum);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int dfs(int v) {
        visited[v] = true;
        int weightSum = 0;

        for (int i = 0; i < N; i++) {
            if (!visited[i] && graph[v][i] != 0) {
                weightSum += graph[v][i] + dfs(i);
            }
        }

        return weightSum;
    }
}
