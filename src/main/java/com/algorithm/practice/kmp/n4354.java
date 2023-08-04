package com.algorithm.practice.kmp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class n4354 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String temp = "";
        while(!(temp = br.readLine()).equals(".")) {
            char[] list = temp.toCharArray();
            int len = list.length;
            if(len == 0)
                System.out.println(0);
            else {
                int p[] = findPattern(list);
                if(len % (len - p[len-1]) != 0)
                    System.out.println(1);
                else
                    System.out.println(len / (len - p[len-1]));
            }
        }

    }

    public static int[] findPattern(char[] list) {

        int n = list.length;
        int[] table = new int[n];
        int idx = 0;

        for(int i = 1; i < n; i++) {
            while(idx > 0 && list[i] != list[idx])
                idx = table[idx-1];
            if(list[i] == list[idx]) {
                idx += 1;
                table[i] = idx;
            }
        }

        return table;

    }
}
