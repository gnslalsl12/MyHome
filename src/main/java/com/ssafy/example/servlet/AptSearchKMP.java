package com.ssafy.example.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AptSearchKMP {
    
    static int[] table;
    
    public static void main(String[] args) throws Exception {
        System.out.println(KMP("래미안 아파트", "래미안"));
        System.out.println(KMP("래미안 아파트", "래미안 아파트2"));
    }
    
    // 문자열 매칭 (매칭되면 1, 안되면 0 리턴)
    // 파라미터 aptName: 현재 확인할 아파트 이름 / pattern: 검색 문자열
    public static int KMP(String aptName, String pattern) {
        if(sizeCheck(aptName, pattern)) {
            makeTable(pattern);
            return search(aptName, pattern);
        } else {
            return 0;
        }
    }
    
    // 아파트 이름보다 검색 문자열 길이가 더 짧을 경우 false
    public static boolean sizeCheck(String aptName, String pattern) {
        if(aptName.length() < pattern.length()) return false;
        else return true;
    }
    
    // 문자열 매칭
    public static int search(String str, String pattern) {
        int sLen = str.length(); // 아파트 이름 길이
        int pLen = pattern.length(); // 검색 문자열의 길이 (패턴)
        
        int index = 0;
        for(int i = 0; i < sLen; i++) {
            while(index > 0 && str.charAt(i) != pattern.charAt(index)) {
                index = table[index - 1];
            }
            
            if(str.charAt(i) == pattern.charAt(index)) {
                if(index == pLen - 1) {
                    index = table[index];
                    return 1; // 매칭 성공
                } else {
                    index++;
                }
            }
        }
        return 0; // 매칭 실패
    }
    
    // 테이블 생성
    public static void makeTable(String pattern) {
        int pLen = pattern.length(); // 검색 문자열의 길이 (패턴)
        table = new int[pLen];
        
        int index = 0;
        for(int i = 1; i < pLen; i++) {
            // index가 0보다 클 때부터 매칭 시작 => 연속으로 일치하지 않으면 index 값을 돌려주어 다시 매칭 시작
            while(index > 0 && pattern.charAt(i) != pattern.charAt(index)) {
                index = table[index - 1];
            }
            
            if(pattern.charAt(i) == pattern.charAt(index)) {
                index += 1;
                table[i] = index;  
            }
        }
     }
}