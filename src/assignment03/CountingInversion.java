package assignment03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 예제 입력
7
4 2 7 1 5 6 3
 */

public class CountingInversion {
    static int[] tmp;
    static int[] arr;
    static long inversion;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N  = Integer.parseInt(br.readLine());   // 순열 길이
        arr = new int[N];
        tmp = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        inversion = 0;

        mergeSort(0, N-1);

        System.out.println(inversion);
    }

    // divide and conquer
    static void mergeSort(int start, int end) {
        if (start >= end)   // 끝까지 분할 한 경우
            return;

        int mid = (start + end) >> 1; // 중앙 값
        mergeSort(start, mid);  // 좌측 (divide)
        mergeSort(mid+1, end);  // 우측 (divide)
        merge(start, end);  // 병합 (conquer)
    }

    static void merge(int left, int right) {
        int mid = (left + right) >> 1;
        int leftIdx = left;     // 좌측 배열 탐색 인덱스
        int rightIdx = mid+1;   // 우측 배열 탐색 인덱스
        int tempIdx = left;     // 임시 저장 배열 인덱스

        while (leftIdx <= mid && rightIdx <= right) {
            if(arr[leftIdx] <= arr[rightIdx]) {
                tmp[tempIdx++] = arr[leftIdx++];
            } else {
                // inversion 발생 경우 (leftIdx < rightIdx && arr[leftIdx] > arr[rightIdx] )
                tmp[tempIdx++] = arr[rightIdx++];
                inversion += (mid - leftIdx + 1);   // 교차점 count
            }
        }

        // 좌측 잔여 원소 배치
        while(leftIdx <= mid) {
            tmp[tempIdx++] = arr[leftIdx++];
        }

        // 우측 잔여 원소 배치
        while(rightIdx <= right) {
            tmp[tempIdx++] = arr[rightIdx++];
        }

        // copy array
        for(int i = left; i<=right; i++) {
            arr[i] = tmp[i];
        }
    }
}
