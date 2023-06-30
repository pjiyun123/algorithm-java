package com.algorithm.practice.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class n1138 {

    static int N; // 사람 수
    static int[] arr; // 입력 배열 (자기보다 키가 큰 사람이 왼쪽에 몇 명 있는지)
    static ArrayList<Integer> tall;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 1; i <= N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        tall = new ArrayList<>();
        for(int i = N; i >=1; i--)
            tall.add(arr[i], i);

        for(int k : tall)
            System.out.print(k + " ");

    }

}
