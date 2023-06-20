package exam.graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 인접리스트 구현 무방향 가중치 그래프 연결요소 가중치 합 최대

class Graph1 {
    private int vertices;
    private List<List<Edge>> adjacencyList;

    public Graph1(int vertices) {
        this.vertices = vertices;
        adjacencyList = new ArrayList<>(vertices);

        for (int i = 0; i < vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    public void addEdge(int src, int dest, int weight) {
        adjacencyList.get(src).add(new Edge(src, dest, weight));
        adjacencyList.get(dest).add(new Edge(dest, src, weight));
    }

    public int getMaxConnectedComponentWeight() {
        boolean[] visited = new boolean[vertices];
        int maxWeight = Integer.MIN_VALUE;

        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                maxWeight = Math.max(maxWeight, dfs(i, visited));
            }
        }

        return maxWeight;
    }

    private int dfs(int currentNode, boolean[] visited) {
        visited[currentNode] = true;
        int totalWeight = 0;

        for (Edge edge : adjacencyList.get(currentNode)) {
            if (!visited[edge.dest]) {
                totalWeight += edge.weight + dfs(edge.dest, visited);
            }
        }

        return totalWeight;
    }
}

class Edge {
    public int src, dest, weight;

    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
}

public class MaxConnectedComp {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/exam/graph/input5.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Graph1 graph = new Graph1(N);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph.addEdge(src, dest, weight);
        }

        br.close();

        int maxWeight = graph.getMaxConnectedComponentWeight();
        System.out.println(maxWeight);
    }
}

