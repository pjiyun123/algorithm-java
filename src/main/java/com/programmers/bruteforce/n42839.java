package com.programmers.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class n42839 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int answer = 0;
        String numbers = br.readLine();

        Set<Integer> set = new HashSet<>();
        List<String> allNums = new ArrayList<>(
                Arrays.asList(numbers.split(""))
        );

        for(int i = 0; i < allNums.size(); i++) {
            Collections.sort(allNums);
            String current = allNums.remove(i);
            permutation(set, allNums, current);
            allNums.add(current);
        }

        for(Integer i : set) {
            if(isPrimeNumber(i)) answer++;
        }

        System.out.println(answer);

    }

    public static void permutation(Set<Integer> set, List<String> all, String current) {

        // 다음거 추가하지 않는 경우
        set.add(Integer.parseInt(current));

        if(all.size() == 0) return;

        for(int i = 0; i < all.size(); i++) {
            String next = all.remove(0);
            permutation(set, all, current+next);
            all.add(next);
        }
    }

    public static boolean isPrimeNumber(int number) {
        int sqrt = (int) Math.sqrt(number);

        if(number <= 1) return false;
        for(int i = 2; i <= sqrt; i++) {
            if(number % i == 0) return false;
        }

        return true;
    }
}
