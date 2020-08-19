package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	private static int N, W, H, stoneCnt;
	private static int[][] map;
	private static int[][] copy;
	private static int[] isSelected;
	private static int[] dx = {0,0,-1,1};
	private static int[] dy = {1,-1,0,0};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for (int t = 1; t <= TC; ++t) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			map = new int[H][W];
			copy = new int[H][W];
			isSelected = new int[N];
			stoneCnt = 100000;
			
			for(int i =0; i<H; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j =0; j<W; j++) {
					copy[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			permutation(0);
			
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ").append(stoneCnt);
			System.out.println(sb.toString());
		}
	}
	
	private static void permutation(int cnt) {
		if(cnt == N) {
			
			for(int i =0; i<H; i++) {
				map[i] = Arrays.copyOf(copy[i], W);
			}

			for(int i = 0; i< N; i++) {
				int count =0;
				int down = isSelected[i];
				while(true) {
					if (count >= H || map[count][down] > 0) break;
					count++;
				}
				if (count != H) bfs(count, down);
				int check = move();
				if (check < stoneCnt) stoneCnt = check;
			}
			return;
		}
		
		for(int i = 0; i< W; i++) {
			isSelected[cnt] =i;
			permutation(cnt+1);
		}
	}

	private static int move() {
		Queue<Stone> qu2 = new LinkedList<Stone>();
		int count =0;
		
		for(int j =0; j<W; j++) {
			for(int i =H-1; i>=0; i--) {
				if (map[i][j] > 0) {
					qu2.offer(new Stone(map[i][j]));
					count++;
					map[i][j] =0;
				}
			}
			
			int size = qu2.size();
			for(int i =H-1; i>=(H-size); i--) {
				Stone stone = qu2.poll();
				map[i][j] = stone.dis;
			}
		}
		return count;
	}

	private static void bfs(int i, int j) {
		Queue<Stone> qu = new LinkedList<Stone>();
		qu.offer(new Stone(i,j, map[i][j]));
		map[i][j] = 0;
		
		while(!qu.isEmpty()) {
			Stone stone = qu.poll();
			for(int k =0; k<4; k++) {
				int x = stone.x+dx[k];
				int y = stone.y+dy[k];
				for(int h = 1; h< stone.dis; h++) {
					if( x <0 || y<0 || x>=H || y >=W) continue;
					if(map[x][y] !=0) {
						qu.offer(new Stone(x,y, map[x][y]));
						map[x][y] = 0;	
					}
					x += dx[k];
					y += dy[k];
				}
			}
		}
	}
}

class Stone{
	int x;
	int y;
	int dis;
	public Stone(int x, int y, int dis) {
		this.x = x;
		this.y = y;
		this.dis = dis;
	}
	public Stone(int dis) {
		this.dis = dis;
	}
}
