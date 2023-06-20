package exam.dynamic;

import java.util.Scanner;

// 두 문자열 포함 가장 짧은 문자열
/*
* 7
* ABCBDAB
* 6
* BDCABA
* */

public class SCS2 {
    public static int shortestCommonSupersequence(String x, String y) {
        int m = x.length();
        int n = y.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else if (x.charAt(i - 1) == y.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int M = Integer.parseInt(scanner.nextLine().trim());
        String X = scanner.nextLine().trim();
        int N = Integer.parseInt(scanner.nextLine().trim());
        String Y = scanner.nextLine().trim();
        scanner.close();

        int result = shortestCommonSupersequence(X, Y);
        System.out.println(result);
    }
}
