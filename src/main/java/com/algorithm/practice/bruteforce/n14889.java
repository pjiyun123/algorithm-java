package com.algorithm.practice.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 스타트와 링크
public class n14889 {

    static int N;
    static int[][] point;
    static int minDiff = Integer.MAX_VALUE; // 스타트팀과 링크팀의 점수 차이 최솟값
    static ArrayList<ArrayList<Integer>> teams = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        point = new int[N+1][N+1];
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 1; j <= N; j++) {
                point[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[] visited = new boolean[N+1];
        int[] arr = new int[N+1];
        ArrayList<Integer> all = new ArrayList<>();
        for(int i = 1; i <= N; i++) {
            arr[i] = i;
            all.add(i);
        }

        makeSet(arr, visited, 1, N/2);

        for(int i = 0; i < teams.size(); i++) {
            ArrayList<Integer> startTeam = teams.get(i);
            ArrayList<Integer> linkTeam = new ArrayList<>();
            boolean[] checked = new boolean[N+1];
            for(int e = 0; e < N/2; e++) {
                checked[startTeam.get(e)] = true;
            }
            for(int e = 1; e <= N; e++) {
                if(!checked[e])
                    linkTeam.add(e);
            }

            int startTeamTotal = 0;
            int linkTeamTotal = 0;
            for(int j = 0; j < N/2 - 1; j++) {
                for(int k = j+1; k < N/2; k++) {
                    int a = startTeam.get(j);
                    int b = startTeam.get(k);
                    startTeamTotal += point[a][b];
                    startTeamTotal += point[b][a];

                    int c = linkTeam.get(j);
                    int d = linkTeam.get(k);
                    linkTeamTotal += point[c][d];
                    linkTeamTotal += point[d][c];
                }
            }

            if(minDiff > Math.abs(startTeamTotal - linkTeamTotal)) {
                minDiff = Math.abs(startTeamTotal - linkTeamTotal);
            }
        }

        System.out.println(minDiff);

    }

    public static void makeSet(int[] arr, boolean[] visited, int start, int r) {
        if(r == 0) {
            ArrayList<Integer> tmp = new ArrayList<>();
            for(int i = 1; i <= N; i++) {
                if(visited[i]) {
                    tmp.add(i);
                    //System.out.print(i + " ");
                }
            }
            //System.out.println();
            teams.add(tmp);
            return;
        }

        for(int i = start; i <= N; i++) {
            visited[i] = true;
            makeSet(arr, visited, i+1, r-1);
            visited[i] = false;
        }
    }

}
