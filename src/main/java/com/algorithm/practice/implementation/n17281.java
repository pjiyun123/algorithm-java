package com.algorithm.practice.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 야구
public class n17281 {

    static int N; // 이닝 수
    static int[][] hit; // 각 이닝 별 타자 결과
    static int maxScore = -1; // 점수

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        hit = new int[N+1][10];
        StringTokenizer st;
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= 9; j++) {
                hit[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[] visited = new boolean[10];
        int[] ord = new int[10];
        findOrd(1, ord, visited);

        System.out.println(maxScore);

    }

    // 타순 구하기
    public static void findOrd(int k, int[] ord, boolean[] visited) {
        if(k == 10) {
            if(ord[4] == 1)
                play(ord);
            return;
        }

        for(int i = 1; i <= 9; i++) {
            if(!visited[i]) {
                visited[i] = true;
                ord[k] = i;
                findOrd(k+1, ord, visited);
                visited[i] = false;
                ord[k] = 0;
            }
        }
    }

    public static void play(int[] nowOrd) {
        int inning = 1;
        int outCount = 0;
        int player = 1;
        boolean[] base = new boolean[4];
        int score = 0;

        while(true) {
            if(outCount == 3) {
                inning++; // 다음 이닝으로
                outCount = 0; // 아웃카운트 초기화
                Arrays.fill(base, false); // 베이스 초기화
            }

            if(inning >= N+1) {
                if(maxScore < score)
                    maxScore = score;
                break;
            }

            // 아웃
            if(hit[inning][nowOrd[player]] == 0) {
                outCount++;
            }

            // 1루타
            else if(hit[inning][nowOrd[player]] == 1) {
                // 3루 주자 홈으로
                if(base[3]) {
                    score++;
                    base[3] = false;
                }
                // 나머지 주자 1루씩 이동
                base[3] = base[2];
                base[2] = base[1];
                base[1] = true;
            }

            // 2루타
            else if(hit[inning][nowOrd[player]] == 2) {
                // 2, 3루 주자 홈으로
                if(base[3]) {
                    score++;
                    base[3] = false;
                }
                if(base[2]) {
                    score++;
                    base[2] = false;
                }
                // 나머지 주자 1루씩 이동
                base[3] = base[1];
                base[1] = false;
                base[2] = true;
            }

            // 3루타
            else if(hit[inning][nowOrd[player]] == 3) {
                // 1, 2, 3루 주자 홈으로
                if(base[3]) {
                    score++;
                    base[3] = false;
                }
                if(base[2]) {
                    score++;
                    base[2] = false;
                }
                if(base[1]) {
                    score++;
                    base[1] = false;
                }
                // 타자 3루로
                base[3] = true;
            }

            // 홈런
            else if(hit[inning][nowOrd[player]] == 4) {
                // 1, 2, 3루 주자, 타자 홈으로
                if(base[3]) {
                    score++;
                    base[3] = false;
                }
                if(base[2]) {
                    score++;
                    base[2] = false;
                }
                if(base[1]) {
                    score++;
                    base[1] = false;
                }
                score++;
            }

            player++;
            if(player == 10)
                player = 1;
        }
    }

}
