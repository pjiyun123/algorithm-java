package com.algorithm.practice.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class n15686 {

    public static class Loc {
        int r, c;

        public Loc(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static ArrayList<Loc> house = new ArrayList<>(); // 집 위치 저장
    static ArrayList<Loc> chicken = new ArrayList<>(); // 치킨집 위치 저장
    static int N; // 도시 크기
    static int M; // 치킨집 최대 개수
    static boolean[] open;
    static int ans;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken()); // 도시 크기
        M = Integer.parseInt(st.nextToken()); // 치킨집 최대 개수

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j++) {
                int now = Integer.parseInt(st.nextToken());
                // 집
                if(now == 1)
                    house.add(new Loc(i, j));
                // 치킨집
                else if(now == 2)
                    chicken.add(new Loc(i, j));
            }
        }

        open = new boolean[chicken.size()];
        ans = Integer.MAX_VALUE;
        DFS(0, 0);

        System.out.println(ans);

    }

    public static void DFS(int start, int cnt) {
        if(cnt == M) {
            int res = 0;

            for(int i = 0 ; i < house.size(); i++) {
                int temp = Integer.MAX_VALUE;
                for(int j = 0; j < chicken.size(); j++) {
                    if(open[j]) {
                        int dist = Math.abs(house.get(i).r - chicken.get(j).r) + Math.abs(house.get(i).c - chicken.get(j).c);
                        temp = Math.min(temp, dist);
                    }
                }
                res += temp;
            }
            ans = Math.min(ans, res);
            return;
        }

        for(int i = start; i < chicken.size(); i++) {
            open[i] = true;
            DFS(i+1, cnt+1);
            open[i] = false;
        }
    }

}
