package com.programmers.implementation;

import java.io.IOException;

public class n18187 {

    public static void main(String[] args) throws IOException {

        long answer = 0;
        int r1 = 2;
        int r2 = 3;

        for(long i = 1; i <= r2; i++) {
            double y2 = Math.sqrt(Math.pow(r2, 2) - Math.pow(i, 2));
            double y1 = Math.sqrt(Math.pow(r1, 2) - Math.pow(i, 2));
            answer += (long)y2 - (long)Math.ceil(y1) + 1;
        }
        answer *= 4;
        System.out.println(answer);
    }
}
