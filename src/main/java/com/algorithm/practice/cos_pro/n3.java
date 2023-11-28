package com.algorithm.practice.cos_pro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 체스의 나이트
public class n3 {

    static int[] dr = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] dc = {1, 2, 2, 1, -1, -2, -2, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String pos = br.readLine(); // 현재 나이트의 위치

        // 나이트의 위치를 좌표로 바꾸기
        int knightR = pos.charAt(0) - 65;
        int knightC = pos.charAt(1) - 49;

        // 나이트가 이동할 수 있는 칸 수 구하기
        int cnt = 0;
        for(int i = 0; i < 8; i++) {
            int nextR = knightR + dr[i];
            int nextC = knightC + dc[i];

            // 나이트가 체스판 밖으로 이동하는 경우
            if(nextR < 0 || nextR >= 8 || nextC < 0 || nextC >= 8)
                continue;

            cnt++;
        }

        System.out.println(cnt);
    }

}
