import java.io.*;
import java.util.*;

public class Main {
	static int[][] RGB;
	static int N, color, nomal;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		RGB = new int[N][N];
		boolean[][] colorVisited = new boolean[N][N];
		boolean[][] nomalVisited = new boolean[N][N];
		
		for(int i=0; i<N; i++) {
			String color = br.readLine();
			for(int j=0; j<N; j++) {
				switch(color.charAt(j)) {
				case 'R':
					RGB[i][j] = 1;
					break;
				case 'G':
					RGB[i][j] = 2;
					break;
				case 'B':
					RGB[i][j] = 3;
					break;
				}
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				//색약인 사람
				if(!colorVisited[i][j]) {
					color++;
					bfs(i,j, colorVisited, 1);
				}
				
				//색약이 아닌 사람
				if(!nomalVisited[i][j]) {
					nomal++;
					bfs(i,j, nomalVisited, 2);
				}
			}
		}
		
		System.out.println(nomal+" "+color);
	}
	
	public static void bfs(int i, int j, boolean[][] visited, int mode) {
		int[] dx = {0,0,1,-1};
		int[] dy = {1,-1,0,0};
		Queue<Pos> qu = new LinkedList<>();
		qu.offer(new Pos(i,j));
		visited[i][j] = true;
		int fix = RGB[i][j];
		
		while(!qu.isEmpty()) {
			int size = qu.size();
			for(int q=0; q<size; q++) {
				Pos pos = qu.poll();
				
				for(int k=0; k<4; k++) {
					int x = pos.x +dx[k];
					int y = pos.y +dy[k];
					
					if(x<0 || x>=N || y<0 || y>=N || visited[x][y]) continue;
					
					if(fix != RGB[x][y]) {
							if(mode == 1 && (fix == 1 || fix ==2) && (RGB[x][y] ==3)) continue;
							if(mode == 1 && fix == 3 && (RGB[x][y] ==1 || RGB[x][y] ==2)) continue;
							
							if(mode == 2) continue;
					}
					
					visited[x][y] = true;
					qu.offer(new Pos(x,y));
				}
			}
		}
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
