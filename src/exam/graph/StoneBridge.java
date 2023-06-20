package exam.graph;

import java.io.*;
import java.util.*;

// 연못에 징검다리 문제 (직사각혐)

public class StoneBridge {
    static class Node {
        int x, y, w, h;
        Node(int x, int y, int w, int h) {
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
        }
    }

    static int N;
    static int D;
    static Node[] nodes;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/exam/graph/input4.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        nodes = new Node[N];
        graph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            nodes[i] = new Node(x, y, w, h);
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (isConnected(nodes[i], nodes[j])) {
                    graph[i].add(j);
                    graph[j].add(i);
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N];
        int[] dist = new int[N];
        Arrays.fill(dist, -1);
        queue.add(start);
        visited[start] = true;
        dist[start] = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next : graph[cur]) {
                if (!visited[next]) {
                    visited[next] = true;
                    dist[next] = dist[cur] + 1;
                    queue.add(next);
                }
            }
        }

        if (dist[end] == -1) {
            System.out.println("No path");
        } else {
            System.out.println(dist[end]);
        }
    }

    static boolean isConnected(Node a, Node b) {
        int left = Math.max(a.x, b.x);
        int right = Math.min(a.x + a.w, b.x + b.w);
        int bottom = Math.max(a.y, b.y);
        int top = Math.min(a.y + a.h, b.y + b.h);

        if (right >= left && top >= bottom) return true;

        if (right < left) {
            if (top < bottom) {
                return getDist(right, top, left, bottom) <= D;
            } else if (a.y > b.y + b.h) {
                return getDist(right, bottom, left, top) <= D;
            } else {
                return left - right <= D;
            }
        } else {
            if (a.x > b.x + b.w) {
                return getDist(left, top, right, bottom) <= D;
            } else {
                return bottom - top <= D;
            }
        }
    }


    static double getDist(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
}
