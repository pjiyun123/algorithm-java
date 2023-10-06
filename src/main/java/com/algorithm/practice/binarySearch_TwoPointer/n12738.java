package com.algorithm.practice.binarySearch_TwoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 가장 긴 증가하는 부분 수열 3
public class n12738 {

    static int N; // 수열 길이
    static int[] arr; // 수열

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        List<Integer> LIS = new ArrayList<>();
        LIS.add(Integer.MIN_VALUE);

        for(int i = 0; i < N; i++) {
            int num = arr[i];
            int left = 1;
            int right = LIS.size() - 1;

            if(num > LIS.get(LIS.size()-1))
                LIS.add(num);
            else {
                while(left < right) {
                    int mid = (left + right) / 2;
                    if(LIS.get(mid) >= num)
                        right = mid;
                    else
                        left = mid+1;
                }
                LIS.set(right, num);
            }
        }

        System.out.println(LIS.size()-1);

    }

}
