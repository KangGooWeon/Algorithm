
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int min, N;
	static int[][] map;
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };
	static int[][] ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for (int t = 1; t <= TC; ++t) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			ans = new int[N][N];
			min = Integer.MAX_VALUE;

			for (int i = 0; i < N; i++) {
				char[] c = br.readLine().toCharArray();
				for (int j = 0; j < N; j++) {
					map[i][j] = c[j] - '0';
				}
			}
			
			ans = new int[N][N];
            for(int i=0; i<N; i++)
                Arrays.fill(ans[i], Integer.MAX_VALUE);
            ans[0][0] = 0;

			bfs();
			System.out.println("#" + t + " " + min);
		}
	}

	private static void bfs() {
		Queue<Pos> qu = new LinkedList<>();
		qu.offer(new Pos(0,0,0,0));
		
		while(!qu.isEmpty()) {
			int size = qu.size();
			for(int i = 0; i<size; i++) {
				Pos pos = qu.poll();
				
				if (pos.x==N-1 && pos.y==N-1) {
					min = Math.min(min, pos.fee);
					break;
				}
								
				for(int k =0; k<4; k++) {
					int x = pos.x +dx[k];
					int y = pos.y +dy[k];
					
					if(x <0 || x>=N || y<0 || y>=N || ans[x][y] <= pos.fee+map[x][y]) continue;
					
					ans[x][y] = pos.fee+map[x][y];
					qu.offer(new Pos(x,y,pos.dis+1, pos.fee+map[x][y]));
				}
			}
		}
	}

	static class Pos implements Comparable<Pos> {
		int x;
		int y;
		int dis;
		int fee;

		public Pos(int x, int y, int dis, int fee) {
			super();
			this.x = x;
			this.y = y;
			this.dis = dis;
			this.fee = fee;
		}

		@Override
		public int compareTo(Pos o) {
			return this.fee - o.fee;
		}
	}
}
