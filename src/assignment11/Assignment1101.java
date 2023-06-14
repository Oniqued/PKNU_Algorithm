package assignment11;

import java.util.Scanner;

public class Assignment1101 {
    public static int minOperations(int K) {
        int operations = 0;
        while (K > 0) {
            if (K % 2 == 0) {
                K /= 2;
            } else {
                K -= 1;
            }
            operations += 1;
        }
        return operations;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int K = scanner.nextInt();
        int result = minOperations(K);
        System.out.println(result);
    }
}
