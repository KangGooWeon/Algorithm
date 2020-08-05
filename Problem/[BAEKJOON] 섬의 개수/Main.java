package bak;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	private static int W,H, count;
	private static int[][] map;
	private static int [][] visited;
	private static Queue<Pos> q = new LinkedList<Pos>();
	private static int[] dx = {-1,-1,-1,0,0,1,1,1};
	private static int[] dy = {-1,0,1,-1,1,-1,0,1};

	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		while(true) {
			W = sc.nextInt();
			H = sc.nextInt();
			if (W== 0 && H ==0) break;
			
			map = new int [H][W];
			visited = new int [H][W];
			count = 0;
			
			for(int i =0; i<H; i++) {
				for (int j = 0; j < W; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			for(int i =0; i<H; i++) {
				for (int j = 0; j < W; j++) {
					if(map[i][j] == 1 && visited[i][j] == 0) {
						BFS(i,j);
						count++;
					}
				}
			}			
			System.out.println(count);	
		}
	}
	
	private static void BFS(int i, int j) {	
		q.offer(new Pos(i,j));
		
		while(!q.isEmpty()) {
			Pos p = q.poll();
			for(int k =0; k<8; k++) {
				int x = p.x + dx[k];
				int y = p.y + dy[k];
				
				if(x <0 || y <0 || x>=H || y >=W || map[x][y] ==0 || visited[x][y] > 0) continue;
				
				q.offer(new Pos(x,y));
				visited[x][y] = 1;
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
