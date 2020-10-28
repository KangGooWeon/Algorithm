import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static int time, tempCh, cheese;
	static int N,M;
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N+2][M+2];
		
		for(int i =1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j =1; j<=M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) cheese++;
			}
		}
		
		while(true) {
			time++;
			int[][] remove = new int[N+2][M+2];
			remove = bfs(remove);
			tempCh = cheese;
			removeCheese(remove);
			if(cheese <=0 )break;
		}
		
		System.out.println(time);
		System.out.println(tempCh);
	}
	private static void removeCheese(int[][] remove) {
		for(int i =1; i<=N; i++) {
			for(int j =1; j<=M; j++) {
				if(remove[i][j] == 1) {
					map[i][j] = 0;
					cheese--;
				}
			}
		}
	}
	private static int[][] bfs(int[][] remove) {
		Queue<Pos> qu = new LinkedList<>();
		qu.offer(new Pos(0,0));
		boolean[][] visited = new boolean[N+2][M+2];
		visited[0][0] = true;;
		
		while(!qu.isEmpty()) {
			int size = qu.size();
			for(int i =0; i<size; i++) {
				Pos pos = qu.poll();
				for(int k =0; k<4; k++) {
					int x = pos.x + dx[k];
					int y = pos.y + dy[k];
					
					if(x <0 || x>N || y <0 || y>M || visited[x][y]) continue;
					
					if(map[x][y] == 1 ) {
						remove[x][y] = 1;
						continue;
					}
					
					qu.offer(new Pos(x,y));
					visited[x][y] = true;;
				}
			}
		}
		
		return remove;
	}
	
	static class Pos{
		int x;
		int y;
		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
}
