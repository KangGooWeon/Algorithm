
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	static int N, cnt;
	static char[][] map;
	static int[] dx = {-1,-1,-1,0,0,1,1,1};
	static int[] dy = {-1,0,1,-1,1,-1,0,1};
	static boolean[][] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		for(int t =1; t<=tc; t++) {
			N = Integer.parseInt(br.readLine());
			map = new char[N][N];
			visited = new boolean[N][N];
			cnt=0;
			
			
			for(int i =0; i<N; i++) {
				char[] c = br.readLine().toCharArray();
				for(int j=0; j<N; j++) {
					map[i][j] = c[j];
					if (map[i][j] == '.') cnt++; 
				}
			}
			
			int count=0;
			for(int i =0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j] == '*' || visited[i][j]) continue;
					if(!check(i,j)) continue;
					count++;
					fill(i,j);
				}
			}
			
			System.out.println("#"+t+" "+(count+cnt));
		}
	}
	
	private static boolean check(int i, int j) {
		for(int k = 0; k<8; k++) {
			int x = i +dx[k];
			int y = j +dy[k];
			
			if (x <0 || x>=N || y<0 || y>=N) continue;
			if (map[x][y] == '*') return false;
		}
		return true;
	}
	
	private static void fill(int i, int j) {
		Queue<Pos> qu = new LinkedList<>();
		qu.offer(new Pos(i,j));
		visited[i][j] = true;
		cnt--;
		
		while(!qu.isEmpty()) {
			int size = qu.size();
			for(int q=0; q<size; q++) {
				Pos pos = qu.poll();
				if(!check(pos.x,pos.y)) continue;
				
				for(int k = 0; k<8; k++) {
					int x = pos.x +dx[k];
					int y = pos.y +dy[k];
					
					if (x <0 || x>=N || y<0 || y>=N || visited[x][y]) continue;
					visited[x][y] = true;
					cnt--;
					qu.offer(new Pos(x,y));
				}
			}
		}
	}

	static class Pos{
		int x;
		int y;
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
