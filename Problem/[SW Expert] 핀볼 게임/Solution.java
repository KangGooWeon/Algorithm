package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Pinball {
	
	static int[][] map;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	static int N, max;
	static List<Pos> list = new ArrayList<>();
	
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int TC = Integer.parseInt(br.readLine());
		
		for(int t=1;t<=TC;++t) {
			N = Integer.parseInt(br.readLine());
			max =0;
			list.clear();
			map = new int[N][N];
			for(int i=0;i<N;++i) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;++j) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if ( map[i][j] > 5 ) list.add(new Pos(i,j, map[i][j]));
				}
			}
			
			for(int i =0; i<N; i++) {
				for(int j =0; j<N; j++) {
					if (map[i][j] == 0) {
						for(int k =0; k<4; k++) {
							soltion(i,j,k);
						}
					}
				}
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ").append(max);
			System.out.println(sb.toString());
		}
	}

	private static void soltion(int i, int j, int dir) {
		int count = 0;
		int x = i +dx[dir];
		int y = j +dy[dir];
		while(true) {
			
			if ( x< 0 || y < 0 || x>=N || y>=N || map[x][y] == 5) {
				if (dir == 0) dir = 1;
				else if (dir == 1) dir = 0;
				else if (dir == 2) dir = 3;
				else if (dir == 3) dir = 2;
				count++;
			}
			
			else if (x==i && y==j || map[x][y] == -1) {
				if (max <count) max = count;
				break;
			}
			
			else if (map[x][y] == 1) {
				if (dir == 0) dir = 2;
				else if (dir == 1) dir = 0;
				else if (dir == 2) dir = 3;
				else if (dir == 3) dir = 1;
				count++;
			}
			
			else if (map[x][y] == 2) {
				if (dir == 0) dir = 1;
				else if (dir == 1) dir = 2;
				else if (dir == 2) dir = 3;
				else if (dir == 3) dir = 0;
				count++;
			}
			
			else if (map[x][y] == 3) {
				if (dir == 0) dir = 1;
				else if (dir == 1) dir = 3;
				else if (dir == 2) dir = 0;
				else if (dir == 3) dir = 2;
				count++;
			}
			
			else if (map[x][y] == 4) {
				if (dir == 0) dir = 3;
				else if (dir == 1) dir = 0;
				else if (dir == 2) dir = 1;
				else if (dir == 3) dir = 2;
				count++;
			}
			
			else if (map[x][y] > 5) {
				for(Pos p : list) {
					if((p.x != x || p.y != y) && map[x][y] == p.data) {
						x = p.x;
						y = p.y;
						break;
					}
				}
			}
			x += dx[dir];
			y += dy[dir];
		}
	}
}

class Pos{
	int x;
	int y;
	int data;
	public Pos (int x, int y, int data) {
		this.x = x;
		this.y = y;
		this.data = data;
	}
}