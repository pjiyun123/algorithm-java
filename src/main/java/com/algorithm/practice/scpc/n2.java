package com.algorithm.practice.scpc;

import java.util.*;

public class n2 {
    static int Answer;
    static int N;
    static int D;
    static Map<Integer, sb> map;
    static boolean[] visited;

    public static class sb {
        int cnt;
        int idx;
        public sb(int cnt, int idx) {
            this.cnt = cnt;
            this.idx = idx;
        }
    }

    public static void main(String args[]) throws Exception	{

        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int T = sc.nextInt();
        for(int test_case = 1; test_case <= T; test_case++) {

            N = sc.nextInt(); // 딸기 개수
            D = sc.nextInt(); // 로봇 최대 이동 가능 거리
            map = new HashMap<>();
            int k = 0;
            for(int i = 0; i < N; i++) {
                int loc = sc.nextInt(); // 딸기 위치
                if(map.containsKey(loc))
                    map.replace(loc, new sb(map.get(loc).cnt+1, map.get(loc).idx));
                else
                    map.put(loc, new sb(1, k++));
            }

            visited = new boolean[map.size()];
            Answer = 0;
            move(0, 0, 0);

            // Print the answer to standard output(screen).
            sb.append("Case #" + test_case + "\n");
            sb.append(Answer + "\n");
        }
        System.out.print(sb);

    }

    public static void move(int moveLen, int nowLoc, int strawberry) {

        for(Integer loc : map.keySet()) {
            int moveDir = Math.abs(nowLoc - loc); // 이동해야 하는 거리
            if(!visited[map.get(loc).idx]) {
                // 이동가능한 경우
                if(moveLen + moveDir <= D) {
                    visited[map.get(loc).idx] = true;
                    move(moveLen + moveDir, loc, strawberry + map.get(loc).cnt);
                    visited[map.get(loc).idx] = false;
                }
                // 이동불가능한 경우
                else {
                    if(Answer < strawberry)
                        Answer = strawberry;
                    return;
                }
            }
        }

    }

}
