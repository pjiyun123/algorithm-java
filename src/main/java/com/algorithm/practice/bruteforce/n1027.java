package com.algorithm.practice.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class n1027 {

    static int N; // 빌딩 수
    static long[] height; // 빌딩 높이

    static int[] visible;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        height = new long[N];
        visible = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; i++)
            height[i] = Long.parseLong(st.nextToken());

        count();
        Arrays.sort(visible);
        System.out.println(visible[N-1]);

    }

    public static void count() {

        for(int i = 0; i < N-1; i++) {
            visible[i] += 1; // 바로 옆 건물은 무조건 보임
            visible[i+1] += 1; // 바로 옆 건물은 무조건 보임
            double slope = height[i+1]-height[i]; // 두 건물 간의 기울기 구하기
            for(int j = i+2; j < N; j++) {
                double nextSlope = (double) (height[j]-height[i])/(j-i); // 그 다음 건물과의 기울기 구하기
                if(nextSlope <= slope) // 건물 A에서 B를 볼 수 있으려면 slope보다 nextSlope의 기울기가 커야 한다
                    continue;
                slope = nextSlope; // 건물이 보인다면 기울기 갱신
                visible[i]++;
                visible[j]++;
            }
        }

    }

}
