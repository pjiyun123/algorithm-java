package com.algorithm.practice.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n14003 {

    static int N; // 수열의 길이
    static int[] arr; // 문제에서 주어지는 수열
    static int[] LIS; // 가장 긴 증가하는 수열
    static int[] idx; // 순서 기억

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        idx = new int[N];
        LIS = new int[N];
        idx[0] = 0;
        LIS[0] = arr[0];
        int lengthOfLIS = 1;
        for(int i = 1; i < N; i++) {
            int key = arr[i];
            if(LIS[lengthOfLIS-1] < key) {
                LIS[lengthOfLIS] = key;
                idx[i] = lengthOfLIS;
                lengthOfLIS++;
            }
            else {
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
                idx[i] = lo;
            }
        }

        System.out.println(lengthOfLIS);
        int k = lengthOfLIS - 1;
        int m = N-1;
        int[] ans = new int[lengthOfLIS];
        while(k >= 0) {
            if(idx[m] == k) {
                ans[k] = arr[m];
                k--;
            }
            m--;
        }
        for(int i = 0; i < lengthOfLIS; i++)
            System.out.print(ans[i] + " ");
    }

}
