package com.algorithm.practice.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n14754 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // 세로
        int M = Integer.parseInt(st.nextToken()); // 가로
        long[][] pizzaBox = new long[N][M];

        // 입력받기
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < M; j++) {
                pizzaBox[i][j] = Long.parseLong(st.nextToken());
            }
        }

        // 앞에서 본 모습
        long[] front = new long[M];
        for(int i = 0; i < M; i++) {
            long frontMax = 0;
            for(int j = 0; j < N; j++) {
                if(frontMax < pizzaBox[j][i])
                    frontMax = pizzaBox[j][i];
            }
            front[i] = frontMax;
        }

        // 옆에서 본 모습
        long[] side = new long[N];
        for(int i = 0; i < N; i++) {
            long sideMax = 0;
            for(int j = 0; j < M; j++) {
                if(sideMax < pizzaBox[i][j])
                    sideMax = pizzaBox[i][j];
            }
            side[i] = sideMax;
        }

        // 지울 수 있는 칸 찾기
        long total = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(pizzaBox[i][j] != front[j] && pizzaBox[i][j] != side[i])
                    total += pizzaBox[i][j];
            }
        }

        System.out.println(total);

    }

}
