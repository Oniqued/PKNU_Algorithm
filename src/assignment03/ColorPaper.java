package assignment03;

import java.io.*;
import java.util.StringTokenizer;

// 예제 입력
/*
9
0 0 0 1 1 1 -1 -1 -1
0 0 0 1 1 1 -1 -1 -1
0 0 0 1 1 1 -1 -1 -1
1 1 1 0 0 0 0 0 0
1 1 1 0 0 0 0 0 0
1 1 1 0 0 0 0 0 0
0 1 -1 0 1 -1 0 1 -1
0 -1 1 0 1 -1 0 1 -1
0 1 -1 1 0 -1 0 1 -1
 */

//모든 조각들이 단일 조각으로 이루어진 종이가 될때까지 자르기

public class ColorPaper {
    static int[][] paper;
    // -1
    static int typeA = 0;
    // 0
    static int typeB = 0;
    // 1
    static int typeC = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        paper = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        checkPaper(0, 0, N);
        bw.write(String.valueOf(typeA) + '\n');
        bw.write(String.valueOf(typeB) + '\n');
        bw.write(String.valueOf(typeC) + '\n');

        bw.flush();
        bw.close();
    }

    public static void checkPaper(int row, int col, int size) {
        if (isSame(row, col, size)) {
            if (paper[row][col] == -1) typeA++;
            if (paper[row][col] == 0) typeB++;
            if (paper[row][col] == 1) typeC++;
        } else {
            checkPaper(row, col, size / 3); //1
            checkPaper(row, col + size / 3, size / 3); //2
            checkPaper(row, col + size / 3 * 2, size / 3); //3
            checkPaper(row + size / 3, col, size / 3); //4
            checkPaper(row + size / 3, col + size / 3, size / 3); //5
            checkPaper(row + size / 3, col + size / 3 * 2, size / 3); //6
            checkPaper(row + size / 3 * 2, col, size / 3); //7
            checkPaper(row + size / 3 * 2, col + size / 3, size / 3); //8
            checkPaper(row + size / 3 * 2, col + size / 3 * 2, size / 3); //9
        }

    }

    public static boolean isSame(int row, int col, int size) {
        int type = paper[row][col];
        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                if (type != paper[i][j]) return false;
            }
        }
        return true;
    }
}
