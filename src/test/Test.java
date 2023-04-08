package test;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        insertionSort(20);
    }

    private static void insertionSort(int N){
        int[] data = new int[N];
        for (int i = 0; i < N; i++) {
            int index = (int)(Math.random()*N);
            System.out.println(index);
            int j = i - 1;
            while (i != 0 && j >= 0 && index < data[j]){
                data[j+1] = data[j];
                j--;
            }
            data[j+1] = index;
            System.out.println(Arrays.toString(data));
        }

    }
}
