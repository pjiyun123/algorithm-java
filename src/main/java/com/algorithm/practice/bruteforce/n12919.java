package com.algorithm.practice.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class n12919 {

    static String S;
    static String T;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        T = br.readLine();

        int result = ab(S, T);
        System.out.println(result);

    }

    public static int ab(String s, String t) {

        if(s.length() == t.length()) {
            if (s.equals(t))
                return 1;
            return 0;
        }

        int ans = 0;
        // A 추가 -> 마지막 글자가 A일 경우 빼기
        if(t.charAt(t.length()-1) == 'A')
            ans += ab(s, t.substring(0, t.length()-1));

        // B 추가하고 뒤집기 -> 맨 앞 글자가 B인 경우 빼고 뒤집기
        if(t.charAt(0) == 'B') {
            String substring = t.substring(1);
            StringBuilder sb = new StringBuilder(substring);
            String string = sb.reverse().toString();
            ans += ab(s, string);
        }

        return ans > 0 ? 1 : 0;
    }

}
