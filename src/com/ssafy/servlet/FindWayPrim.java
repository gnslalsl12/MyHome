package com.ssafy.servlet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class FindWayPrim {

    static class Apt implements Comparable<Apt> {
        double x; // 위도
        double y; // 경도
        double d; // 누적 거리
        List<Integer> selectedList; // 누적 경로
        boolean[] visited;
        
        public Apt(double x, double y, double d, List<Integer> selectedList, boolean[] visited) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.selectedList = selectedList;
            this.visited = visited;
        }

        @Override
        public int compareTo(Apt o) {
            return Double.compare(this.d, o.d);
        }
    }
    
    static class selectedApt {
        double x; // 위도
        double y; // 경도
        
        public selectedApt(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }
    
    static double min = Double.MAX_VALUE;
    static List<Integer> ans = new ArrayList<>();
 
    
    public static List<Integer> Prim(double startX, double startY, List<selectedApt> aptList) { // 시작 아파트의 위도, 경도, 나머지 아파트 리스트
        PriorityQueue<Apt> apt = new PriorityQueue<>();
        apt.add(new Apt(startX, startY, 0, new ArrayList<>(), new boolean[aptList.size()])); // 한 가지 경로의 시작 위도, 경도, 누적 거리, 방문 경로

        while(!apt.isEmpty()) {
            Apt now = apt.poll();
//            System.out.println(now);
            double x = now.x;
            double y = now.y;
            double d = now.d;
            List<Integer> nowSelectedList = now.selectedList;
            boolean[] visited = now.visited;
            
            for(int i = 0; i < aptList.size(); i++) {
                double nx = aptList.get(i).x;
                double ny = aptList.get(i).y;
                
                double tmpD = d;
                List<Integer> tmpList = new ArrayList<>();
                tmpList.addAll(nowSelectedList);
                boolean[] tmpVisited = Arrays.copyOf(visited, visited.length);
                
                if(tmpVisited[i]) continue;
                
                tmpD += Math.abs(x - nx) + Math.abs(y - ny); // 거리 누적
                tmpVisited[i] = true;
                tmpList.add(i); // 경로 누적
                
                if(tmpList.size() >= aptList.size()) { // 다 만들었으면
                    if(min > tmpD) {
                        ans.addAll(tmpList);
                        min = tmpD;
                    }
                } else {
                    apt.add(new Apt(nx, ny, tmpD, tmpList, tmpVisited));
                }
            }
        }

        return ans;
    }
}