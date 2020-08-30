package bak;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
	static int N, M, min;
	static int[][] map;
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		min = 100000;
		
		for(int i =0; i<N; i++) {
			String s = br.readLine();
			for(int j =0; j<M; j++) {
				map[i][j] = s.charAt(j) -'0';
			}
		}
		
		BFS(0,0);
	}

	private static void BFS(int i, int j) {
		Deque<Pos> q = new LinkedList<>();
        q.offer(new Pos(i,j,0));
        visited[i][j] = true;
        
        while (!q.isEmpty()) {
        	Pos temp = q.pollLast();
            int count = temp.count;
            
            if (temp.x == N-1 && temp.y == M-1) {
            	System.out.println(count);
            	return;
            }

            for (int k = 0; k < 4; k++) {
                int nx = temp.x + dx[k];
                int ny = temp.y + dy[k];
                
                if (nx<0 || ny <0 || nx>=N || ny >=M || visited[nx][ny]) continue;
                
                visited[nx][ny] = true;
                if (map[nx][ny] == 1) q.offerFirst(new Pos(nx,ny,count+1));
                else q.offerLast(new Pos(nx,ny,count));
            }
        }
        System.out.println(-1);
	}
	
	static class Pos{
		int x;
		int y;
		int count;
		
		public Pos(int x, int y, int count) {
			this.x = x;
			this.y = y;
			this.count = count;
		}
	}
}
