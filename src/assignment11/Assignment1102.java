package assignment11;

import java.util.Scanner;

public class Assignment1102 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        int[] dp = new int[K + 1];

        // 초기값 설정
        dp[0] = 1; // 합이 0인 경우는 아무것도 선택하지 않는 경우 1가지

        // 동적 프로그래밍을 통한 경우의 수 계산
        for (int i = 1; i <= K; i++) {
            if (i - 1 >= 0)
                dp[i] += dp[i - 1];
            if (i - 2 >= 0)
                dp[i] += dp[i - 2];
            if (i - 3 >= 0)
                dp[i] += dp[i - 3];
        }

        System.out.println(dp[K]);
    }
}
