package exam.graph;

import java.util.*;
import java.io.*;

class IslandBridge {
    static class Island {
        int x, y, d;

        public Island(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }

    static int N;
    static ArrayList<ArrayList<Integer>> graph;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("src/exam/graph/input3.txt"));

        N = Integer.parseInt(br.readLine());
        ArrayList<Island> islands = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);
            int d = Integer.parseInt(input[2]);
            islands.add(new Island(x, y, d));
        }

        br.close();

        int maxGroupSize = findLargestGroupSize(islands);
        System.out.println(maxGroupSize-1);
    }

    static int findLargestGroupSize(ArrayList<Island> islands) {
        graph = new ArrayList<>();
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        // 섬들을 그래프로 연결
        for (int i = 0; i < N; i++) {
            Island current = islands.get(i);

            for (int j = i + 1; j < N; j++) {
                Island next = islands.get(j);
                int distance = getDistance(current.x, current.y, next.x, next.y);
                int sumRadius = current.d + next.d;

                if (distance <= sumRadius * 2) {
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }

        int maxGroupSize = 0;

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                int groupSize = dfs(i);
                maxGroupSize = Math.max(maxGroupSize, groupSize);
            }
        }

        return maxGroupSize;
    }

    static int dfs(int start) {
        int count = 1;
        visited[start] = true;

        for (int next : graph.get(start)) {
            if (!visited[next]) {
                count += dfs(next);
            }
        }

        return count;
    }

    static int getDistance(int x1, int y1, int x2, int y2) {
        return (int) Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
}

