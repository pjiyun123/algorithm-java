package com.algorithm.practice.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class n16637 {

    static int ans; // 정답
    static ArrayList<Character> ops; // 연산자
    static ArrayList<Integer> nums; // 피연산자

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String input = br.readLine();

        ops = new ArrayList<>();
        nums = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            char c = input.charAt(i);
            if(c == '+' || c == '-' || c == '*') {
                ops.add(c);
                continue;
            }
            nums.add(Character.getNumericValue(c));
        }

        ans = Integer.MIN_VALUE;
        DFS(nums.get(0), 0);

        System.out.println(ans);

    }

    public static int calc(char op, int n1, int n2) {

        switch(op) {
            case '+':
                return n1 + n2;
            case '-':
                return n1 - n2;
            case '*':
                return n1 * n2;
        }
        return -1;

    }

    public static void DFS(int result, int op_idx) {

        if(op_idx >= ops.size()) {
            ans = Math.max(ans, result);
            return;
        }

        int res1 = calc(ops.get(op_idx), result, nums.get(op_idx+1));
        DFS(res1, op_idx+1);

        if(op_idx + 1 < ops.size()) {
            int res2 = calc(ops.get(op_idx+1), nums.get(op_idx+1), nums.get(op_idx+2));
            DFS(calc(ops.get(op_idx), result, res2), op_idx+2);
        }

    }

}
