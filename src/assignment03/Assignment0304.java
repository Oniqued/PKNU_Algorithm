package assignment03;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Assignment0304 {
    static int Max = 0, w, n;
    static int[] chk = new int[17], a = new int[17], b = new int[17];

    public static void main(String[] args) throws IOException {
        String filePath = "src/assignment03/input.txt";
        Scanner sc = new Scanner(new File(filePath));

        n = sc.nextInt();
        w = sc.nextInt();
        for (int i = 0; i < n; i++) { // 무게
            a[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) { // 가격
            b[i] = sc.nextInt();
        }
        DFS(0);
        System.out.printf("%d", Max);
    }

    static void DFS(int k) {
        if (k == n) {
            int maxw = 0, maxv=0;
            for (int i = 0; i < n; i++) {
                if (chk[i] == 1) { // chk가 1인 것만 더한다
                    maxw += a[i];
                    maxv += b[i];
                }
            }
            if (maxw <= w) {
                if (Max <= maxv) {
                    Max = maxv;
                }
            }
        }
        else {
            chk[k] = 1;
            DFS(k + 1);
            chk[k] = 0;
            DFS(k + 1);
        }
    }
}
