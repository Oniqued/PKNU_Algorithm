package exam.dynamic;

import java.util.Scanner;

public class LongestPalindromeSubstring {
    public static int longestPalindrome(String str) {
        int n = str.length();
        int[][] L = new int[n][n];

        for (int i = 0; i < n; i++) {
            L[i][i] = 1;
        }

        for (int cl = 2; cl <= n; cl++) {
            for (int i = 0; i < n - cl + 1; i++) {
                int j = i + cl - 1;
                if (str.charAt(i) == str.charAt(j) && cl == 2)
                    L[i][j] = 2;
                else if (str.charAt(i) == str.charAt(j))
                    L[i][j] = L[i + 1][j - 1] + 2;
                else
                    L[i][j] = Math.max(L[i][j - 1], L[i + 1][j]);
            }
        }

        return L[0][n - 1];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String str = sc.next();
        System.out.println(longestPalindrome(str));
    }
}

