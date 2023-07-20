package com.algorithm.practice.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n12015 {

    static int N; // 수열의 길이
    static int[] arr; // 문제의 수열
    static int[] LIS; // 증가하는 가장 긴 수열

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        LIS = new int[N];
        LIS[0] = arr[0];
        int lengthOfLIS = 1;

        for(int i = 1; i < N; i++) {
            int key = arr[i];
            // 만약 key가 LIS의 마지막 값보다 크면 추가해주기
            if(LIS[lengthOfLIS-1] < key) {
                LIS[lengthOfLIS] = key;
                lengthOfLIS++;
            }
            else {
                // Lower Bound 이분탐색 진행하기
                int lo = 0;
                int hi = lengthOfLIS;
                while(lo < hi) {
                    int mid = (lo + hi) / 2;
                    if(LIS[mid] < key)
                        lo = mid + 1;
                    else
                        hi = mid;
                }
                LIS[lo] = key;
            }
        }

        System.out.println(lengthOfLIS);

    }

}
