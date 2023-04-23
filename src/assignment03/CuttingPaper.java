package assignment03;

import java.io.*;
import java.util.StringTokenizer;

/* 예제 입력

8
1 1 0 0 0 0 1 1
1 1 0 0 0 0 1 1
0 0 0 0 1 1 0 0
0 0 0 0 1 1 0 0
1 0 0 0 1 1 1 1
0 1 0 0 1 1 1 1
0 0 1 1 1 1 1 1
0 0 1 1 1 1 1 1

 */

public class CuttingPaper {
    static int[][] paper;
    static int blue = 0;
    static int white = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        paper = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        cut(0, 0, N);

        bw.write(String.valueOf(white) + '\n');
        bw.write(String.valueOf(blue) + '\n');

        bw.flush();
        bw.close();
    }

    public static void cut(int row, int col, int size) {
        if(isSameColor(row,col,size)){
            if(paper[row][col] == 1) blue++;
            if(paper[row][col] == 0) white++;
        } else{
            cut(row, col, size/2);
            cut(row, col+size/2, size/2);
            cut(row+size/2, col, size/2);
            cut(row+size/2, col+size/2, size/2);
        }
    }

    public static boolean isSameColor(int row, int col, int size){
        int color = paper[row][col]; //첫번째 색상 기준으로 다음 색들이 같은지 검사
        for(int i = row; i < row+size; i++){
            for(int j = col; j < col+size; j++){
                if(paper[i][j] != color) return false;
            }
        }
        return true;
    }
}

