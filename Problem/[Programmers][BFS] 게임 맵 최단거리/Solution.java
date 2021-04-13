import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[][] maps) {
        int answer = 1;
        int N = maps.length;
        int M = maps[0].length;
        int[] dx = {0,0,1,-1};
        int[] dy = {1,-1,0,0};
        boolean[][] visited = new boolean[N][M];
        Queue<Pos> qu = new LinkedList<>();
        qu.offer(new Pos(0,0));
        visited[0][0]= true;
        boolean flag = false;
        
        loop:
        while(!qu.isEmpty()) {
        	int size = qu.size();
        	
        	for(int i=0; i<size; i++) {
        		Pos pos = qu.poll();
        		
        		if(pos.x == N-1 && pos.y == M-1) {
        			flag = true;
        			break loop;
        		}
        		
        		for(int k=0; k<4; k++) {
        			int x = pos.x + dx[k];
        			int y = pos.y + dy[k];
        			
        			if(x<0 || x>=N || y<0 || y>=M || visited[x][y] || maps[x][y] == 0) continue;
        			
        			qu.offer(new Pos(x,y));
        			visited[x][y] = true;
        		}
        	}
        	
        	answer++;
        }
        
        if(!flag) answer = -1;
        return answer;
    }
    
    static class Pos{
    	int x;
    	int y;
		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
    }
}