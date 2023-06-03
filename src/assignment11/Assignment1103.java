package assignment11;

import java.util.Scanner;

public class Assignment1103 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 막대기의 길이
        int[] prices = new int[N + 1]; // 가격 배열

        // 가격 입력
        for (int i = 1; i <= N; i++) {
            prices[i] = sc.nextInt();
        }

        int[] dp = new int[N + 1]; // 최대 가격을 저장할 배열

        // 동적 프로그래밍을 통한 최대 가격 계산
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] = Math.max(dp[i], dp[i - j] + prices[j]);
            }
        }

        System.out.println(dp[N]); // 최대 가격 출력
    }
}
