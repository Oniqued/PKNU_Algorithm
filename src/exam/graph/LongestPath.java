package exam.graph;

import java.io.File;
import java.io.IOException;
import java.util.*;

// 도시에 병원을 세웠을 때 그 병원으로부터 가장 멀리 떨어진 도시까지의 거리가 가장 짧아지는 도시는?

class Graph {
    int V;
    List<Edge>[] adjList;

    class Edge {
        int dest;
        int weight;

        Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }

    Graph(int V) {
        this.V = V;
        adjList = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    void addEdge(int src, int dest, int weight) {
        adjList[src].add(new Edge(dest, weight));
        adjList[dest].add(new Edge(src, weight));
    }

    int findFarthestCityFromHospital() {
        int maxDistance = Integer.MIN_VALUE;
        int farthestCity = 0;

        for (int i = 0; i < V; i++) {
            int distance = bfs(i);
            if (distance > maxDistance) {
                maxDistance = distance;
                farthestCity = i;
            }
        }

        return farthestCity;
    }

    int bfs(int src) {
        boolean[] visited = new boolean[V];
        int[] distance = new int[V];
        Queue<Integer> queue = new LinkedList<>();

        visited[src] = true;
        queue.offer(src);

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            for (Edge neighbor : adjList[curr]) {
                int next = neighbor.dest;
                int weight = neighbor.weight;

                if (!visited[next]) {
                    visited[next] = true;
                    distance[next] = distance[curr] + weight;
                    queue.offer(next);
                }
            }
        }

        int maxDistance = 0;
        for (int i = 0; i < V; i++) {
            if (distance[i] > maxDistance) {
                maxDistance = distance[i];
            }
        }

        return maxDistance;
    }
}

public class LongestPath {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("src/exam/graph/input.txt"));
        int N = sc.nextInt();
        int M = sc.nextInt();

        Graph graph = new Graph(N);

        for (int i = 0; i < M; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int d = sc.nextInt();

            graph.addEdge(u, v, d);
        }

        int farthestCity = graph.findFarthestCityFromHospital();
        System.out.println(farthestCity);
    }
}
