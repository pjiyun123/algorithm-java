package com.algorithm.practice.implementation;

import java.io.IOException;
import java.util.Scanner;


public class n1016 {

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        Long min = sc.nextLong();
        Long max = sc.nextLong();

        int result = (int) (max-min+1);
        int sqrt = (int) Math.sqrt(max);

        boolean[] checked = new boolean[result];
        for(long i = 2; i <= sqrt; i++) {
            Long squared = i * i;
            Long start = min % squared == 0 ? min / squared : (min / squared) + 1;
            for(Long j = start; j * squared <= max; j++) {
                checked[(int) ((j * squared) - min)] = true;
            }
        }

        int count = 0;
        for(int i = 0; i < result; i++) {
            if(!checked[i]) count++;
        }

        System.out.println(count);

    }
}
