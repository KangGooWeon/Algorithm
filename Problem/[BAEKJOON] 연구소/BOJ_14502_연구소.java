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

public class BOJ_14502_연구소 {
	static int N, M, max, zeroCount;
	static int[][] map;
	static List<Pos> biList;
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		zeroCount = 0;
		max = 0;
		
		map = new int[N][M];
		biList = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) biList.add(new Pos(i,j));
				if(map[i][j] == 0) zeroCount++;
			}
		}
		
		if(biList.size() == 0) {
			System.out.println(zeroCount);
			return;
		}
		
		solution(0,0,0);
		
		System.out.println(max-3);
	}
	
	public static void solution(int cnt, int x, int y) {
		if (cnt == 3) {
			bfs();
			return;
		}
		
		for(int i=x; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 0) {
					map[i][j] = 1;
					solution(cnt+1, i, j);
					map[i][j] = 0;
				}
			}
		}
	}

	private static void bfs() {
		int [][] copy = new int[N][M];
		
		for(int i=0; i<N; i++) {
			copy[i] = Arrays.copyOf(map[i], M);
		}
		
		Queue<Pos> qu = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		int copyZeroCnt = zeroCount;
		
		for(int i=0; i<biList.size(); i++) {
			qu.offer(biList.get(i));
		}
		
		while(!qu.isEmpty()) {
			int size = qu.size();
			for(int i=0; i<size; i++) {
				Pos pos = qu.poll();
				
				for(int k=0; k<4; k++) {
					int x = pos.x + dx[k];
					int y = pos.y + dy[k];
					
					if( x < 0 || x >=N || y<0 || y>=M || copy[x][y] != 0 ||visited[x][y]) continue;
					
					copy[x][y] = 2;
					visited[x][y] = true;
					copyZeroCnt--;
					qu.add(new Pos(x,y));
				}
			}
		}
		
		max = Math.max(copyZeroCnt, max);
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
