package com.algorithm.practice.dataStructures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n10830 {

    static int N;
    static int P = 1000;
    static int[][] map;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        long B = Long.parseLong(st.nextToken());  // 타입 주의

        map = new int[N][N];

        // 행렬 입력
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken()) % P;
            }
        }

        // 행렬 거듭제곱
        int[][] result = pow(map, B);

        // 결과 출력
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                sb.append(result[i][j]).append(' ');
            }
            sb.append('\n');
        }

        System.out.println(sb);

    }

    public static int[][] pow(int[][] base, long expo) {

        //지수가 1일 경우
        if(expo == 1L)
            return base;

        // 지수를 절반으로 분할하여 재귀호출
        int[][] ret = pow(base, expo/2);

        // 절반으로 나뉜 행렬을 제곱
        ret = multiply(ret, ret);

        // 지수가 홀수라면
        // base^(expo/2) * base^(expo/2) * base 이다.
        if(expo % 2 == 1L)
            ret = multiply(ret, base);

        return ret;

    }

    public static int[][] multiply(int[][] o1, int[][] o2) {

        int[][] ret = new int[N][N];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                for(int k = 0; k < N; k++) {
                    ret[i][j] += o1[i][k] * o2[k][j];
                    ret[i][j] %= P;
                }
            }
        }

        return ret;
    }

}
