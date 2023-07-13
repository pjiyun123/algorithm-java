package com.algorithm.practice.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class n9935 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String string1 = br.readLine();
        String string2 = br.readLine();
        int string2Len = string2.length();

        Stack<Character> st = new Stack<>();

        for(int i = 0; i < string1.length(); i++) {
            st.push(string1.charAt(i));

            if(st.size() >= string2Len) {
                boolean flag = true;

                for(int j = 0; j < string2Len; j++) {
                    if(st.get(st.size() - string2Len + j) != string2.charAt(j)) {
                        flag = false;
                        break;
                    }
                }

                if(flag) {
                    for(int j = 0; j < string2Len; j++)
                        st.pop();
                }
            }
        }

        for(Character c: st)
            sb.append(c);

        System.out.println(sb.length()==0 ? "FRULA" : sb.toString());

    }

}
