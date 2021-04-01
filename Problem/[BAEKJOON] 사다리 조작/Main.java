import java.io.*;
import java.util.*;

public class Main {
	static int N, M, H, min;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		min = Integer.MAX_VALUE;
		
		map = new int[M][N-1];
		
		for(int i=0; i<H; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			map[a-1][b-1] =1;
		}
		
		//0~3 추가 가로수 반복 -> 3보다 크면 -1 이니깐
		for(int i =0; i<=3; i++) {
			dfs(0,i,0);
			if(min != Integer.MAX_VALUE) break;
		}
		
		if(min == Integer.MAX_VALUE) min = -1;
		System.out.println(min);
	}
	private static void dfs(int cnt, int limit ,int start_x) {
		if(cnt == limit) {
			for(int i=0; i<N; i++) {
				//현재 자리하고 있는 사다리 
				int y =i;
				//지나간 사다리
				int x =0;
				while(true) {
					
					//맨 왼쪽
					if(y ==0) {
						//오른쪽 사다리 존재 검사
						if(map[x][y] == 1) y++;
					}
					//맨 오른쪽
					else if(y == N-1) {
						//왼쪽 사다리 존재 검사
						if(map[x][y-1] == 1) y--;
					}
					else {
						if(map[x][y] == 1) y++;
						else if(map[x][y-1] ==1) y--;
					}
					
					x++;
					if(x == M) break;
				}
				if(y != i) return; 
			}
			
			min = limit;
			return;
		}
		
		for(int i=start_x; i<M; i++) {
			for(int j=0; j<N-1; j++) {
				//해당 자리에 사다리 놓을 수 있는지 체크
				if(checkLadder(i,j) == false) continue;
				
				//사다리 설치
				map[i][j] = 1;
				dfs(cnt+1, limit, i);
				//사다리 제거
				map[i][j] = 0;
			}
		}
	}
	private static boolean checkLadder(int i, int j) {
		int[] dy = {1,0,-1};
		
		for(int k=0; k<3; k++) {
			int y = j+dy[k];
			
			if(y<0 || y>=N-1) continue;
			if(map[i][y] == 1) return false;
		}
		
		return true;
	}
}
