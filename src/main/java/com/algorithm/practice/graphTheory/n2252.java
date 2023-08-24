package com.algorithm.practice.graphTheory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 줄 세우기
// 위상정렬
public class n2252 {

    static int N; // 학생 수
    static int M;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] edgeCount = new int[N+1]; // 진입차수 저장 배열
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for(int i = 0; i <= N+1; i++)
            graph.add(new ArrayList<Integer>());

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph.get(A).add(B);
            edgeCount[B]++;
        }

        Queue<Integer> q = new LinkedList<>();

        // 진입차수가 0인 값 큐에 넣기
        for(int i = 1; i < edgeCount.length; i++) {
            if(edgeCount[i] == 0)
                q.offer(i);
        }

        while(!q.isEmpty()) {
            int stdNo = q.poll();
            System.out.print(stdNo + " ");
            List<Integer> slist = graph.get(stdNo);
            for(int i = 0; i < slist.size(); i++) {
                int temp = slist.get(i);
                edgeCount[temp]--;
                if(edgeCount[temp] == 0)
                    q.offer(temp);
            }

        }

    }

}
