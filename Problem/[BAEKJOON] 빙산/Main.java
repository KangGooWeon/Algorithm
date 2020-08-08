package Algorism;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Pos{
		int x;
		int y;
		
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N,M,year;
	static int [][]map;
	static int [][] visited;
	static int [][] bigSanVisit;
	static int [] dx = {0,0,-1,1};
	static int [] dy = {1,-1,0,0};
	static Queue<Pos> qu = new LinkedList<Pos> ();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new int[N][M];
		bigSanVisit = new int[N][M];
		
		for (int i = 0; i < N; i++) {

			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] >0) qu.offer(new Pos(i,j));
			}
		}
		
		int check = 0;
		while(!qu.isEmpty()) {
			BingSan();
			
			int count =0;
			int size = qu.size();
			for(int i =0; i<size; i++) {
				Pos p = qu.poll();
 				if (visited[p.x][p.y] == 0) {
 					visited[p.x][p.y] = 1;
 					DFS(p);
					count++;
				}
				qu.offer(p);
			}
			
			year++;
			
			if (count > 1) {
				check =1;
				break;
			}
			
			for(int[] row: visited) {
	            Arrays.fill(row, 0);
	        }
		}
		
		if (check == 0) System.out.println(0);
		else System.out.println(year);
	}

	private static void DFS(Pos p) {
		for(int j =0; j<4; j++) {
			int x = p.x + dx[j];
			int y = p.y + dy[j];
			
			if( x <0 || y<0 || x>=N || y>=M || map[x][y] <=0 || visited[x][y] == 1) continue;
			
			visited[x][y] = 1;
			DFS(new Pos(x,y));
		}
	}

	private static void BingSan() {
		int size = qu.size();
		for(int i =0; i<size; i++) {
			Pos p = qu.poll();
			for(int j =0; j<4; j++) {
				int x = p.x + dx[j];
				int y = p.y + dy[j];
				
				if( x <0 || y<0 || x>=N || y>=M || map[x][y] >0 || bigSanVisit[x][y] == 1) continue;
				
				bigSanVisit[p.x][p.y] = 1;
				map[p.x][p.y]--;
			}
			if (map[p.x][p.y] > 0) qu.offer(new Pos(p.x, p.y));
		}
		
		for(int[] row: bigSanVisit) {
            Arrays.fill(row, 0);
        }
	}
}
