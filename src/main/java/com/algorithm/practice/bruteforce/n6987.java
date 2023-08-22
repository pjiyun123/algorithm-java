package com.algorithm.practice.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 월드컵
public class n6987 {

    static int MAX_TEAM_COUNT = 6;
    static int[][] matches;
    static boolean isEndGame = false;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        // 최대 경기 가능한 경우의 수 구하기
        int size = 0;
        for(int i = 1; i < MAX_TEAM_COUNT; i++)
            size += i;

        // 경기 매치별 팀 저장
        matches = new int[size][2];
        int idx = 0;
        for(int i = 0; i < MAX_TEAM_COUNT - 1; i++) {
            for(int j = i + 1; j < MAX_TEAM_COUNT; j++) {
                matches[idx][0] = i;
                matches[idx][1] = j;
                idx++;
            }
         }

        int t = 4;
        while(t-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int[][] worldCup = new int[3][MAX_TEAM_COUNT];
            boolean isPossible = true;

            for(int i = 0; i < MAX_TEAM_COUNT; i++) {
                worldCup[0][i] = Integer.parseInt(st.nextToken());
                worldCup[1][i] = Integer.parseInt(st.nextToken());
                worldCup[2][i] = Integer.parseInt(st.nextToken());

                if(worldCup[0][i] + worldCup[1][i] + worldCup[2][i] != 5) {
                    isPossible = false;
                    break;
                }
            }

            if(isPossible) {
                check(worldCup, 0, size);
                if(isEndGame)
                    sb.append("1");
                else
                    sb.append("0");
            }
            else
                sb.append("0");

            sb.append(" ");
            isEndGame = false;
        }

        System.out.println(sb.toString());

    }

    public static void check(int[][] worldCup, int matchCount, int size) {

        if(isEndGame)
            return;

        // 모든 게임 수행 가능
        if(matchCount == size) {
            isEndGame = true;
            return;
        }

        int myTeam = matches[matchCount][0];
        int enemyTeam = matches[matchCount][1];

        // 승 -> 패
        if(worldCup[0][myTeam] > 0 && worldCup[2][enemyTeam] > 0) {
            worldCup[0][myTeam]--;
            worldCup[2][enemyTeam]--;
            check(worldCup, matchCount+1, size);
            worldCup[0][myTeam]++;
            worldCup[2][enemyTeam]++;
        }

        // 무 -> 무
        if(worldCup[1][myTeam] > 0 && worldCup[1][enemyTeam] > 0) {
            worldCup[1][myTeam]--;
            worldCup[1][enemyTeam]--;
            check(worldCup, matchCount+1, size);
            worldCup[1][myTeam]++;
            worldCup[1][enemyTeam]++;
        }

        // 패 -> 승
        if(worldCup[2][myTeam] > 0 && worldCup[0][enemyTeam] > 0) {
            worldCup[2][myTeam]--;
            worldCup[0][enemyTeam]--;
            check(worldCup, matchCount+1, size);
            worldCup[2][myTeam]++;
            worldCup[0][enemyTeam]++;
        }

    }

}
