
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	private static int N, K, max;
	private static int[][] map, visited;
	private static List<Pos> top;
	private static int[] dx = {0,0,-1,1};
	private static int[] dy = {1,-1,0,0};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());

		for (int t = 1; t <= TC; ++t) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			visited = new int[N][N];
			top = new ArrayList<>();
			max =0;
			
			int dMax = 0;
			for (int i = 0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j =0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (dMax < map[i][j]) dMax = map[i][j];
				}
			}
			
			for (int i = 0; i<N; i++) {
				for(int j =0; j<N; j++) {
					if (map[i][j] == dMax) top.add(new Pos(i,j));
				}
			}
			
			for(int j =0; j<top.size(); j++) {
				Pos pos = top.get(j);
				DFS(pos.x, pos.y,0);
			}
			
			for(int i =1; i<=K; i++) {
				for(int x =0; x<N; x++) {
					for(int y = 0; y<N; y++) {
						for(int j =0; j<top.size(); j++) {
							Pos pos = top.get(j);
							map[x][y] -= i;
							DFS(pos.x, pos.y,1);
							map[x][y] += i;
						}
					}
				}
			}
			System.out.println("#"+t+" "+max);
		}
	}
	private static void DFS(int i, int j, int cnt) {
		if(max<cnt) max = cnt;
		for(int k =0; k<4; k++) {
			int x = i +dx[k];
			int y = j +dy[k];
			
			if (x<0 || y<0 || x>=N || y>=N || map[x][y] >= map[i][j] || visited[x][y] == 1) continue;
			
			visited[x][y] = 1;
			DFS(x,y,cnt+1);
			visited[x][y] = 0;
		}
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

