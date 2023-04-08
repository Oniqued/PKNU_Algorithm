package test;

import java.util.Arrays;

public class Test {
    public static void main(String[] args){
//        for (int N = 1000; N == 100000; N = N * 10) {
//
//        }
        int N = 20;
        int[] data;
        for (int i = 0; i < 3; i++) {
            data = new int[N];
            for (int j = 0; j < N; j++) {
                data[j] = (int)(Math.random()*20);
            }
            mergeSort(data, 0, N-1);
            System.out.println(Arrays.toString(data));
        }
    }

    private static void mergeSort(int[] data, int p, int r){
        int q = (p + r) / 2;
        if (p < r) {
            mergeSort(data, p, q);
            mergeSort(data, q+1, r);
            merge(data, p, q, r);
        }
    }

    private static void merge(int[] data, int p, int q, int r){
        int i = p, j = q+1, k = p;
        int[] tmp = new int[data.length];
        while(i <= q && j <= r){
            if (data[i] <= data[j])
                tmp[k++] = data[i++];
            else
                tmp[k++] = data[j++];
        }
        while(i <= q)
            tmp[k++] = data[i++];
        while (j <= r)
            tmp[k++] = data[j++];
        for (i = p; i <= r; i++) {
            data[i] = tmp[i];
        }
    }
}
