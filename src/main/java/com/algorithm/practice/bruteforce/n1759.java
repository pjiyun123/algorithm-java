package com.algorithm.practice.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class n1759 {

    static int L; // 암호의 길이
    static int C; // 주어지는 알파벳 개수
    static char[] alphabet; // 주어지는 알파벳
    static boolean[] checked;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        alphabet = new char[C];
        checked = new boolean[C];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < C; i++) {
            char tmp = st.nextToken().charAt(0);
            alphabet[i] = tmp;
        }

        Arrays.sort(alphabet);
        makePassword(0, 0);

    }

    public static void makePassword(int level, int length) {

        if(length == L) {
            StringBuilder sb = new StringBuilder();
            int moeumCnt = 0;
            int gaeumCnt = 0;

            for(int i = 0; i < C; i++) {
                if(checked[i]) {
                    char tmp = alphabet[i];
                    if(tmp == 'a' || tmp == 'e' || tmp == 'i' || tmp == 'o' || tmp == 'u')
                        moeumCnt++;
                    else
                        gaeumCnt++;
                    sb.append(tmp);
                }
            }

            if(moeumCnt >= 1 && gaeumCnt >= 2)
                System.out.println(sb);
        }
        else {
            for(int i = level; i < C; i++) {
                checked[i] = true;
                makePassword(i+1, length+1);
                checked[i] = false;
            }
        }

    }

}
