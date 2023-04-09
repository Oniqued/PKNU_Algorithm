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
            rand(data);
            //System.out.println(Arrays.toString(data));
            quickSort(data, 0, N-1);
        }
    }

    private static void rand(int[] data) {
        for (int i = 0; i < data.length; i++) {
            data[i] = (int)(Math.random() * data.length);
        }
    }

    private static void quickSort(int[] data, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(data, left, right);
            quickSort(data, left, pivotIndex-1);
            quickSort(data, pivotIndex+1, right);
        }
    }

    private static int partition(int[] data, int left, int right) {
        int pivot = data[right];
        int i = left-1;
        for (int j = left; j < right; j++) {
            if (data[j] < pivot) {
                i++;
                swap(data, i, j);
            }
        }
        swap(data, i + 1, right);
        return i + 1;
    }

    private static void swap(int[] data, int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

}
