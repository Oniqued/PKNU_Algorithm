package assignment04;

public class Assignment0401 {
    static int[] staticData;
    public static void main(String[] args) {
        System.out.println("\t\t\tN=1000\tN=10000\tN=100000");
//        bubbleSort();
//        selectionSort();
        insertionSort();
        int[] data;
        double sum;
        // mergeSort
        System.out.print("Merge\t");
        for (int N = 1000; N <= 100000; N = N * 10) {
            data = new int[N];
            rand(data);
            sum = 0;
            for (int testCase = 0; testCase < 10; testCase++) {
                long startTime = System.currentTimeMillis();
                mergeSort(data, 0, N-1);
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
        for (int N = 1000; N == 100000; N = N * 10) {
            rand(N);
            for (int testCase = 0; testCase < 10; testCase++) {
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
            rand(N);
            for (int testCase = 0; testCase < loop; testCase++) {
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
            rand(N);
            for (int testCase = 0; testCase < loop; testCase++) {
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
                    while (i != 0 && j >= 0 && index < data[j]){
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
}
