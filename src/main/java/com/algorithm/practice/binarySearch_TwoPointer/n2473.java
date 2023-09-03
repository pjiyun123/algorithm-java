package com.algorithm.practice.binarySearch_TwoPointer;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class n2473 {

    static long[] pick = new long[3];
    static long max = 3000000000L;

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        long[] arr = new long[N];
        for(int i = 0; i < N; i++)
            arr[i] = sc.nextLong();
        Arrays.sort(arr);

        for(int i = 0; i < N-2; i++)
            solution(arr, i);

        Arrays.sort(pick);
        System.out.println(pick[0] + " " + pick[1] + " " + pick[2]);

    }

    public static void solution(long[] arr, int index) {
        int left = index+1;
        int right = arr.length-1;

        while(left < right) {
            long sum = arr[left] + arr[right] + arr[index];
            long absSum = Math.abs(sum);

            if(absSum < max) {
                pick[0] = arr[left];
                pick[1] = arr[right];
                pick[2] = arr[index];
                max = absSum;
            }

            if(sum > 0)
                right--;
            else
                left++;
        }
    }



}
