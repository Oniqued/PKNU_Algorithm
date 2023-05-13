package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DictionaryGraph {
    private Map<String, List<String>> graph; // 그래프를 표현하기 위한 맵

    public DictionaryGraph() {
        graph = new HashMap<>();
    }

    // 그래프에 정점을 추가하는 메서드
    public void addVertex(String word) {
        graph.put(word, new LinkedList<>());
    }

    // 그래프에 에지(연결)를 추가하는 메서드
    public void addEdge(String wordA, String wordB) {
        if (graph.containsKey(wordA) && graph.containsKey(wordB)) {
            List<String> edgesA = graph.get(wordA);
            List<String> edgesB = graph.get(wordB);
            edgesA.add(wordB);
            edgesB.add(wordA);
        }
    }

    // 그래프의 정점 개수와 에지 개수를 출력하는 메서드
    public void printGraphStats() {
        int numVertices = graph.size();
        int numEdges = countEdges();
        System.out.println("Answer1: " + numVertices + " " + numEdges);
    }

    // 그래프에서 차수(degree)가 최대인 정점을 찾아 해당 단어와 차수를 출력하는 메서드
    public void printVertexWithMaxDegree() {
        int maxDegree = 0;
        String maxDegreeWord = "";

        for (Map.Entry<String, List<String>> entry : graph.entrySet()) {
            int degree = entry.getValue().size();
            if (degree > maxDegree) {
                maxDegree = degree;
                maxDegreeWord = entry.getKey();
            }
        }

        System.out.println("Answer2: " + maxDegreeWord + " " + maxDegree);
    }

    // 가장 큰 연결요소(Connected Component)의 크기(정점 개수)를 출력하는 메서드
    public void printLargestConnectedComponentSize() {
        Set<String> visited = new HashSet<>();
        int largestSize = 0;

        for (String word : graph.keySet()) {
            if (!visited.contains(word)) {
                int componentSize = dfs(word, visited);
                largestSize = Math.max(largestSize, componentSize);
            }
        }

        System.out.println("Answer3: " + largestSize);
    }

    // 깊이 우선 탐색(DFS)을 수행하여 연결요소의 크기(정점 개수)를 반환하는 메서드
    private int dfs(String word, Set<String> visited) {
        visited.add(word);
        int size = 1;

        for (String neighbor : graph.get(word)) {
            if (!visited.contains(neighbor)) {
                size += dfs(neighbor, visited);
            }
        }

        return size;
    }

    // 단어와 탐색 깊이를 입력받아 탐색 깊이 이하의 모든 단어를 출력하고, 출력된 단어 개수를 반환하는 메서드
    public int printWordsWithinDepth(String startWord, int depth) {
        Set<String> visited = new HashSet<>();
        List<String> wordsWithinDepth = new ArrayList<>();
        int wordCount = 0;

        dfsWithDepth(startWord, depth, visited, wordsWithinDepth);

        for (String word : wordsWithinDepth) {
            System.out.println(word);
            wordCount++;
        }

        System.out.println("Answer4: " + wordCount);
        return wordCount;
    }

    // 깊이 우선 탐색(DFS)을 수행하여 탐색 깊이 이하의 단어들을 리스트에 저장하는 메서드
    private void dfsWithDepth(String word, int depth, Set<String> visited, List<String> wordsWithinDepth) {
        visited.add(word);
        wordsWithinDepth.add(word);

        if (depth == 0) {
            return;
        }

        for (String neighbor : graph.get(word)) {
            if (!visited.contains(neighbor)) {
                dfsWithDepth(neighbor, depth - 1, visited, wordsWithinDepth);
            }
        }
    }

    // 그래프의 에지 개수를 계산하는 메서드
    private int countEdges() {
        int edgeCount = 0;

        for (List<String> edges : graph.values()) {
            edgeCount += edges.size();
        }

        return edgeCount / 2; // 양방향 연결이므로 에지 개수를 2로 나눔
    }

    public static void main(String[] args) {
        // 파일 읽기 및 그래프 생성
        DictionaryGraph dictionaryGraph = new DictionaryGraph();
        try {
            Scanner scanner = new Scanner(new File("src/test/dict_simplified.txt"));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\t");
                String word = parts[0];
                String description = parts[1];

                // 단어 추가
                dictionaryGraph.addVertex(word);

                // 설명에서 등장하는 단어와 연결
                for (String w : dictionaryGraph.graph.keySet()) {
                    if (description.contains(w)) {
                        dictionaryGraph.addEdge(word, w);
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // 문제 해결
        dictionaryGraph.printGraphStats();
        dictionaryGraph.printVertexWithMaxDegree();
        dictionaryGraph.printLargestConnectedComponentSize();
        dictionaryGraph.printWordsWithinDepth("mountain", 2);
    }
}
