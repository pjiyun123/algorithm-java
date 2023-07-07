package com.algorithm.practice.graphTheory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class n1446 {

    static int N; // 지름길의 개수
    static int D; // 고속도로의 길이
    static ShortCut[] shortCuts; // 이동하는 최소 거리
    static int[] moveDist; // 최단으로 운전한 거리

    public static class ShortCut implements Comparable<ShortCut> {
        int start;
        int finish;
        int dist;

        public ShortCut(int start, int finish, int dist) {
            this.start = start;
            this.finish = finish;
            this.dist = dist;
        }

        @Override
        public int compareTo(ShortCut o) {
            if(this.start < o.start) {
                return -1;
            }
            return 1;
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        shortCuts = new ShortCut[N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int finish = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            shortCuts[i] = new ShortCut(start, finish, dist);
        }

        // 시작점 기준 오름차순 정렬
        Arrays.sort(shortCuts);

        moveDist = new int[D+1];
        Arrays.fill(moveDist, Integer.MAX_VALUE);


        int nowDist = 0;
        int nowIndex = 0;
        moveDist[0] = 0;

        while(nowDist < D) {
            while (nowIndex < N) {
                if(shortCuts[nowIndex].start != nowDist)
                    break;

                // 지름길 이동
                if(shortCuts[nowIndex].finish <= D) {
                    int goShortCutDist = moveDist[nowDist] + shortCuts[nowIndex].dist;
                    if(goShortCutDist < moveDist[shortCuts[nowIndex].finish])
                        moveDist[shortCuts[nowIndex].finish] = goShortCutDist;
                }

                nowIndex++;
            }

            // 한 칸 이동
            if(moveDist[nowDist] + 1 < moveDist[nowDist + 1]) {
                moveDist[nowDist + 1] = moveDist[nowDist] + 1;
            }

            nowDist++;

        }

        System.out.println(moveDist[D]);

    }

}
