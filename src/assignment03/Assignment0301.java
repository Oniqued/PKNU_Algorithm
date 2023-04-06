package assignment03;

import static java.lang.Math.abs;

public class Assignment0301 {
    static int cnt;
    static int[] cols = new int[100];

    public static void main(String[] args) {
        for (int N = 1; N <= 8; N++) {
            cnt = 0;
            System.out.printf("N[%d] = ", N);
            queens(0, N, cols);
            System.out.printf("%d\n", cnt);
        }
    }

    static void queens(int row, int N, int[] cols) {
        if (row == N) {
            cnt++;
            return;
        }

        for (int col = 0; col < N; col++) {
            cols[row] = col;
            if (promising(cols, row, col)) {
                queens(row + 1, N, cols);
            }
        }
    }

    static boolean promising(int[] cols, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (cols[i] == col) {
                return false;
            }
            else if ((row - i) == abs(col - cols[i])) {
                return false;
            }
        }
        return true;
    }
}
