import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


class Solution {
    public int[][] solution(int n, int[][] build_frame) {
    	int[][] gi = new int[n+3][n+3];
    	int[][] bo = new int[n+3][n+3];
    	
    	for(int i =0; i<build_frame.length; i++) {
    		int y = build_frame[i][0]+1; // 가로
    		int x = build_frame[i][1]+1; // 세로
    		int check = build_frame[i][2];		// 기둥인지 보인지 확인
    		int update = build_frame[i][3]; // 설치확인
    		
    		if( check == 0) {// 기둥인경우
				if (update == 1) { // 설치인경우
					if (x == 1 || gi[x - 1][y] == 1 || bo[x][y] == 1 || bo[x][y - 1] == 1) gi[x][y] = 1; // 자신 밑에 기둥이 있을 경우 설치
    			}else { 
    				if (gi[x][y] == 0) continue;
					
					gi[x][y] = 0;
					boolean che = true;
					
					out:
					for(int q = 1; q<=n+1; q++) {
			    		for(int j =1; j<=n+1; j++) {
			    			if(gi[q][j] == 1 && !(q == 1 || gi[q - 1][j] == 1 || bo[q][j] == 1 || bo[q][j - 1] == 1) ) {
			    				che = false;
			    				break out;
			    			}
			    			if(q==0) continue;
			    			if(bo[q][j] == 1 && !(gi[q-1][j] == 1 || gi[q-1][j+1] == 1 || (bo[q][j-1]==1 && bo[q][j+1]==1))) {
			    				che = false;
			    				break out;
			    			}
			    		}
			    	}
					
					if(!che) gi[x][y] = 1;
    			}
    		}
    		else {	//보인 경우
    			if ( update == 1) { // 설치인경우
    				if ( x > 1) {	// 바닥 배제
    					if (gi[x-1][y] == 1 || gi[x-1][y+1] == 1 || (bo[x][y-1]==1 && bo[x][y+1]==1)) bo[x][y] = 1; // 기둥인 경우
    				}
    			}else {
    				if (bo[x][y] == 0) continue;
					
					bo[x][y] = 0;
					boolean che = true;
					
					out:
					for(int q = 1; q<=n+1; q++) {
			    		for(int j =1; j<=n+1; j++) {
			    			if(gi[q][j] == 1 && !(q == 1 || gi[q - 1][j] == 1 || bo[q][j] == 1 || bo[q][j - 1] == 1) ) {
			    				che = false;
			    				break out;
			    			}
			    			if(q==0) continue;
			    			if(bo[q][j] == 1 && !(gi[q-1][j] == 1 || gi[q-1][j+1] == 1 || (bo[q][j-1]==1 && bo[q][j+1]==1))) {
			    				che = false;
			    				break out;
			    			}
			    		}
			    	}
					
					if(!che) bo[x][y] = 1;
    			}
    		}
    	}
    	
    	List<Pos> list = new ArrayList<>();
    	for(int i = 1; i<=n+1; i++) {
    		for(int j =1; j<=n+1; j++) {
    			if(gi[i][j] == 1) {
    				list.add(new Pos(i,j,0)); 
    			}
    			if(bo[i][j] == 1) {
    				list.add(new Pos(i,j,1));
    			}
    		}
    	}
    	
    	Collections.sort(list);
    	
    	int[][] answer = new int[list.size()][3];
    	
    	for(int i =0; i<list.size(); i++) {
    		answer[i][0] = list.get(i).y-1;
    		answer[i][1] = list.get(i).x-1;
    		answer[i][2] = list.get(i).ch;
    	}
    	
        return answer;
    }
    
    static class Pos implements Comparable<Pos>{
    	int x;
    	int y;
    	int ch;
		public Pos(int x, int y, int ch) {
			super();
			this.x = x;
			this.y = y;
			this.ch = ch;
		}
		@Override
		public int compareTo(Pos p) {
			if ( this.y == p.y) {
				if(this.x == p.x) {
					return this.ch - p.ch;
				}
				return this.x - p.x;
			}
			return this.y - p.y;
		}
    }
}