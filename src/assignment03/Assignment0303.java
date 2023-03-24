package assignment03;

public class Assignment0303 {
    static int m, n;
    static int[] graph;
    static boolean[][] w;

    public static void main(String[] args) {

    }

    static void coloring(int i) {
        if (promising(i)) {
            if (i == n) {
                graph[1...n] 출력;
            }
            else { //다음 정점에 모든 색을 시도해본다
                for (int color = 1; color <= m; color++) {
                    graph[i+1] = color;
                    coloring(i+1);
                }
            }
        }
    }

    static boolean promising(int i) {
        int j = 1;
        boolean switched = true;
        while(j < i && switched){
            if(w[i][j] && graph[i] == graph[j]) switched = false;
            j++;
        }
        return switched;
    }
}
