package assignment04;

public class Assignment0401 {
    static int[] data;
    public static void main(String[] args) {
        System.out.println("\t\t\tN=1000\tN=10000\tN=100000");
        insertionSort();
        bubbleSort();
        selectionSort();
    }

    private static void rand(int N){
        data = new int[N];
        for (int i = 0; i < N; i++) {
            data[i] = (int)Math.random() * N;
        }
    }

    //버블 정렬
    private static void bubbleSort() {
        double sum = 0;
        int loop = 10;
        System.out.print("Bubble\t");
        for (int N = 1000; N <= 100000; N = N * 10) {
            rand(N);
            for (int testCase = 0; testCase < loop; testCase++) {
                long startTime = System.currentTimeMillis();
                for (int i = 0; i < N; i++) {
                    for (int j = i + 1; j < N; j++) {
                        if (data[i] > data[j]) {
                            int tmp = data[j];
                            data[j] = data[i];
                            data[i] = tmp;
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
        double sum = 0;
        int loop = 10;
        System.out.print("Selection");
        for (int N = 1000; N <= 100000; N = N * 10) {
            rand(N);
            for (int testCase = 0; testCase < loop; testCase++) {
                long startTime = System.currentTimeMillis();
                for (int i = 0; i < N - 1; i++) {
                    int minIndex = i;
                    for (int j = i + 1; j < N; j++) {
                        if (data[j] < data[minIndex]) {
                            minIndex = j;
                        }
                    }
                    int temp = data[minIndex];
                    data[minIndex] = data[i];
                    data[i] = temp;
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
        double sum = 0;
        int loop = 10;
        System.out.print("Insertion");
        for (int N = 1000; N <= 100000; N = N * 10) {
            for (int testCase = 0; testCase < loop; testCase++) {
                int data[] = new int[N];
                long startTime = System.currentTimeMillis();
                //구현
                for (int i = N-1; i >= 0; i--) {
                    int index = (int)Math.random()*N;
                    int j = i-1;

                }

                long finishTime = System.currentTimeMillis();
                long elapsedTime = finishTime - startTime;
                sum += elapsedTime;
            }
            System.out.printf("\t%.3f", ((sum/loop)/1000.0));
        }
        System.out.println();
    }

    //합병 정렬
    private void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    private void merge(int[] arr, int l, int m, int r) {
        int[] leftArr = new int[m - l + 1];
        int[] rightArr = new int[r - m];

        for (int i = 0; i < leftArr.length; i++) {
            leftArr[i] = arr[l + i];
        }

        for (int i = 0; i < rightArr.length; i++) {
            rightArr[i] = arr[m + i + 1];
        }

        int i = 0;
        int j = 0;
        int k = l;

        while (i < leftArr.length && j < rightArr.length) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }

        while (i < leftArr.length) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }

        while (j < rightArr.length) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }


}
