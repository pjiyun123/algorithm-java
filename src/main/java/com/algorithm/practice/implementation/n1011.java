package com.algorithm.practice.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n1011 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for(int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            int distance = end - start;
            int max = (int) Math.sqrt(distance);

            if(max == Math.sqrt(distance))
                System.out.println(max*2 - 1);
            else if(distance <= max*max + max)
                System.out.println(max*2);
            else
                System.out.println(max*2 + 1);
        }

    }

}
