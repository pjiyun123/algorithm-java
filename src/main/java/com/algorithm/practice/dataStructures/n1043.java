package com.algorithm.practice.dataStructures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 거짓말
public class n1043 {


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] info = new int[M][N+1]; // 파티별 참석인 정보
        Queue<Integer> partyQue = new LinkedList<>();
        Queue<Integer> knowQue = new LinkedList<>();
        boolean[] manVisited = new boolean[N+1];
        boolean[] partyVisited = new boolean[M];

        st = new StringTokenizer(br.readLine(), " ");
        int knowFactCnt = Integer.parseInt(st.nextToken());
        for(int i = 0; i < knowFactCnt; i++) {
            int per = Integer.parseInt(st.nextToken());
            knowQue.add(per);
            manVisited[per] = true;
        }

        for(int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine(), " ");
            int partypeople = Integer.parseInt(st.nextToken());
            for(int p = 0; p < partypeople; p++) {
                int tmp = Integer.parseInt(st.nextToken());
                info[m][tmp] = 1;
            }
        }

        while(!knowQue.isEmpty()) {
            int nowMan = knowQue.poll();
            for(int i = 0; i < M; i++) {
                if(info[i][nowMan] == 1) {
                    partyQue.add(i);
                    partyVisited[i] = true;
                }
            }

            while(!partyQue.isEmpty()) {
                int nowParty = partyQue.poll();
                for(int j = 1; j <= N; j++) {
                    if(info[nowParty][j] == 1 && !manVisited[j]) {
                        knowQue.add(j);
                        manVisited[j] = true;
                    }
                }
            }
        }

        int check = 0;
        for(int i = 0; i < M; i++) {
            if(!partyVisited[i])
                check++;
        }

        System.out.println(check);

    }
}
