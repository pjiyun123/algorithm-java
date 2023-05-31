package com.algorithm.practice.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class n12869 {

    static int N;
    static ArrayList<Integer> scv;
    static int[][][] visited;
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // SCV의 수

        scv = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; i++)
            scv.add(Integer.parseInt(st.nextToken()));

        for(int i = N; i < 3; i++)
            scv.add(0);

        visited = new int[61][61][61];
        cal(scv.get(0), scv.get(1), scv.get(2), 0);

        System.out.println(result);

    }

    public static void cal(int a, int b, int c, int cnt) {

        a = Math.max(0, a);
        b = Math.max(0, b);
        c = Math.max(0, c);

        int max = Math.max(Math.max(a, b), c);
        int min = Math.min(Math.min(a, b), c);
        int mid = a + b + c - max - min;
        a = max;
        b = mid;
        c = min;

        if(a <= 0 && b <= 0 && c <= 0) {
            result = Math.min(cnt, result);
            return;
        }

        if(visited[a][b][c] == 1)
            return;
        else
            visited[a][b][c] = 1;

        if(result < cnt)
            return;

        cal(a-9, b-3, c-1, cnt+1);
        cal(a-9, b-1, c-3, cnt+1);
        cal(a-3, b-9, c-1, cnt+1);
        cal(a-3, b-1, c-9, cnt+1);
        cal(a-1, b-9, c-3, cnt+1);
        cal(a-1, b-3, c-9, cnt+1);

        return;

    }

}
