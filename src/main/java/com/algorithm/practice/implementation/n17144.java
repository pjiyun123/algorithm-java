package com.algorithm.practice.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class n17144 {

    static int R; // 행
    static int C; // 열
    static int T; // 초

    static int[][] room; // 방
    static ArrayList<Loc> robotLoc = new ArrayList<>(); // 공기청정기 위치
    static Queue<Loc> dusts; // 미세먼지

    public static class Loc {

        int r;
        int c;
        int dust = 0;

        public Loc(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public Loc(int r, int c, int dust) {
            this.r = r;
            this.c = c;
            this.dust =dust;
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        room = new int[R][C];

        for(int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < C; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
                // 공기청정기 위치 기억하기
                if(room[i][j] == -1)
                    robotLoc.add(new Loc(i, j));
            }
        }

        for(int t = 0; t < T; t++) {
            checkDust();
            spread();
            clean();
        }

        int total = 0;
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(i == robotLoc.get(0).r && j == robotLoc.get(0).c)
                    continue;
                if(i == robotLoc.get(1).r && j == robotLoc.get(1).c)
                    continue;
                total += room[i][j];
            }
        }

        System.out.println(total);

    }

    public static void checkDust() {

        dusts = new LinkedList<>();
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(room[i][j] == -1 || room[i][j] == 0)
                    continue;
                dusts.add(new Loc(i, j, room[i][j]));
            }
        }

    }

    public static void spread() {

        int[][] newRoom = new int[R][C];
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        while(!dusts.isEmpty()) {

            Loc now = dusts.poll();
            if(now.dust < 5)
                continue;
            int amountofSpread = now.dust / 5;
            int cnt = 0;
            for(int d = 0; d < 4; d++) {
                int nr = now.r + dr[d];
                int nc = now.c + dc[d];

                // 공기청정기를 만나면 패스
                if(nr == robotLoc.get(0).r && nc == robotLoc.get(0).c)
                    continue;
                if(nr == robotLoc.get(1).r && nc == robotLoc.get(1).c)
                    continue;

                // 범위를 벗어나면 패스
                if(nr < 0 || nr >= R || nc < 0 || nc >= C)
                    continue;

                room[nr][nc] += amountofSpread;
                cnt++;
            }
            room[now.r][now.c] -= amountofSpread * cnt;
        }
        
    }

    public static void clean() {

        // 공기청정기 위
        // 아래로 당기기기
        for(int i = robotLoc.get(0).r - 1; i > 0; i--)
            room[i][0] = room[i-1][0];
        // 왼쪽으로 당기기
        for(int i = 0; i < C - 1; i++)
            room[0][i] = room[0][i+1];
        // 위로 당기기
        for(int i = 0; i < robotLoc.get(0).r; i++)
            room[i][C-1] = room[i+1][C-1];
        // 오른쪽으로 당기기
        for(int i = C - 1; i > 1; i--)
            room[robotLoc.get(0).r][i] = room[robotLoc.get(0).r][i-1];
        // 미세먼지 없는 바람
        room[robotLoc.get(0).r][1] = 0;

        // 공기청정기 아래
        // 위로 당기기
        for(int i = robotLoc.get(1).r + 1; i < R - 1; i++)
            room[i][0] = room[i+1][0];
        // 왼쪽으로 당기기
        for(int i = 0; i < C - 1; i++)
            room[R-1][i] = room[R-1][i+1];
        // 아래로 당기기
        for(int i = R - 1; i > robotLoc.get(1).r; i--)
            room[i][C-1] = room[i-1][C-1];
        // 오른쪽으로 당기기
        for(int i = C - 1; i > 1; i--)
            room[robotLoc.get(1).r][i] = room[robotLoc.get(1).r][i-1];
        // 미세먼지 없는 바람
        room[robotLoc.get(1).r][1] = 0;

    }

}
