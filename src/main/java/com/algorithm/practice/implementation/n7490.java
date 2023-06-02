package com.algorithm.practice.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class n7490 {

    static int n;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for(int i = 0; i < t; i++) {
            n = Integer.parseInt(br.readLine());
            sb = new StringBuilder();
            dfs(1, 1, 0, 1, "1");
            System.out.println(sb);
        }

    }

    public static void dfs(int idx, int num, int sum, int op, String express) {

        if(idx == n) {
            sum += num * op;
            if(sum == 0)
                sb.append(express + "\n");
            return;
        }

        dfs(idx+1, num*(10)+(idx+1), sum, op, express+" "+Integer.toString(idx+1));
        dfs(idx+1, idx+1, sum+(num*op), 1, express+"+"+Integer.toString(idx+1));
        dfs(idx+1, idx+1, sum+(num*op), -1, express+"-"+Integer.toString(idx+1));

    }

}
