package bak;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
	static int N, M;
	static int[][] map, visited;
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N+2][M+2];
		visited = new int[N+2][M+2];
		
		for(int i = 1; i<=N; i++) {
			String s = br.readLine();
			for(int j =0; j<M; j++) {
				int num = s.charAt(j) -'0';
				map[i][j+1] = num;
			}
		}
		
		BFS(1,1);
		int result = visited[N][M];
		if (result == 0) System.out.println(-1);
		else System.out.println(result);
	}
	
	private static void BFS(int i, int j) {
		Queue<Pos> qu = new LinkedList<>();
		qu.add(new Pos(i,j,0));
		visited[i][j] = 1;
		
		while(!qu.isEmpty()) {
			Pos pos = qu.poll();
			for(int k =0; k<4; k++) {
				int x = pos.x + dx[k];
				int y = pos.y + dy[k];
				int drill = pos.drill;
				
				if (x<=0 || y<=0 || x>=N+1 || y>=M+1) continue;
					
				if(map[x][y] == 1) { //벽
                    if (drill == 0 && visited[x][y] ==0) { //벽을 부신 적 없음
                    	visited[x][y] = visited[pos.x][pos.y]+1;
                        qu.offer(new Pos(x, y, 1));
                    }
                } else { //빈 칸
                    if(visited[x][y] ==0) {
                    	qu.offer(new Pos(x, y, drill));
                    	visited[x][y] = visited[pos.x][pos.y]+1;
                    }
                }
			}
		}
	}

	static class Pos{
		int x;
		int y;
		int drill;
		public Pos(int x, int y, int drill) {
			this.x = x;
			this.y = y;
			this.drill = drill;
		}
	}
}
