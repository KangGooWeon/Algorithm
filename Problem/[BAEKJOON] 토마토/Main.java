package bak;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	private static int N,M, count;
	private static int[][] map;
	private static int [][] visited;
	private static Queue<Pos> q = new LinkedList<Pos>();
	private static int[] dx = {0,0,1,-1};
	private static int[] dy = {1,-1,0,0};

	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int [M][N];
		visited =  new int [M][N];
		
		for(int i =0; i<M; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] == 1) {
					q.offer(new Pos(i,j));
					visited[i][j] = 1;
				}
			}
		}
		
		if(tomatoCheck()) {
			System.out.println(0);
			return;
		}
		
		BFS();
		
		if (!tomatoCheck()) {
			System.out.println(-1);
			return;
		}
			
		int max = 0;
		for(int i =0; i<M; i++) {
			for (int j = 0; j < N; j++) {
				if(visited[i][j] > max) {
					max = visited[i][j];
				}
			}
		}
		
		System.out.println(max-1);
	}
	
	private static boolean tomatoCheck() {
		for(int i =0; i<M; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}

	private static void BFS() {	
		while(!q.isEmpty()) {
			Pos p = q.poll();
			for(int i =0; i<4; i++) {
				int x = p.x + dx[i];
				int y = p.y + dy[i];
				
				if(x <0 || y <0 || x>=M || y >=N || map[x][y] !=0 || visited[x][y] > 0) continue;
				
				q.offer(new Pos(x,y));
				map[x][y] = 1;
				visited[x][y] = visited[p.x][p.y]+1;
			}
		}
	}
}

class Pos{
	int x;
	int y;
	
	public Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
