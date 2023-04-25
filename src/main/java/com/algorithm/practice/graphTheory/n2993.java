package com.algorithm.practice.graphTheory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class n2993 {

    static class Loc {
        int i;
        int j;

        public Loc(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    static int height;
    static int width;
    static char[][] graph;
    static int[][] clusters;
    static int stick;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        height = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());
        graph = new char[height][width];

        for(int i = 0; i < height; i++) {
            graph[i] = br.readLine().toCharArray();
        }

        int chance = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < chance; i++) {
            stick = Integer.parseInt(st.nextToken());
            destroyMineral(stick, i%2==0?1:2);
            setCluster();
        }

        // 결과 출력
        for(int i = 0; i < height; i++) {
            System.out.println(graph[i]);
        }

    }

    public static void destroyMineral(int stick, int dir) {

        // dir이 1이면 왼쪽
        if(dir == 1) {
            for(int i = 0; i < width; i++) {
                if(graph[height-stick][i] == 'x') {
                    graph[height-stick][i] = '.';
                    return;
                }
            }
        }

        // dir이 2이면 오른쪽
        else {
            for(int i = width-1; i >=0; i--) {
                if(graph[height-stick][i] == 'x') {
                    graph[height-stick][i] = '.';
                    return;
                }
            }
        }

    }

    public static void setCluster() {

        clusters = new int[height][width]; // 0으로 초기화 됨
        int clusterNum = 1;
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                if(graph[i][j] == 'x' && clusters[i][j] == 0) {
                    // 떠있는 클러스터를 발견할 경우
                    if(findCluster(i, j, clusterNum))
                        return;
                    clusterNum++;
                }
            }
        }

    }

    public static boolean findCluster(int i, int j, int clusterNum) {

        int[] di = {0, 0, -1, 1};
        int[] dj = {1, -1, 0, 0};

        int lowest = -1;

        Queue<Loc> q = new LinkedList<>();
        ArrayList<Loc> arr = new ArrayList<>();

        q.add(new Loc(i, j));
        clusters[i][j] = clusterNum;

        // bfs로 클러스터 찾기
        while(!q.isEmpty()) {
            Loc now = q.poll();
            lowest = Math.max(lowest, now.i);

            for(int d = 0; d < 4; d++) {
                int ni = now.i + di[d];
                int nj = now.j + dj[d];

                if(ni < 0 || nj < 0 || ni >= height || nj >= width)
                    continue;

                if(clusters[ni][nj] == 0 && graph[ni][nj] == 'x') {
                    clusters[ni][nj] = clusterNum;
                    q.add(new Loc(ni, nj));
                }
            }

            // 하나의 클러스터가 arr
            arr.add(now);

        }

        // 클러스터의 가장 아래가 바닥과 맞닿아있지 않을 경우, 클러스터 이동
        if(lowest != height-1) {
            moveCluster(arr);
            return true;
        }

        return false;

    }

    public static void moveCluster(ArrayList<Loc> arr) {

        int move = 1;

        // 원래 자리에 있는 클러스터 지우기
        for(Loc loc : arr) {
            graph[loc.i][loc.j] = '.';
        }

        outerLoop:
        while(true) {
            for(Loc loc : arr) {
                // 밑으로 한칸 내렸을 때 바닥 넘어가거나 다른 클러스터랑 겹치기 전까지만 내리기
                if(loc.i + move == height || graph[loc.i + move][loc.j] == 'x') {
                    move--;
                    break outerLoop;
                }
            }
            move++;
        }

        // 업데이트
        for(Loc loc : arr) {
            graph[loc.i + move][loc.j] = 'x';
        }

    }

}
