package assignment04;

import java.util.Arrays;

public class Assignment0401 {
    static int[] staticData;
    public static void main(String[] args) {
        System.out.println("\t\t\tN=1000\tN=10000\tN=100000");
        bubbleSort();
        selectionSort();
        insertionSort();

        int[] data;
        double sum;

        // mergeSort
        System.out.print("Merge\t");
        for (int N = 1000; N <= 100000; N = N * 10) {
            data = new int[N];
            sum = 0;
            for (int testCase = 0; testCase < 10; testCase++) {
                rand(data);
                long startTime = System.currentTimeMillis();
                mergeSort(data, 0, N-1);
                long finishTime = System.currentTimeMillis();
                long elapsedTime = finishTime - startTime;
                sum += elapsedTime;
            }
            System.out.printf("\t%.3f", ((sum/10)/1000.0));
        }
        System.out.println();

        //quickSort1
        System.out.print("Quick1\t");
        for (int N = 1000; N <= 100000; N = N * 10) {
            data = new int[N];
            sum = 0;
            for (int testCase = 0; testCase < 10; testCase++) {
                rand(data);
                long startTime = System.currentTimeMillis();
                quickSort(data, 0, N-1);
                long finishTime = System.currentTimeMillis();
                long elapsedTime = finishTime - startTime;
                sum += elapsedTime;
            }
            System.out.printf("\t%.3f", ((sum/10)/1000.0));
        }
        System.out.println();

        //quickSort2
        System.out.print("Quick2\t");
        for (int N = 1000; N <= 100000; N = N * 10) {
            data = new int[N];
            sum = 0;
            for (int testCase = 0; testCase < 10; testCase++) {
                rand(data);
                long startTime = System.currentTimeMillis();
                quickSort2(data, 0, N-1);
                long finishTime = System.currentTimeMillis();
                long elapsedTime = finishTime - startTime;
                sum += elapsedTime;
            }
            System.out.printf("\t%.3f", ((sum/10)/1000.0));
        }
        System.out.println();

        //quickSort3
        System.out.print("Quick3\t");
        for (int N = 1000; N <= 100000; N = N * 10) {
            data = new int[N];
            sum = 0;
            for (int testCase = 0; testCase < 10; testCase++) {
                rand(data);
                long startTime = System.currentTimeMillis();
                quickSort3(data, 0, N-1);
                long finishTime = System.currentTimeMillis();
                long elapsedTime = finishTime - startTime;
                sum += elapsedTime;
            }
            System.out.printf("\t%.3f", ((sum/10)/1000.0));
        }
        System.out.println();

        //Heap
        System.out.print("Heap\t");
        for (int N = 1000; N <= 100000; N = N * 10) {
            data = new int[N];
            sum = 0;
            for (int testCase = 0; testCase < 10; testCase++) {
                rand(data);
                long startTime = System.currentTimeMillis();
                heapSort(data);
                long finishTime = System.currentTimeMillis();
                long elapsedTime = finishTime - startTime;
                sum += elapsedTime;
            }
            System.out.printf("\t%.3f", ((sum/10)/1000.0));
        }
        System.out.println();

        //Library
        System.out.print("Library\t");
        for (int N = 1000; N <= 100000; N = N * 10) {
            data = new int[N];
            sum = 0;
            for (int testCase = 0; testCase < 10; testCase++) {
                rand(data);
                long startTime = System.currentTimeMillis();
                Arrays.sort(data);
                long finishTime = System.currentTimeMillis();
                long elapsedTime = finishTime - startTime;
                sum += elapsedTime;
            }
            System.out.printf("\t%.3f", ((sum/10)/1000.0));
        }
        System.out.println();
    }

    private static void rand(int N){
        staticData = new int[N];
        for (int i = 0; i < N; i++) {
            staticData[i] = (int)(Math.random() * N);
        }
    }

    private static void rand(int[] data) {
        for (int i = 0; i < data.length; i++) {
            data[i] = (int)(Math.random() * data.length);
        }
    }

    private static void loopTimeEx(){
        double sum = 0;
        System.out.print("Bubble\t");
        for (int N = 1000; N <= 100000; N = N * 10) {
            for (int testCase = 0; testCase < 10; testCase++) {
                rand(N);
                long startTime = System.currentTimeMillis();
                //code here
                long finishTime = System.currentTimeMillis();
                long elapsedTime = finishTime - startTime;
                sum += elapsedTime;
            }
            System.out.printf("\t%.3f", ((sum/10)/1000.0));
        }
        System.out.println();
    }

    //버블 정렬
    private static void bubbleSort() {
        int loop = 10;
        System.out.print("Bubble\t");
        for (int N = 1000; N <= 100000; N = N * 10) {
            double sum = 0;
            for (int testCase = 0; testCase < loop; testCase++) {
                rand(N);
                long startTime = System.currentTimeMillis();
                for (int i = 0; i < N; i++) {
                    for (int j = i + 1; j < N; j++) {
                        if (staticData[i] > staticData[j]) {
                            int tmp = staticData[j];
                            staticData[j] = staticData[i];
                            staticData[i] = tmp;
                        }
                    }
                }
                long finishTime = System.currentTimeMillis();
                long elapsedTime = finishTime - startTime;
                sum += elapsedTime;
            }
            System.out.printf("\t%.3f", ((sum/loop)/1000.0));
        }
        System.out.println();
    }

    //선택 정렬
    private static void selectionSort() {
        int loop = 10;
        System.out.print("Selection");
        for (int N = 1000; N <= 100000; N = N * 10) {
            double sum = 0;
            for (int testCase = 0; testCase < loop; testCase++) {
                rand(N);
                long startTime = System.currentTimeMillis();
                for (int i = 0; i < N - 1; i++) {
                    int minIndex = i;
                    for (int j = i + 1; j < N; j++) {
                        if (staticData[j] < staticData[minIndex]) {
                            minIndex = j;
                        }
                    }
                    int temp = staticData[minIndex];
                    staticData[minIndex] = staticData[i];
                    staticData[i] = temp;
                }
                long finishTime = System.currentTimeMillis();
                long elapsedTime = finishTime - startTime;
                sum += elapsedTime;
            }
            System.out.printf("\t%.3f", ((sum/loop)/1000.0));
        }
        System.out.println();
    }

    //삽입 정렬
    private static void insertionSort() {
        int loop = 10;
        System.out.print("Insertion");
        for (int N = 1000; N <= 100000; N = N * 10) {
            double sum = 0;
            for (int testCase = 0; testCase < loop; testCase++) {
                int data[] = new int[N];
                long startTime = System.currentTimeMillis();
                //구현
                for (int i = 0; i < N; i++) {
                    int index = (int)(Math.random()*N);
                    int j = i - 1;
                    while (j >= 0 && index < data[j]){
                        data[j+1] = data[j];
                        j--;
                    }
                    data[j+1] = index;
                }

                long finishTime = System.currentTimeMillis();
                long elapsedTime = finishTime - startTime;
                sum += elapsedTime;
            }
            System.out.printf("\t%.3f", ((sum/loop)/1000.0));
        }
        System.out.println();
    }

    private static void mergeSort(int[] data, int p, int r) {
        int q = (p + r)/2;
        if (p < r) {
            mergeSort(data, p, q);
            mergeSort(data, q+1, r);
            merge(data, p, q, r);
        }
    }

    private static void merge(int[] data, int p, int q, int r) {
        int i = p, j = q + 1, k = p;
        int[] tmp = new int[data.length];
        while (i <= q && j <= r) {
            if(data[i] <= data[j])
                tmp[k++] = data[i++];
            else
                tmp[k++] = data[j++];
        }
        while (i <= q)
            tmp[k++] = data[i++];
        while (j <= r)
            tmp[k++] = data[j++];
        for (i = p; i <= r; i++) {
            data[i] = tmp[i];
        }
    }

    private static void quickSort(int[] data, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(data, left, right);
            quickSort(data, left, pivotIndex - 1);
            quickSort(data, pivotIndex + 1, right);
        }
    }

    private static int partition(int[] data, int left, int right) {
        int pivot = data[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (data[j] < pivot) {
                i++;
                swap(data, i, j);
            }
        }
        swap(data, i + 1, right);
        return i + 1;
    }

    private static void quickSort2(int[] data, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(data, left, right);
            quickSort(data, left, pivotIndex - 1);
            quickSort(data, pivotIndex + 1, right);
        }
    }

    private static int partition2(int[] data, int left, int right) {
        int pivot = data[right/2];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (data[j] < pivot) {
                i++;
                swap(data, i, j);
            }
        }
        swap(data, i + 1, right);
        return i + 1;
    }

    private static void quickSort3(int[] data, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(data, left, right);
            quickSort(data, left, pivotIndex - 1);
            quickSort(data, pivotIndex + 1, right);
        }
    }

    private static int partition3(int[] data, int left, int right) {
        int pivot = data[(int)(Math.random()*right)];
        int i = left - 1;
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

    private static void heapSort(int[] data) {
        int n = data.length;
        // max heap 구성
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(data, n, i);
        }
        // heap에서 하나씩 요소를 뽑아내서 정렬
        for (int i = n - 1; i >= 0; i--) {
            // root(최대값)을 마지막 요소와 교환
            int temp = data[0];
            data[0] = data[i];
            data[i] = temp;
            // heapify
            heapify(data, i, 0);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int largest = i; // root
        int left = 2 * i + 1; // left child
        int right = 2 * i + 2; // right child

        // left child가 root보다 크면 largest 업데이트
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        // right child가 largest보다 크면 largest 업데이트
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        // largest가 root가 아니면 root와 largest 교환 후 heapify
        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;

            heapify(arr, n, largest);
        }
    }

}
