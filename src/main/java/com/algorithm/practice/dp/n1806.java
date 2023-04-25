package com.algorithm.practice.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n1806 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        int sum = 0, left = 0, right = 0;
        int minLength = Integer.MAX_VALUE;
        int change = 0;

        while(true) {
            if(right == N) {
                while(left != N) {
                    if(sum >= S) {
                        if(minLength > right-left)
                            minLength = right-left;
                        change = 1;
                    }
                    sum -= arr[left++];
                }
                break;
            }
            if(sum < S) {
                sum += arr[right++];
            }
            else {
                if(minLength > right-left)
                    minLength = right-left;
                change = 1;
                sum -= arr[left++];
            }
        }

        if(change == 1)
            System.out.println(minLength);
        else
            System.out.println(0);

    }
}
